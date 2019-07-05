/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 *
 * @author roliv
 */
public class Client {
    
    private InetAddress address;
    private int port;
 
    /**
     * construtor que seta o hostname e port que o cliente se conecta
     * @param address endereço do server
     * @param port porta do server
     */
    public Client(InetAddress address, int port) { 
        this.address = address;
        this.port = port;
    }
 
    /**
     * Método que executa a ação do cliente para conexão ao server
     */
    public void execute() {
        try {
            Socket socket = new Socket(address, port); //cria um socket com os parametros para conectar no server
 
            System.out.println("Conectado ao server.");
 
            new ReadThread(socket).start(); //executa o metodo run() da classe ReadThread
            new WriteThread(socket).start(); //executa o metodo run() da classe WriteThread
 
        } catch (UnknownHostException ex) {
            System.out.println("Servidor não encontrado: " + ex.getMessage()); //printa erro caso não encontre o server
        } catch (IOException ex) {
            System.out.println("Erro I/O: " + ex.getMessage()); //printa erro caso encontre algum outro problema
        }
 
    }
}
