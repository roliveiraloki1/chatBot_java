/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotserver.AppServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author roliv
 */
public class Server implements Runnable {

    private int port;
    private int counter;
    private InetAddress address;
    private String nome = "servidor";
    private final Set<UserThread> userThreads = new HashSet<>(); //aqui é criado os UserThreads por Set para, posteriormente, limitar
    //o númeor de clientes que acessam o sistema

    public Server(int port, InetAddress address) {
        this.port = port;
        this.address = address;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPort() {
        return port;
    }

    public int getCounter() {
        return counter;
    }

    public InetAddress getAddress() {
        return address;
    }

    public Server() {

    }

    /**
     * executa o server
     */
    public void execute() {

        try (ServerSocket serverSocket = new ServerSocket(port, 3, address)) { //aqui o sistema tenta abrir o server com um ServerSocket utilizando a 
            //porta setada.

            System.out.println("O " + nome + " está aberto na porta: " + port + " e no endereço de ip: " + address + ".");

            while (true) {//aqui é feito o loop do server, enquanto ele estiver ligado ele fica recebendo novos usuários
                Socket socket = serverSocket.accept(); //aqui é aberto um novo socket para o novo usuário
                System.out.println("Novo usuário conectado.");
                counter++;

                UserThread newUser = new UserThread(socket, this); //é criado um novo usuário que recebe o novo socket
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

    public PrintWriter setClient(Server mainServer) {
        try (ServerSocket serverSocket = new ServerSocket(mainServer.getPort(), 10, mainServer.getAddress())) {

            System.out.println("O Gerenciador está aberto na porta: " + mainServer.getPort()
                    + " e no endereço de ip: " + mainServer.getAddress() + ".");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Novo usuário conectado.");

                UserThread newUser = new UserThread(socket, mainServer);
                OutputStream sender = newUser.getSocket().getOutputStream();
                PrintWriter writer = new PrintWriter(sender, true);
                return writer;

            }

        } catch (IOException ex) {
            System.out.println("Erro no servidor: " + ex.getMessage()); //printa um erro caso o servidor não possa ser aberto
        }
        return null;

    }

    /**
     * Quando um usuário é desconectado, remove a Thread daquele usuário do
     * sistema.
     */
    public void removeUser(UserThread aUser) { //esse método remove o usuário do server, caso ele digite "Sair"
        userThreads.remove(aUser); //método para remover a thread do usuário específico
        counter--;
        System.out.println("O usuário " + aUser.getId() + " saiu do servidor.");
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
