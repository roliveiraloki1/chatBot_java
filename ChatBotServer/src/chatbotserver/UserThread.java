/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotserver;

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
public class UserThread extends Thread{
    
    private Socket socket;
    private Server server;
    private PrintWriter writer;
 
    public UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }
    
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
 
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
 
            String userID = String.valueOf(this.getId());
           
            String clientMessage;
 
            do {
                clientMessage = reader.readLine();
                System.out.println("Usuário " + userID + ": " + clientMessage);

                server.respond(Command.executeCommand(clientMessage), this);
                
 
            } while (!clientMessage.equals("Sair"));
 
            server.removeUser(this);
            socket.close();

 
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
