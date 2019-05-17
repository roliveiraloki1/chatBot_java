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
    private final Socket SOCKET;
    private final Server SERVER;
    private PrintWriter writer;
 
    public UserThread(Socket socket, Server server) {
        this.SOCKET = socket;
        this.SERVER = server;
    }
    
    @Override
    public void run() {
        try {
            InputStream input = SOCKET.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
 
            OutputStream output = SOCKET.getOutputStream();
            writer = new PrintWriter(output, true);
 
            String userID = String.valueOf(this.getId());
           
            String clientMessage;
 
            do {
                clientMessage = reader.readLine();
                System.out.println("Usuário " + userID + ": " + clientMessage);

                SERVER.respond(Command.executeCommand(clientMessage), this);
                
 
            } while (!clientMessage.equals("Sair"));
 
            SERVER.removeUser(this);
            SOCKET.close();

 
        } catch (IOException ex) {
            System.out.println("Erro na thread do usuário: " + ex.getMessage());
        }
    }
 
    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}
