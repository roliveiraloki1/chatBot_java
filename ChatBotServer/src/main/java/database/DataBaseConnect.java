/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entidades.Game;
import entidades.enums.GameplayEnum;
import entidades.enums.GenreEnum;
import entidades.enums.TypeEnum;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roliv
 */
public class DataBaseConnect {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/bot_server?useTimezone=true&serverTimezone=UTC";

    /**
     * Busca na base de dados todos os jogos que possuem o determinado genero
     *
     * @param genre recebe um enum
     * @return retorna lista de obj Games com os dados encontrados
     */
    public static List<Game> getGameByGenre(GenreEnum genre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Game> gameList = new ArrayList();
        try {

            conn = DriverManager.getConnection(DB_URL, "root", ""); //Abre a conexão           
            String sql = "SELECT * FROM Games WHERE genre = ?"; //Executa a linha de Query
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, genre.getCode());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) { //Extrai os dados do set de resultados               
                Game game = new Game();
                game.setName(rs.getString("name")); //recupera por nome da coluna
                game.setGameplay(GameplayEnum.values()[rs.getInt("gameplay")]);
                game.setGenre(GenreEnum.values()[rs.getInt("genre")]);
                game.setType(TypeEnum.values()[rs.getInt("type")]);
                game.setScore(rs.getFloat("score"));
                gameList.add(game);

            }

            rs.close(); //fecha as conexões abertas
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { //Bloqueia o acesso aos recursos           
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return gameList;
    }

    /**
     * Método que busca um jogo randomico na base de dados
     *
     * @return retorna o jogo encontrado
     */
    public static Game getRandomGame() {
        Connection conn = null;
        PreparedStatement stmt = null;
        Game game = new Game();
        try {
            conn = DriverManager.getConnection(DB_URL, "root", "");
            String sql = "SELECT * FROM games ORDER BY RAND() LIMIT 1";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                game.setName(rs.getString("name"));
                game.setGameplay(GameplayEnum.values()[rs.getInt("gameplay")]);
                game.setGenre(GenreEnum.values()[rs.getInt("genre")]);
                game.setType(TypeEnum.values()[rs.getInt("type")]);
                game.setScore(rs.getFloat("score"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return game;
    }

}
