/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import monster.MON_Ogre;
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
        int i = 0;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = gp.tileSize*7;
        gp.obj[i].worldY = gp.tileSize*19;
        i++;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = gp.tileSize*8;
        gp.obj[i].worldY = gp.tileSize*19;
        i++;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = gp.tileSize*18;
        gp.obj[i].worldY = gp.tileSize*14;
        i++;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = gp.tileSize*18;
        gp.obj[i].worldY = gp.tileSize*15;
        i++;
        
        
        
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*4;
        gp.npc[0].worldY = gp.tileSize*9;
    }
    
    public void setMonster(){
        
        //Min and Max x = [9 - 24]
        //Min and Max y = [8- 18]
        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*15;
        gp.monster[i].worldY = gp.tileSize*16;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*14;
        gp.monster[i].worldY = gp.tileSize*16;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*13;
        gp.monster[i].worldY = gp.tileSize*17;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*15;
        gp.monster[i].worldY = gp.tileSize*13;
        i++;
        gp.monster[i] = new MON_Ogre(gp);
        gp.monster[i].worldX = gp.tileSize*23;
        gp.monster[i].worldY = gp.tileSize*9;
        i++;
        gp.monster[i] = new MON_Ogre(gp);
        gp.monster[i].worldX = gp.tileSize*26;
        gp.monster[i].worldY = gp.tileSize*12;
        i++;
        gp.monster[i] = new MON_Ogre(gp);
        gp.monster[i].worldX = gp.tileSize*30;
        gp.monster[i].worldY = gp.tileSize*13;
        i++;
        gp.monster[i] = new MON_Ogre(gp);
        gp.monster[i].worldX = gp.tileSize*27;
        gp.monster[i].worldY = gp.tileSize*14;
        i++;
       
    }
    
    
}
