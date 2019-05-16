/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author roliv
 */
public class Server {

    private int port;
    private Set<UserThread> userThreads = new HashSet<>();

    public Server(int port) {
        this.port = port;
    }

    public void execute() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("O servidor está aberto na porta: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Novo usuário conectado.");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();

            }

        } catch (IOException ex) {
            System.out.println("Erro no servidor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void respond(String message, UserThread user){
        user.sendMessage(message);
    }
    
    /**
     * Quando um usuário é desconectado, remove a Thread daquele usuário do sistema.
     */
    public void removeUser(UserThread aUser) {
            userThreads.remove(aUser);
            System.out.println("O usuário " + aUser.getId() + " saiu do servidor.");
    }


}
