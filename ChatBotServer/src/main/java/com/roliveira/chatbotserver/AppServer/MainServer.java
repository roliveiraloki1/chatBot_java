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
public class MainServer {

//    private static List<Server> serverList = new ArrayList<Server>();
//    private static Server mainServer;
//    private static String[] ipList;
//    private static int[] portList;
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws UnknownHostException, IOException {
//
//        InetAddress adrs = InetAddress.getByName("127.0.0.1"); //localhost
//        mainServer = new Server(1234, adrs);
//        mainServer.setNome("Gerenciador");
//        ipList[0] = "127.0.0.2";
//        ipList[1] = "127.0.0.3";
//        ipList[2] = "127.0.0.4";
//        portList[0] = 1111;
//        portList[1] = 2222;
//        portList[2] = 3333;
//
//        //Aguarda conexões de clientes e aloca-os
//        do {
//            ServerSocket serverSocket = new ServerSocket(1234, 15, adrs);
//            Socket socket = serverSocket.accept();
//            UserThread newUser = new UserThread(socket, mainServer);
//
//            OutputStream sender = newUser.getSocket().getOutputStream();
//            PrintWriter writer = new PrintWriter(sender, true);
//            
//            execute(writer, portList, ipList);//Passa informações do servidor ao qual será alocado para o Cliente
//
//            
//        } while (true);
//    }
//
//    public static String execute(PrintWriter writer, int[] portList, String[] ipList) throws UnknownHostException {
//        if (serverList.isEmpty()) {
//            Server servidor1 = new Server(portList[0], InetAddress.getByName(ipList[0]));
//            serverList.add(servidor1);
//            writer.println(servidor1.getAddress());
//            writer.println(servidor1.getPort());
//            return "Novo Servidor Aberto";
//        } else {
//            for (int i = 0; i <= 3; i++) {
//                if (serverList.size() > i && serverList.get(i).getCounter() < 3) {
//                    writer.println(serverList.get(i).getAddress());
//                    writer.println(serverList.get(i).getPort());
//                    return "Novo cliente conectado ao novo servidor " + i;
//                }
//            }
//            for (int i = 1; i <= 3; i++) {
//                if (serverList.size() < i) {
//                    if (i == 2) {
//                        Server servidor2 = new Server(portList[i - 1], InetAddress.getByName(ipList[i - 1]));
//                        serverList.add(servidor2);
//                        writer.println(servidor2.getAddress());
//                        writer.println(servidor2.getPort());
//                        return "Novo cliente conectado ao servidor 2";
//                    }
//                    if (i == 3) {
//                        Server servidor3 = new Server(portList[i - 1], InetAddress.getByName(ipList[i - 1]));
//                        serverList.add(servidor3);
//                        writer.println(servidor3.getAddress());
//                        writer.println(servidor3.getPort());
//                        return "Novo cliente conectado ao servidor 3";
//                    }
//                }
//            }
//        }
//        return "Servidores Cheios";
//    };
}
