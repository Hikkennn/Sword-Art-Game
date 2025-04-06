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
import object.OBJ_Rock;

/**
 *
 * @author Gambet
 */
public class MON_GreenSlime extends Entity{
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_monster;
        name = "Green Slime";
        speed = 1;
        maxLife = 10;
        life = maxLife;
        attack = 3;
        defense = 0;
        exp = 2;
        
        projectile = new OBJ_Rock(gp);
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
       getImage();
    }
    
    public void getImage(){
        up1 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/greenslime_down_2",gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/greenslime_down_1",gp.tileSize, gp.tileSize);
        
    }
    
    @Override
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

        // Optional: Random projectile shooting
        int i = new Random().nextInt(100) + 1;
        if (i > 99 && projectile.alive == false && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

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
            if (monster != null && monster.life > 0 && monster.name.equals("Green Slime")) {
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
