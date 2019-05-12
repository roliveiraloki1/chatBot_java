/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotclient;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author roliv
 */
public class WriteThread extends Thread{
    
    private PrintWriter writer;
    private Socket socket;
 
    public WriteThread(Socket socket) {
        this.socket = socket;
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
 
        Console console = System.console();
 
        String text;
 
        do {
            text = console.readLine();
            writer.println(text);
 
        } while (!text.equals("Sair"));
 
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Erro ao enviar mensagem pro servidor: " + ex.getMessage());
        }
    }
    
}
