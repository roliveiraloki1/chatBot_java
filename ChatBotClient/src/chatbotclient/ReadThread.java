/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author roliv
 */
public class ReadThread extends Thread {

    private BufferedReader reader;
    private Socket socket;

    public ReadThread(Socket socket) {
        this.socket = socket;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Erro ao pegar input: " + ex.getMessage());
        }
    }

    /**
     *
     */
    @Override
    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                System.out.println(response);

            } catch (IOException ex) {
                System.out.println("Você foi desconectado do servidor: " + ex.getMessage());
                break;
            }
        }
    }

}
