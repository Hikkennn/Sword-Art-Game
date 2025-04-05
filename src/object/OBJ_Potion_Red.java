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
public class OBJ_Potion_Red extends Entity{
    GamePanel gp;
   
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setup("/res/objects/potion_red",gp.tileSize,gp.tileSize);
        description = name+"\nHeals your life by "+value+".";
    }

    public void use(Entity entity){
        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
    }
   
    
}
