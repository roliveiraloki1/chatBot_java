/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotserver.AppServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author roliv
 */
public class Server extends Thread{

    private ServerSocket serverSocket;
    private final Set<UserThread> userThreads = new HashSet<>(); //aqui é criado os UserThreads por Set para, posteriormente, limitar
    //o númeor de clientes que acessam o sistema

    /**
     * método construtor da classe
     *
     * @param port recebe o port do server
     */
    public Server(int port, InetAddress address) throws IOException {
        this.serverSocket = new ServerSocket(port, 5, address);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public int getNumberUsersConnected() {
        return userThreads.size();
    }

    /**
     * executa o server
     */
    @Override
    public void run() {

        try { //aqui o sistema tenta abrir o server com um ServerSocket utilizando a 
            //porta setada.

            while (true) {//aqui é feito o loop do server, enquanto ele estiver ligado ele fica recebendo novos usuários
                Socket socket = serverSocket.accept(); //aqui é aberto um novo socket para o novo usuário

                UserThread newUser = new UserThread(socket, this); //é criado um novo usuário que recebe o novo socket
                System.out.println("O cliente " + newUser.getName() + " está conectado no servidor "
                        + serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort());
                
                userThreads.add(newUser); //o usuário é adicionado no hashSet de threads
                newUser.start(); //executa o método run() da classe UserThread

            }

        } catch (IOException ex) {
            System.out.println("Erro no servidor: " + ex.getMessage()); //printa um erro caso o servidor não possa ser aberto
        }

    }

    /**
     * esse método pega a mensagem retornada pelo server e printa para o usuário
     * a resposta
     *
     * @param message mensagem retornada do método executeCommand()
     * @param user
     */
    public void respond(String message, UserThread user) {
        user.sendMessage(message); //
    }

    /**
     * Quando um usuário é desconectado, remove a Thread daquele usuário do
     * sistema.
     */
    public void removeUser(UserThread aUser) { //esse método remove o usuário do server, caso ele digite "Sair"
        userThreads.remove(aUser); //método para remover a thread do usuário específico
        System.out.println("O usuário " + aUser.getId() + " saiu do servidor.");
    }

}
