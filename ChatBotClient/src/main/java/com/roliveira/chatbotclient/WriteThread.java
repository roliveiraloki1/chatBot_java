/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author roliv
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;

    /**
     * construtor da classe
     * @param socket utiliza o socket criado do cliente para receber a resposta do server
     */
    public WriteThread(Socket socket) { //construtor do WriteThread
        this.socket = socket;

        try {
            OutputStream output = socket.getOutputStream(); //pega a resposta dada
            writer = new PrintWriter(output, true); //cria um escritor com a resposta
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage()); //printa msg de erro caso não consiga receber a resposta
        }
    }

       /**
        * Método que inicia o receptor de mensagens do usuário
        */
    @Override
    public void run() {
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            
            String input;

            do {
                input = console.readLine(); //
                writer.println(input);

            } while (!input.equals("Sair"));

            socket.close();
        } catch (IOException ex) {

            System.out.println("Erro ao enviar mensagem pro servidor: " + ex.getMessage());
        }
    }
}
