/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roliveira.chatbotserver.AppServer;

import database.DataBaseConnect;
import entidades.Game;
import entidades.enums.GenreEnum;
import java.util.List;

/**
 *
 * @author roliv
 */
public class Command {

    public static String executeCommand(String command) {

        if (isCommand(command)) {
            String[] splitedCommand = command.split(" ");
            
            switch (splitedCommand[0]) {
                case "\\comandos":
                    return listCommands();
                case "\\info":
                    return showInfo();
                case "\\randgame":
                    return rndGame();
                case "\\findgame":
                    if(hasParameter(splitedCommand)){
                        return gameByGenre(splitedCommand[1]);
                    }else{
                        return "Este comando necessita de um parâmetro!";
                    }                    
                default:
                    return "Comando não encontrado.";
            }
        }
        return command;
    }

    public static boolean isCommand(String string) {
        if (string.equals("")) {
            return false;
        }
        return string.charAt(0) == '\\';
    }

    public static String listCommands() {
        return "comandos disponíveis: \r"
                + "***************************************************** \r"
                + "\\comandos - Mostra a lista de comandos disponíveis. \r"
                + "***************************************************** \r"
                + "\\info - Mostra informações sobre o chatbot \r"
                + "***************************************************** \r";
    }

    public static String showInfo() {
        return "------------------------------------------------------------------------- \n"
                + "Este é um chatbot implementado em java, como entrega do Trabalho 1 da \n"
                + "disciplina Redes e Sistemas Distribuídos do quinto período do curço de \n"
                + "Engenharia de Software , ministrada pelo professor Rodrigo Mansilha. \n"
                + "Este trabalho foi produzido pelo aluno da disciplina Rodrigo Oliveira. \n"
                + "-------------------------------------------------------------------------";
    }

    public static String rndGame() {
        Game game = DataBaseConnect.getRandomGame();
        if (game == null) {
            return "Não existe jogos cadastrados";
        }
        return game.toString();
    }

    public static String gameByGenre(String genre) {
        if (genre == null || genre.trim().equals("")) {
            return "Parâmetro inválido!";
        }
        
        GenreEnum genreEnum = GenreEnum.valueOf(genre.toUpperCase());
        
        if (genreEnum == null) {
            return "Gênero não cadastrado!";
        }
        
        List<Game> gameList = DataBaseConnect.getGameByGenre(genreEnum);
        String resp = "";
        
        for (Game game : gameList) {
            resp += game.toString();
        }
        return resp;
    }
    
    public static boolean hasParameter(String[] splited){
        return (splited.length > 1);
    }
}
