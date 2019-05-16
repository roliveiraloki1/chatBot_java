/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotserver;

/**
 *
 * @author roliv
 */
public class Command {

    public static String executeCommand(String command) {

        String aux;
        if (isCommand(command)) {
            switch (command) {
                case "comando":
                    System.out.println("foi aqui");
                    return command;
                case "\\comandos":
                    System.out.println("foi aqui tb");
                    return command;
                default:
                    aux = "Comando não encontrado.";
                    return aux;
            }
        }
        return "oi, td bem?";

    }

    public static boolean isCommand(String string) {

        return string.charAt(0) == '\\';
    }

}
