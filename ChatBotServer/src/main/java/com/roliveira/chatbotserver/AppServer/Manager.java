/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotserver.AppServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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

    /**
     * construtor que seta o hostname e port que o cliente se conecta
     *
     * @throws java.net.UnknownHostException necessário quando se utiliza a
     * classe Inet4Address
     * @throws java.io.IOException necessário quando se utiliza a classe
     * InetAddress
     */
    public Manager() throws UnknownHostException, IOException {
        this.socket = new ServerSocket(1234);
        this.serverList = new ArrayList<Server>();
        this.addressVector = new InetAddress[]{Inet4Address.getByName("127.0.0.100"), //aqui é criado um vetor com os 3 endereços de ip
            Inet4Address.getByName("127.0.0.102"), Inet4Address.getByName("127.0.0.103")};//que serão abertos os servidores
    }

    /**
     * método que executa o gerenciador
     */
    public void run() {
        try {

            System.out.println("O Gerenciador está aberto na porta: 1234");

            while (true) {//aqui é feito o loop do gerenciador, enquanto ele estiver ligado ele fica recebendo novos usuários
                Socket socketClient = socket.accept(); //aceita novo usuário no novo socket
                redirectClient(socketClient); //chama o método que irá redirecior o cliente para o servidor
            }

        } catch (IOException ex) {
            System.out.println("Erro no servidor: " + ex.getMessage()); //printa um erro caso o servidor não possa ser aberto
        }
    }

    /**
     *
     * @param socketClient no cliente
     * @throws IOException necessário quando se utiliza a classe InetAddress
     */
    public void redirectClient(Socket socketClient) throws IOException {

        switch (serverList.size()) { //inicia um switch para determinar se irá abrir novo servidor, se irá alocar para servidor já aberto, ou se estão cheios todos os servidores
            case 0: //caso não tenha nenhum servidor na lista de servidores
                createNewServer();//cria novo servidor
                addUserToServer(serverList.get(0), socketClient);//redireciona cliente para o servidor
                break;
            case 1://caso tenha apenas um servidor na lista
                if (serverList.get(0).getNumberUsersConnected() < 3) {//verifica se o servidor aberto não está cheio
                    addUserToServer(serverList.get(0), socketClient);//redireciona cliente para o servidor
                } else {//caso esteja
                    createNewServer();//cria novo servidor
                    addUserToServer(serverList.get(1), socketClient);//redireciona cliente para o servidor
                }
                break;
            case 2:
                if (serverList.get(0).getNumberUsersConnected() < 3) {//verifica se o primeiro servidor aberto não está cheio
                    addUserToServer(serverList.get(0), socketClient);//redireciona cliente para o servidor
                } else if (serverList.get(1).getNumberUsersConnected() < 3) {//verifica se o segundo servidor aberto não está cheio
                    addUserToServer(serverList.get(1), socketClient);//redireciona cliente para o servidor
                } else {//caso o segundo também esteja cheio
                    createNewServer();//cria novo servidor
                    addUserToServer(serverList.get(2), socketClient);//redireciona cliente para o servidor
                }
                break;
            case 3:
                int index = checkServers();//verifica qual servidor tem menos clientes conectados
                if (index == -1) {//se o retorno for -1 significa que todos os servidores estão cheios
                    OutputStream sender = socketClient.getOutputStream();//cria output até o cliente
                    PrintWriter writer = new PrintWriter(sender, true); //cria um escritor para enviar msg 
                    writer.println("-1");//manda a msg pra o cliente que informa que os servidores estão cheios 
                    break;
                }
                addUserToServer(serverList.get(index), socketClient);//redireciona cliente para o servidor
                break;
            default:
                throw new AssertionError();
        }

    }

    /**
     * Método que cria um novo servidor
     *
     * @throws IOException necessário quando se utiliza a classe InetAddress
     */
    public void createNewServer() throws IOException {
        int number = serverList.size();
        Server server = new Server(portVector[number], addressVector[number]);
        server.start();
        serverList.add(server);
    }

    /**
     * Método que verifica qual servidor de lista de servidores tem o menor
     * numero de clientes
     *
     * @return retorna o index da lista
     */
    public int checkServers() {
        int min = 3;
        int index = -1;
        for (int i = 0; i < serverList.size(); i++) {
            if (serverList.get(i).getNumberUsersConnected() < min) {
                min = serverList.get(i).getNumberUsersConnected();
                index = i;
            }
        }
        return index;
    }

    /**
     *
     * @param server Servidor que o novo cliente será redirecionado
     * @param socketClient Socket do cliente
     * @throws IOException necessário quando se utiliza a classe InetAddress
     */
    public void addUserToServer(Server server, Socket socketClient) throws IOException {

        OutputStream sender = socketClient.getOutputStream();//cria output até o cliente
        PrintWriter writer = new PrintWriter(sender, true);//cria um escritor para enviar dados 

        writer.println(server.getServerSocket().getInetAddress().getHostAddress());//manda para o cliente o ip que ele irá se conectar
        writer.println(String.valueOf(server.getServerSocket().getLocalPort()));//manda para o cliente a porta que ele irá se conectar
    }

}
