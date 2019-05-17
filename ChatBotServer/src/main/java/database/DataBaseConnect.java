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


    public static List<Game> getGameByGenre(GenreEnum genre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Game> gameList = new ArrayList();
        try {

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, "root", "");
            //STEP 4: Execute a query
            String sql = "SELECT * FROM Games WHERE genre = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, genre.getCode());
            ResultSet rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                Game game = new Game();
                game.setName(rs.getString("name"));
                game.setGameplay(GameplayEnum.values()[rs.getInt("gameplay")]);
                game.setGenre(GenreEnum.values()[rs.getInt("genre")]);
                game.setType(TypeEnum.values()[rs.getInt("type")]);
                game.setScore(rs.getFloat("score"));
                gameList.add(game);
                
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return gameList;
    }//end main
    
    public static Game getRandomGame() {
        Connection conn = null;
        PreparedStatement stmt = null;
        Game game = new Game();
        try {
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, "root", "");
            //STEP 4: Execute a query
            String sql = "SELECT * FROM games ORDER BY RAND() LIMIT 1";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name               
                game.setName(rs.getString("name"));
                game.setGameplay(GameplayEnum.values()[rs.getInt("gameplay")]);
                game.setGenre(GenreEnum.values()[rs.getInt("genre")]);
                game.setType(TypeEnum.values()[rs.getInt("type")]);
                game.setScore(rs.getFloat("score"));          
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return game;
    }//end main

}
