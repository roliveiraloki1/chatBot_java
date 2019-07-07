/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotserver.AppServer;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roliv
 */
public class Manager {

    private List<Server> serverList;
    private ServerSocket socket;
    private InetAddress[] addressVector;
    private int[] portVector = {1111, 2222, 3333};

    public Manager() throws UnknownHostException, IOException {
        this.socket = new ServerSocket(1234);
        this.serverList = new ArrayList<Server>();
        this.addressVector = new InetAddress[]{Inet4Address.getByName("127.0.0.1"),
            Inet4Address.getByName("127.0.0.1"), Inet4Address.getByName("127.0.0.1")};
    }

    public void run() {
        try { //aqui o sistema tenta abrir o server com um ServerSocket utilizando a 
            //porta setada.

            System.out.println("O Gerenciador está aberto na porta: 1234");

            while (true) {//aqui é feito o loop do server, enquanto ele estiver ligado ele fica recebendo novos usuários
                Socket socketClient = socket.accept();
                System.out.println(socketClient.getInetAddress() + "; " + socketClient.getLocalSocketAddress());
                redirectClient(socketClient);
            }

        } catch (IOException ex) {
            System.out.println("Erro no servidor: " + ex.getMessage()); //printa um erro caso o servidor não possa ser aberto
        }
    }

    public void redirectClient(Socket socketClient) throws IOException {

        switch (serverList.size()) {
            case 0:
                createNewServer();
                addUserToServer(serverList.get(0), socketClient);
                break;
            case 1:
                if (serverList.get(0).getNumberUsersConnected() < 3) {
                    addUserToServer(serverList.get(0), socketClient);
                } else {
                    createNewServer();
                    addUserToServer(serverList.get(1), socketClient);
                }
                break;
            case 2:
                if (serverList.get(1).getNumberUsersConnected() < 3) {
                    addUserToServer(serverList.get(1), socketClient);
                } else {
                    createNewServer();
                    addUserToServer(serverList.get(2), socketClient);
                }
                break;
            case 3:
                int index = checkServers();
                addUserToServer(serverList.get(index), socketClient);
                break;
            default:
                throw new AssertionError();
        }

        if (serverList.isEmpty()) {
            createNewServer();
        } else if (serverList.size() == 1) {
            if (serverList.get(0).getNumberUsersConnected() < 3) {

            } else {
                createNewServer();
            }
        } else if (serverList.size() == 2) {

        }
    }

    public void createNewServer() throws IOException {
        int number = serverList.size();
        Server server = new Server(portVector[number], addressVector[number]);
        serverList.add(server);
    }

    public int checkServers() {
        int min = 3;
        int size;
        int index = -1;
        for (int i = 0; i < serverList.size(); i++) {
            size = serverList.get(i).getNumberUsersConnected();
            if (size < min) {
                min = size;
                index = i;
            }
        }
        return index;    
    }
    
    public void addUserToServer(Server server, Socket socketClient) throws IOException{
        server.addClient(socketClient);
    }

}
