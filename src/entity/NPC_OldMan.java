/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import game.GamePanel;
import java.util.Random;

/**
 *
 * @author Gambet
 */
public class NPC_OldMan extends Entity{
    
    
    public NPC_OldMan(GamePanel gp){
        super(gp);
        
        direction = "down";
         speed = 1;
         getImage();
         setDialogue();
    }
    
    public void setDialogue(){
        dialogues[0] = "Hello, idiot!";
        dialogues[1] = "You are summoned in this \ndungeon to represent and \nsave your planet.";
        dialogues[2] = "I used to be a great wizard \nto protect your planet but my \ntime has come!";
        dialogues[3] = "Good Luck to you, I will always \nbe by your side.";
        
    }
    public void getImage(){
        up1 = setup("/res/npc/oldman_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/oldman_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/oldman_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/oldman_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/oldman_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/oldman_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/oldman_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/oldman_right_2",gp.tileSize, gp.tileSize);
        
        
    }
    
    public void setAction(){
        
        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction= "down";
            }
            if(i > 50 && i <=75){
                direction = "left";
            }
            if(i > 75 && i<=100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    
    public void speak(){
        super.speak();
    }
}
