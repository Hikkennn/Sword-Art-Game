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
public class OBJ_Axe extends Entity{
    
    public OBJ_Axe(GamePanel gp) {
        super(gp);
        
        type = type_axe;
        name = "Woodcutter's Axe";
        down1 = setup("/res/objects/axe",gp.tileSize,gp.tileSize);
        attackArea.width = 30;
        attackArea.height = 30;
        attackValue = 2;
        description ="["+name+"\n shorter range but higher \nhas higher attack!";
    }
    
}
