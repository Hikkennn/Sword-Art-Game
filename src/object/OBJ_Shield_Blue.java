/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import game.GamePanel;

/**
 *
 * @author Gambet
 */
public class OBJ_Shield_Blue extends Entity{
    
    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);
        
        type= type_shield;
        name = "Blue Shield";
        down1 = setup("/res/objects/shield_blue", gp.tileSize,gp.tileSize);
        defenseValue = 2;
        description = "[" + name+"]\nA shiny blue shield\nDefense " + defenseValue;
    }
    
    
}
