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
public class OBJ_Coin extends Entity{
    GamePanel gp;
    public OBJ_Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_pickupOnly;
        name = "Bronze Coin";
        value = 1;
        down1 = setup("/res/objects/coin_bronze",gp.tileSize/2,gp.tileSize/2);
    }
    public void use(Entity entity){
        
        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
    } 
}
