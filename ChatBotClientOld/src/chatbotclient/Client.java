/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotclient;

import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 *
 * @author roliv
 */
public class Client {
    
    private String hostname;
    private int port;
 
    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
 
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
 
            System.out.println("Conectado ao server.");
 
            new ReadThread(socket).start();
            new WriteThread(socket).start();
 
        } catch (UnknownHostException ex) {
            System.out.println("Servidor não encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro I/O: " + ex.getMessage());
        }
 
    }
    
}
