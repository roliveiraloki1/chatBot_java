/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enums;

import lombok.Getter;

/**
 *
 * @author roliv
 */
@Getter
public enum GenreEnum {
    
    ACTION(0, "Ação"),
    ADVENTURE(1, "Aventura"),
    SPORTS(2, "Esportes"),
    RPG(3, "RPG"),
    CASUAL(4, "Casual"),
    INDIE(5, "Indie"),
    RACING(6, "Corrida"),
    SIMULATION(7, "Simulação"),
    STRATEGY(8, "Estratégia");
    
    private int code;
    private final String description;

    public String getDescription() {
        return this.description;
    }

    private GenreEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode(){
        return this.code;
    }
    
    
}
