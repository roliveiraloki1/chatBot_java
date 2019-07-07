/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotclient;

/**
 *
 * @author roliv
 */
public class ChatBotClient {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hostname = null; //seta o hostname como null
        int port = 1234; //seta a porta a se conectar em 1234
 
        Client client = new Client(hostname, port); //cria um novo cliente
        client.execute(); //executa o metodo para conectar o cliente criado
    }
}
