/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotserver.AppServer;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roliv
 */
public class ChatBotServer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        try {
            Manager manager = new Manager();
            manager.run();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ChatBotServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatBotServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
