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

    public WriteThread(Socket socket) {
        this.socket = socket;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            
            String input;

            do {
                input = console.readLine();
                writer.println(input);

            } while (!input.equals("Sair"));

            socket.close();
        } catch (IOException ex) {

            System.out.println("Erro ao enviar mensagem pro servidor: " + ex.getMessage());
        }
    }
}
