/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import entity.Entity;
import game.GamePanel;
import java.util.Random;
import object.OBJ_Boots;
import object.OBJ_Coin;
import object.OBJ_Key;
import object.OBJ_Potion_Red;

/**
 *
 * @author Gambet
 */
public class MON_Ogre extends Entity{
    GamePanel gp;
    private int attackCooldown = 0;
    private boolean isAttacking =false;
    public MON_Ogre(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_monster;
        name = "Ogre";
        speed = 1;
        maxLife = 10;
        life = maxLife;
        attack = 5;
        defense = 2;
        exp = 5;
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        getImageAttack();
    }
    
    public void getImage(){
        up1 = setup("/res/monster/orc_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/orc_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/orc_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/orc_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/orc_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/orc_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/orc_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/orc_right_2",gp.tileSize, gp.tileSize);
        
    }
    public void getImageAttack(){
        attackUp1 = setup("/res/monster/orc_attack_up_1",gp.tileSize, gp.tileSize);
        attackUp2 = setup("/res/monster/orc_attack_up_2",gp.tileSize, gp.tileSize);
        attackDown1 = setup("/res/monster/orc_attack_down_1",gp.tileSize, gp.tileSize);
        attackDown2 = setup("/res/monster/orc_attack_down_2",gp.tileSize, gp.tileSize);
        attackLeft1 = setup("/res/monster/orc_attack_left_1",gp.tileSize, gp.tileSize);
        attackLeft2 = setup("/res/monster/orc_attack_left_2",gp.tileSize, gp.tileSize);
        attackRight1 = setup("/res/monster/orc_attack_right_1",gp.tileSize, gp.tileSize);
        attackRight2 = setup("/res/monster/orc_attack_right_2",gp.tileSize, gp.tileSize);
        
    }
    
    public void setAction() {
        int dx = gp.player.worldX - this.worldX;
        int dy = gp.player.worldY - this.worldY;
        int tileDistance = Math.abs(dx) + Math.abs(dy);

        int aggroRange = 5 * gp.tileSize; // Distance in pixels

        if (tileDistance < aggroRange) {
            // Chase the player
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    direction = "right";
                } else {
                    direction = "left";
                }
            } else {
                if (dy > 0) {
                    direction = "down";
                } else {
                    direction = "up";
                }
            }
            if(tileDistance < 2 * gp.tileSize && attackCooldown == 0){
                isAttacking = true;
                
                attackCooldown = 60;
            }
        } else {
            
            actionLockCounter++;
            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) direction = "up";
                else if (i <= 50) direction = "down";
                else if (i <= 75) direction = "left";
                else direction = "right";

                actionLockCounter = 0;
            }
        }

        
    }
    
    
//    public void update(){
//       
//        
//        if(attackCooldown > 0){
//            attackCooldown--;
//        }
//        
//        if(isAttacking == true){
//            switch(direction){
//                case "up":
//                    if(spriteCounter %20 == 0){
//                        if(spriteNum == 1){
//                            image = attackUp1;
//                        }else{
//                            image = attackUp2;
//                        }
//                        spriteNum = (spriteNum == 1)? 2: 1;
//                    }
//                    break;
//                
//                case "down":
//                    if(spriteCounter %20 == 0){
//                        if(spriteNum == 1){
//                            image = attackDown1;
//                        }else{
//                            image = attackDown2;
//                        }
//                        spriteNum = (spriteNum == 1)? 2: 1;
//                    }
//                    break;
//                
//                case "left":
//                    if(spriteCounter %20 == 0){
//                        if(spriteNum == 1){
//                            image = attackLeft1;
//                        }else{
//                            image = attackLeft2;
//                        }
//                        spriteNum = (spriteNum == 1)? 2: 1;
//                    }
//                    break;
//                
//                case "right":
//                    if(spriteCounter %20 == 0){
//                        if(spriteNum == 1){
//                            image = attackRight1;
//                        }else{
//                            image = attackRight2;
//                        }
//                        spriteNum = (spriteNum == 1)? 2: 1;
//                    }
//                    break;
//            }
//        }
//    }

    public void checkDrop(){
        int i = new Random().nextInt(100)+1;
        
        //set the monster drop
        if(i < 50){
            dropItem(new OBJ_Coin(gp));
        }
        if(i >= 50 && i < 55){
            dropItem(new OBJ_Boots(gp));
        }
        if(i >= 55 && i< 70){
            dropItem(new OBJ_Potion_Red(gp));
        }
        
        checkStageCompletion();
    }
    public void checkStageCompletion() {
        boolean allMonstersDefeated = true;

        // Check all monsters in the current stage
        for (Entity monster : gp.monster) {
            if (monster != null && monster.life > 0 && monster.name.equals("Ogre")) {
                allMonstersDefeated = false;
                break;
            }
        }

        // If all monsters are defeated, drop a key
        if (allMonstersDefeated) {
            dropItem(new OBJ_Key(gp));  
            gp.ui.addMessage("A Key has dropped!");  
        }
    }
    
}
