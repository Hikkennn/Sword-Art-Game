/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import game.GamePanel;
import game.KeyHandler;
import game.UtilityTool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import java.text.DecimalFormat;

/**
 *
 * @author Andre Policios
 */
public class Player extends Entity{
    
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCOunter =0;
    int counterCooldown;
    boolean moving = false;
    int counter2 = 0;
    
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp);
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 25;
        solidArea.height = 25;
        
//        attackArea.width = 36;
//        attackArea.height = 36;
        
        
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize * 1;
        worldY = gp.tileSize * 8;
        direction = "down";
        
        //Player Status
        level = 1;
        maxLife = 100;
        life = maxLife;
        mana = 50;
        maxMana = 50;
        counterRegen = 0;
        speed = 4;
        strength = 10;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        counterCooldown = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }
    
    public void setDefaultPostions(){
        worldX = gp.tileSize * 1;
        worldY = gp.tileSize * 8;
        direction = "down";
        speed = 4;
    }
    public void restorLifeAndMana(){
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }
    
    
    public void setItems(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength* currentWeapon.attackValue;
        
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }
    private void getPlayerImage(){
        
        up1 = setup("/res/player/boy_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/boy_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/boy_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/boy_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/boy_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/boy_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/boy_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/boy_right_2",gp.tileSize, gp.tileSize);
        
        
    }
    
    public void getPlayerAttackImage(){
        attackUp1 = setup("/res/player/boy_attack_up_1",gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/player/boy_attack_up_2",gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/res/player/boy_attack_down_1",gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/player/boy_attack_down_2",gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/player/boy_attack_Left_1",gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/player/boy_attack_Left_2",gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/res/player/boy_attack_Right_1",gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/player/boy_attack_Right_2",gp.tileSize*2, gp.tileSize);
    }
    
    
    
    public void update(){
        
        if(attacking == true){
            attacking();
            
        }
        
        else if(keyH.upPressed == true || keyH.downPressed == true || 
           keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){
            
            if(keyH.upPressed){
                direction = "up";
            }
            if(keyH.downPressed){
                direction = "down";
            }
            if(keyH.leftPressed){
                direction = "left";
            } 
            if(keyH.rightPressed){
                direction = "right";
            }
        
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //CheckOject Collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            //check npc Collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            
            //If collision is false, player can move
            if(collisionOn == false & keyH.enterPressed == false){
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            
            
            spriteCounter++;
            if(spriteCounter > 12){ 
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }   
                spriteCounter = 0;
            }
//            spriteCounter++;
//            if (spriteCounter > 10) {
//                spriteNum = (spriteNum % 6) + 1;
//                spriteCounter = 0;
//            }
//
        }else{
            counter2++;
            if(counter2 == 12){
                spriteNum = 1;
                counter2 = 0;
            }
        }
        if (gp.keyH.shotKeyPressed && shotAvailableCounter == 30 && projectile.alive == false 
                && projectile.haveResource(this) == true) {
            
                projectile.set(worldX, worldY, direction, true, this);
                projectile.subtractResource(this);
                gp.projectileList.add(projectile);
                
                gp.playSE(10);
                
                      
                    
             
        }
        
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        
        hpRegeneration();
        manaRegeneration();
        
        if(life <= 0){
            gp.gameState = gp.gameOverState;
        }
    }
    
    
    
    public void attacking(){
        spriteCounter++;
        
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
            
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            //AdjustPlayer's WorldX/Y for the attackArea
            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case"down": worldY += attackArea.height; break;
                case"left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
                }
                //attackArea becomes solidArea
                solidArea.width = attackArea.width;
                solidArea.height = attackArea.height;
                
                //Chec monster Collision with the updated worldX, worldY and solidArea
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                damageMonster(monsterIndex, attack);
                
                worldX = currentWorldX;
                worldY = currentWorldY;
                solidArea.width = solidAreaWidth;
                solidArea.height = solidAreaHeight;
            
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
        
        
    }
    public void pickUpObject(int i){
        Random rand = new Random();
        
        if(i != 999){
            String objectName = gp.obj[i].name;
            
            if(objectName.equals("Door")){
                if(hasKey> 0){
                    gp.obj[i] = null;
                    hasKey--;
                    if(objectName.equals("Key")){
                        gp.obj[i] = null;
                        
                    }
                }
                return;
            }
            //pickup only items
            if(gp.obj[i].type == type_pickupOnly){
                gp.obj[i].use(this);
                gp.obj[i] = null;
                return;
            }
            else if(gp.obj[i].type != type_doorOnly){
                //Inventory Items
                
                String text;
                if(inventory.size()!= maxInventorySize){
                    if(objectName.equals("Key")){
                        hasKey++;
                    }
                    inventory.add(gp.obj[i]);
                    gp.playSE(i);
                    text = "Got a " + gp.obj[i].name + "!";
                }else{
                    text = "You cannot carry any more!";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;
            }
        }
    }
    
    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true){
            if(i != 999){
 
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();

            }else{
                gp.playSE(7);
                attacking = true;
            }
        }
        
       
        
    }
    
    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false && gp.monster[i].dying == false){
                gp.playSE(6);

                int damage = gp.monster[i].attack - defense;
                if(damage == 0){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
                counterRegen = 0;
            }
            if(gp.monster[i] == null){
                dropItem(new OBJ_Key(gp));
            }
        }
    }
    
     public void damageMonster(int i, int attack){
         if(i != 999){
            if(gp.monster[i].invincible == false){
                gp.playSE(5);
                
                int damage = attack - gp.monster[i].defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage");
                
                gp.monster[i].invincible = true;
                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying =true;
                    gp.ui.addMessage("Exp + " + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    
                    checkLevelUp();
                }
            }
            
         }
    }
    public void checkLevelUp(){
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp *2;
            maxLife += 10;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            projectile.attack++;
            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You leveled up";
        }
    }
    
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }
    public void draw(Graphics2D g2){
               
      BufferedImage image = null;
      int tempScreenX = screenX;
      int tempScreenY = screenY;
      
        switch(direction){
            case "up":
                if(attacking == false){
                    if(spriteNum == 1){image = up1;}
                    if(spriteNum == 2){image = up2;}
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1){image = attackUp1;}
                    if(spriteNum == 2){image = attackUp2;}
                }
                
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                }
                if(attacking == true){
                    if(spriteNum == 1){image = attackDown1;}
                    if(spriteNum == 2){image = attackDown2;}
                }
                break;
            
            case "left":
                if(attacking == false){
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft2;}
                }
                break;
            
            case "right" :
                if(attacking == false){
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                }
                if(attacking == true){
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight2;}
                }
                break;
        }
        
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        
        
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.setColor(Color.red);
        
        
        setScreenForChar(g2, tempScreenX, tempScreenY);
        
    }
    
    public void hpRegeneration(){
        
        if(life < maxLife && invincible == false){
            counterRegen++;
            if(counterRegen >= 240){
                life+=2;
                if(life> maxLife){
                    life = maxLife;
                }
                counterRegen=0;
            }
        }
    }
    public void manaRegeneration(){
        
        if(mana < maxMana && invincible == false){
            counterRegen++;
            if(counterRegen >= 180){
                mana+=5;
                if(mana> maxMana){
                    mana = maxMana;
                }
                counterRegen=0;
            }
        }
    }
    
    public void setScreenForChar(Graphics2D g2, int x, int y) {
        Color c = new Color(0, 0, 0, 220);
        
        if (gp.gameState == gp.characterState) {
            g2.setColor(c);
            
            switch (direction) {
                case "up": 
                    int f = x;
                    int g = y-10;
                    for(int i = 0; i< 4; i++){
                        g2.fillRect(f, g, gp.tileSize, 5);  // In front (above)
                        f+=1;
                        g-=2;
                    }
                    
                    break;
                case "down":
                    int h = x;
                    int j = y+ gp.tileSize;
                    for(int i = 0; i< 4; i++){
                        g2.fillRect(h, j, gp.tileSize, 5);
                        h+=1;
                        j+=2;    
                    }
                    
                    break;
                case "left":
                    int a = x -10;
                    int b = y;
                    for(int i = 0; i< 4; i++){
                        g2.fillRect(a, b, 5, gp.tileSize);  // In front (left)
                        a += 2;
                        b-=2;
                    }
                    
                    break;
                case "right":
                    int d = x + gp.tileSize;
                    int e = y;
                    for(int i = 0; i< 4; i++){
                        g2.fillRect(d, e, 5, gp.tileSize);  // In front (right)
                        d+=2;
                        e-=2;
                    }
                    break;
            }
        }
    }
    
}
