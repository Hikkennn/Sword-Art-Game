/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Coin;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;

/**
 *
 * @author Gambet
 */
public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
        
    }
    
    public void setObject(){
        
        
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*4;
        gp.npc[0].worldY = gp.tileSize*9;
    }
    
    public void setMonster(){
        
        //Min and Max x = [9 - 24]
        //Min and Max y = [8- 18]
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize*15;
        gp.monster[0].worldY = gp.tileSize*16;
        
        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize*14;
        gp.monster[1].worldY = gp.tileSize*16;
        
        gp.monster[2] = new MON_GreenSlime(gp);
        gp.monster[2].worldX = gp.tileSize*13;
        gp.monster[2].worldY = gp.tileSize*17;
        
        gp.monster[3] = new MON_GreenSlime(gp);
        gp.monster[3].worldX = gp.tileSize*15;
        gp.monster[3].worldY = gp.tileSize*13;
        
        gp.monster[4] = new MON_GreenSlime(gp);
        gp.monster[4].worldX = gp.tileSize*20;
        gp.monster[4].worldY = gp.tileSize*14;
        
        gp.monster[4] = new MON_GreenSlime(gp);
        gp.monster[4].worldX = gp.tileSize*22;
        gp.monster[4].worldY = gp.tileSize*16;
    }
    
    
}
