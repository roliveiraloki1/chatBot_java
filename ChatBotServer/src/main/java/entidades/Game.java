/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import entidades.enums.GameplayEnum;
import entidades.enums.GenreEnum;
import entidades.enums.TypeEnum;

/**
 *
 * @author roliv
 */
public class Game {

    private String name;
    private float score;
    private GameplayEnum gameplay;
    private GenreEnum genre;
    private TypeEnum type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public GameplayEnum getGameplay() {
        return gameplay;
    }

    public void setGameplay(GameplayEnum gameplay) {
        this.gameplay = gameplay;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "-------------------------------------------------------------------\n"
                + "Nome: " + name + "\nMetaScore: " + score + "\n"
                + "Estilo: " + gameplay.getDescription() + " Tipo: " + type.getDescription() + "\n"
                + "Gênero: " + genre.getDescription()
                + "\n-------------------------------------------------------------------\n";
    }

}
