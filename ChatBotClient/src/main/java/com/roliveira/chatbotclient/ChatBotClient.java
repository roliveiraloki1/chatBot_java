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

/**
 *
 * @author roliv
 */
public class ChatBotClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {

        InetAddress adrs = InetAddress.getByName("127.0.0.1"); //seta o ip do servidor Gerenciador
        Socket mainServer = new Socket(adrs, 1234); //Conecta ao servidor Gerenciador

        
        InputStream input = mainServer.getInputStream();//Aguarda dados do MainServer (Gerenciador)
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String ip = reader.readLine();//Recebe endereço ip e porta do servidor que será alocado
        int port = Integer.parseInt(reader.readLine());
        String[] strIP = ip.split("/");//Formaliza a string do ip
        System.out.println(port);
        InetAddress address = InetAddress.getByName(strIP[1]);// seta o endereço com o ip recebido
        System.out.println("teste1");
        mainServer.close();//Fecha socket do líder neste cliente
        System.out.println("teste2");

        //Client client = new Client(address, port); 
        Socket client = new Socket(address, port);//cria um novo cliente
        System.out.println("teste3");
        System.out.println(address + " " + port);
        connect(client); //executa o metodo para conectar o cliente criado
    }

    public static void connect(Socket socket) throws IOException {
        
        new ReadThread(socket).start(); //executa o metodo run() da classe ReadThread
        new WriteThread(socket).start(); //executa o metodo run() da classe WriteThread
    }
}
