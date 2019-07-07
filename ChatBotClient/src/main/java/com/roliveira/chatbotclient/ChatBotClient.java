/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roliv
 */
public class ChatBotClient {

    /**
     * @param args the command line arguments
     * Classe main do cliente
     */
    public static void main(String[] args) {

        try { //neste try-catch o cliente entra em um loope esperando resposta de um servidor disponível

            String ip = null;
            int port = 0;
            boolean serverNotFree = true;

            while (serverNotFree) { //enquanto não houver um servidor com espaço para novos clientes o loop continua

                InetAddress adrs = InetAddress.getByName("127.0.0.1"); //seta o ip do servidor Gerenciador
                Socket manager = new Socket(adrs, 1234); //Conecta ao servidor Gerenciador

                InputStream input = manager.getInputStream();//Aguarda dados do manager (Gerenciador)
                BufferedReader reader = new BufferedReader(new InputStreamReader(input)); //lê os dados do manager

                ip = reader.readLine();//Recebe endereço ip do servidor que será alocado ou mensagem de servidores lotados (-1)
                if (ip.equals("-1")) {//caso a mensagem atribuída ao ip seja -1, ainda não existe servidor com vaga
                    System.out.println("Todos os servidores estão ocupados no momento.");//printa constantemente msg de servidores ocupados
                } else {
                    port = Integer.parseInt(reader.readLine());//Recebe a porta do servidor que será alocado
                    serverNotFree = false;
                }

                manager.close();//Fecha socket do manager neste cliente

            }

            Client client = new Client(ip, port); //cria um novo cliente
            client.execute(); //executa o metodo para conectar o cliente criado

        } catch (UnknownHostException ex) {
            Logger.getLogger(ChatBotClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatBotClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
