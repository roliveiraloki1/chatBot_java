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
 * essa classe tem a funcionalidade de analisar o que o cliente digitou e
 * responder de acordo.
 *
 * @author roliv
 */
public class Command {

    /**
     * esse é o método que seleciona a devida resposta ao cliente utilizando um
     * switch
     *
     * @param command comando recebido do cliente
     * @return retorna devida resposta para o cliente
     */
    public static String executeCommand(String command) {
        //aqui é feito uma verificação se o comando enviado é de fato um comando ou apenas uma string comum.
        if (isCommand(command)) {
            String[] splitedCommand = command.split(" "); //caso o comando possua um parâmetro, aqui é divido o comando do parâmetro para ser trato devidamente            
            switch (splitedCommand[0]) {
                case "\\comandos":
                    return listCommands(); //chama o método que lista os comandos
                case "\\info":
                    return showInfo(); //chama o método que retorna as informações sobre o sistema
                case "\\randgame":
                    return rndGame(); //chama o método que retorna um jogo randomico
                case "\\findgame":
                    if (hasParameter(splitedCommand)) { //aqui é verificado se o comando possui um parâmetro
                        return gameByGenre(splitedCommand[1]); //chama o metodo que retorna o jogo de acordo com o gênero
                    } else {
                        return "Este comando necessita de um parâmetro!"; //caso o comando necessite de um parâmetro e não, retorna essa string.
                    }
                default:
                    return "Comando não encontrado."; //caso o comando não exista, essa string é retornada
            }
        }
        return command; //caso o que o cliente digitar não seja um comando, o sistema simplesmente printa o que foi digitado de volta.
    }

    /**
     * verifica se o que foi digitado pelo cliente é um comando
     *
     * @param string
     * @return retorna se é true ou false
     */
    public static boolean isCommand(String string) {
        if (string.equals("")) { //aqui é verificado se o que foi enviado pelo cliente é uma string vazia.
            return false;
        }
        return string.charAt(0) == '\\';
    }

    /**
     * esse metodo simplesmente retorna a string com os comandos disponíveis
     *
     * @return retorna a string
     */
    public static String listCommands() {
        return "comandos disponíveis: \r"
                + "***************************************************** \r"
                + "\\comandos - Mostra a lista de comandos disponíveis. \r"
                + "***************************************************** \r"
                + "\\info - Mostra informações sobre o chatbot \r"
                + "***************************************************** \r"
                + "\\randgame - Mostra um jogo selecionado randomicamente \r"
                + "***************************************************** \r"
                + "\\findgame - Encontra um jogo de acordo com um gênero \r"
                + "informado como parâmetro \r"
                + "***************************************************** \r";
    }

    /**
     * esse metodo simplesmente retorna a string com as informações sobre o
     * sistema
     *
     * @return retorna a string
     */
    public static String showInfo() {
        return "------------------------------------------------------------------------- \n"
                + "Este é um chatbot implementado em java, como entrega do Trabalho 1 da \n"
                + "disciplina Redes e Sistemas Distribuídos do quinto período do curço de \n"
                + "Engenharia de Software , ministrada pelo professor Rodrigo Mansilha. \n"
                + "Este trabalho foi produzido pelo aluno da disciplina Rodrigo Oliveira. \n"
                + "-------------------------------------------------------------------------";
    }

    /**
     * encontra e retorna um jogo randomico
     *
     * @return retorna as informações sobre o jogo encontrado
     */
    public static String rndGame() {
        Game game = DataBaseConnect.getRandomGame(); //aqui é retornado do banco de dados um jogo randomico
        if (game == null) { //verifica se o objeto "game" é nulo
            return "Não existe jogos cadastrados";
        }
        return game.toString();
    }

    /**
     * encontra e retorna uma lista de jogos de acordo com o parametro passado
     *
     * @param genre genero do jogo
     * @return retorna a lista de jogos para o cliente
     */
    public static String gameByGenre(String genre) {
        GenreEnum genreEnum;

        if (genre == null || genre.trim().equals("")) { //verifica se o parametro foi passado
            return "Parâmetro inválido!";
        }

        try { //se o parametro passado não existe no sistema, retorna a msg
            genreEnum = GenreEnum.valueOf(genre.toUpperCase()); //transforma o parâmetro passado no formato dos enums utilizados

        } catch (Exception e) {
            return "Gênero não cadastrado!";
        }

        List<Game> gameList = DataBaseConnect.getGameByGenre(genreEnum); //aqui é retornado do banco de dados uma lista de jogos que se encaixam no parâmetro passado

        if (gameList.isEmpty()) {
            return "Ainda não existe nenhum jogo cadastrado desse gênero";
        }
        String resp = "";

        for (Game game : gameList) { // cria uma string com os jogos encontrados
            resp += game.toString();
        }
        return resp;
    }

    /**
     * faz verificação se o comando possui um parâmetro
     *
     * @param splited recebe um array de string
     * @return true ou false
     */
    public static boolean hasParameter(String[] splited) {
        return (splited.length > 1); //
    }
}
