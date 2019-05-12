/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotserver;

/**
 *
 * @author roliv
 */
public class ChatBotServer extends Thread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 12345;
        Server server = new Server(port);
        server.execute();
    }
    

}
