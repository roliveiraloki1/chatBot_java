/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotserver.AppServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author roliv
 */
public class UserThread extends Thread {
    private final Socket socket;
    private final Server server;
    private PrintWriter writer;
 
    /**
     * construtor da thread dos usuários. Recebe o server a entrar e um socket para o server
     * @param socket socket daquele cliente
     * @param server dados do server que o cliente está
     */
    public UserThread(Socket socket, Server server) { 
        this.socket = socket;
        this.server = server;
    }
    
    /**
     * método executor que inicia as ações do Usuário da thread
     */
    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream(); //cria um "leitor" para receber o que o usuário escreve
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));//Guarda o que o usuário digitou
            
 
            OutputStream output = socket.getOutputStream(); //receptor para o que foi escrito
            writer = new PrintWriter(output, true); //cria um PrintWriter para printar os dados 
 
            String userID = String.valueOf(this.getId()); //pega o id do usuário 
           
            String clientMessage;
 
            do {
                clientMessage = reader.readLine(); //recebe do cliente o que foi digitado
                System.out.println("Usuário " + userID + ": " + clientMessage); //printa no console do servidor a mensagem recebida

                server.respond(Command.executeCommand(clientMessage), this); //responde no console do cliente a resposta recebida pelo
                                                                            //comando
                
 
            } while (!clientMessage.equals("Sair")); //enquanto a mensagem recebida do usuário for diferente de "Sair" continua rodando
 
            server.removeUser(this); //ao sair do laço, remove o usuário
            socket.close(); //ao remover o usuário, fecha o socket que estava sendo utilizado por ele

 
        } catch (IOException ex) {
            System.out.println("Erro na thread do usuário: " + ex.getMessage()); //printa erro caso encontre erro na thread
            server.removeUser(this); //ao sair do laço, remove o usuário
        }
    }
 
    /**
     * método que envia a resposta do server para o usuário específico
     * @param message 
     */
    void sendMessage(String message) { 
        writer.println(message);
    }
}
