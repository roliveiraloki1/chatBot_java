/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotclient;

import java.net.InetAddress;

/**
 *
 * @author roliv
 */
public class ChatBotClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hostname = null;
        int port = 12345;
 
        Client client = new Client(hostname, port);
        client.execute();
    }
    
}
