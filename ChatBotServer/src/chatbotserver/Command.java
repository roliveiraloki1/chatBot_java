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

        if (isCommand(command)) {
            switch (command) {
                case "\\comandos":
                    return listCommands();
                case "\\info":
                    return showInfo();
                case "\\randgame":
                    return command;
                default:
                    return "Comando não encontrado.";
            }
        }
        return command;

    }

    public static boolean isCommand(String string) {
        return string.charAt(0) == '\\';
    }
    
    public static String listCommands(){
        return "comandos disponíveis: \r"
                + "***************************************************** \r"
                + "\\comandos - Mostra a lista de comandos disponíveis. \r"
                + "***************************************************** \r"
                + "\\info - Mostra informações sobre o chatbot \r"
                + "***************************************************** \r";
    }
    
    public static String showInfo(){
        return "------------------------------------------------------------------------- \n"
                + "Este é um chatbot implementado em java, como entrega do Trabalho 1 da \n"
                + "disciplina Redes e Sistemas Distribuídos do quinto período do curço de \n"
                + "Engenharia de Software , ministrada pelo professor Rodrigo Mansilha. \n"
                + "Este trabalho foi produzido pelo aluno da disciplina Rodrigo Oliveira. \n"
                + "-------------------------------------------------------------------------";
    }

}
