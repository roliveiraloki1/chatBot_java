/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enums;

/**
 *
 * @author roliv
 */
public enum GameplayEnum {
    
    FIRSTPERSON(0, "Primeira Pessoa"),
    THIRDPERSON(1, "Treceira Pessoa"),
    ISOMETRIC(2, "Isométrico"),
    SIDESCROLLER(3, "Plataforma"),
    G2D(4, "Gráficos 2D"),
    G3D(5, "Gráficos 3D"),
    VR(6, "Realidade Virtual");
    
    private int code;
    private final String description;

    public String getDescription() {
        return this.description;
    }

    private GameplayEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode(){
        return this.code;
    }
    
}
