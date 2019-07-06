/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotserver.AppServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
public class ChatBotServer {

    private static List<Server> serverList = new ArrayList<Server>();
    private static Server mainServer;
    //private static List<String> ipList = new ArrayList<String>();
    private static final String[] ipList = {"127.0.0.210", "127.0.0.215", "127.0.0.220"};
    private static final int[] portList = {1111, 2222, 3333};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {

        InetAddress adrs = InetAddress.getByName("127.0.0.1"); //localhost
        mainServer = new Server(1234, adrs);
        mainServer.setNome("Gerenciador");

        //Aguarda conexões de clientes e aloca-os
        do {
            Socket socket = mainServer.getServer().accept();
            //UserThread newUser = new UserThread(socket, mainServer);
            OutputStream sender = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(sender, true);
            //PrintWriter writer = mainServer.setClient(mainServer);

            execute(writer, portList, ipList);//Passa informações do servidor ao qual será alocado para o Cliente            
        } while (true);
    }

    public static String execute(PrintWriter writer, int[] portList, String[] ipList) throws UnknownHostException, IOException {

        if (serverList.isEmpty()) {         
            Server servidor1 = new Server(portList[0], InetAddress.getByName(ipList[0]));
            System.out.println("chegou aqui");
            serverList.add(servidor1);
            serverList.get(0).run();
            
            writer.println(InetAddress.getByName(ipList[0]));
            writer.println(portList[0]);
            return "Novo Servidor Aberto";
        } else {
            for (int i = 0; i <= 3; i++) {
                if (serverList.size() > i && serverList.get(i).getCounter() < 3) {
                    writer.println(serverList.get(i).getAddress());
                    writer.println(serverList.get(i).getPort());
                    return "Novo cliente conectado ao novo servidor " + i;
                }
            }
            for (int i = 1; i <= 3; i++) {
                if (serverList.size() < i) {
                    if (i == 2) {
                        Server servidor2 = new Server(portList[i - 1], InetAddress.getByName(ipList[i - 1]));
                        serverList.add(servidor2);
                        serverList.get(1).run();
                        writer.println(servidor2.getAddress());
                        writer.println(servidor2.getPort());
                        return "Novo cliente conectado ao servidor 2";
                    }
                    if (i == 3) {
                        Server servidor3 = new Server(portList[i - 1], InetAddress.getByName(ipList[i - 1]));
                        serverList.add(servidor3);
                        serverList.get(2).run();
                        writer.println(servidor3.getAddress());
                        writer.println(servidor3.getPort());
                        return "Novo cliente conectado ao servidor 3";
                    }
                }
            }
        }
        return "Servidores Cheios!";
    }
}
