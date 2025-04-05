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
public class OBJ_Sword_Normal extends Entity{
    
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Normal Sword";
        down1 = setup("/res/objects/sword_normal", gp.tileSize,gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name+"]\nAn old sword";
    }
    
    
}
