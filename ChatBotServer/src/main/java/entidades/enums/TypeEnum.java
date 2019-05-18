/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enums;

/**
 * classe enum para tipos de games
 * @author roliv
 */
public enum TypeEnum {
    
    SINGLEPLAYER(0, "Um Jogador"),
    MULTIPLAYER(1, "Vários Jogadores"),
    COOP(2, "Cooperativo"),
    LOCAL_COOP(3, "Cooperativo Local"),
    MMO(4, "Multi Massivo Online"),
    MOBA(5, "MOBA");
    
    private final int code;
    private final String description;

    public String getDescription() {
        return this.description;
    }
    
    /**
     * seta um numerador e uma string para cada enum
     * @param code numerado do enum
     * @param description descrição do enum
     */
    private TypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode(){
        return this.code;
    }
    
}
