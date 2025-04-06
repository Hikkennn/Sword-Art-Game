/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import object.OBJ_Key;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;


/**
 *
 * @author Gambet
 */
public class UI {
    Graphics2D g2;
    GamePanel gp;
    public Font purisaB, arial, arial_30, arial_15;
//    BufferedImage keyImage;
    public boolean messageOn = false;
//    public String message = "";
//    int messageCounter = 0;
    private BufferedImage background, playerImage;
    public boolean gameFinished = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    double playTime;
    DecimalFormat df;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        df = new DecimalFormat("#0.00");
        
        arial = new Font("Serif",Font.BOLD,80);
        arial_30 = new Font("Arial",Font.BOLD,30);
        arial_15 = new Font("Arial",Font.BOLD,15);
        try{
            background = ImageIO.read(getClass().getResourceAsStream("/res/objects/7392521.jpg"));
            playerImage = ImageIO.read(getClass().getResourceAsStream("/res/player/down_6.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            InputStream is = getClass().getResourceAsStream("/res/fonts/purisa.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
        
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(purisaB);
        g2.setColor(Color.white);
        
        //Title State
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        
        //playState
        if(gp.gameState == gp.playState){
            drawPlayerHealthBar();
            drawPlayerManaBar();
            drawMessage();
        }
        
        //Pause State
        if(gp.gameState == gp.pauseState){
            drawPlayerHealthBar();
            drawPlayerManaBar();
            drawPauseScreen();
        }
        
        //Dialogue State
        if(gp.gameState == gp.dialogueState){
            drawPlayerHealthBar();
            drawPlayerManaBar();
            drawDialogueScreen();
        }
        
        if(gp.gameState == gp.characterState){
            drawPlayerHealthBar();
            drawPlayerManaBar();
            drawCharacterScreen();  
            drawInventory();
        }
        if(gp.gameState == gp.optionsState){
            drawOptionScreen();
        }
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }
    
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
        
        text = "Game Over";
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text,x,y);
        
        g2.setColor(Color.white);
        g2.drawString(text, x-5, y-5);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
        y+= gp.tileSize*6;
        text = "Quit";
        x = getXforCenteredText(text);
        g2.drawString(text, x, y);
        
        if(commandNum == 0){
            g2.drawString(">", x-40, y);
            if(gp.keyH.enterPressed == true){
                
            }
        }
        
    }
    
    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*5;
        g2.setFont(arial_15);
        
        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                
                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                
                messageY+=30;
                
                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
        
    }
    
    public void drawTitleScreen(){
        
        g2.drawImage(background,0,0,gp.screenWidth,gp.screenHeight,null);
        
        
        g2.setFont(arial);
        String text = "Sword Art Offline";
        
        int x = getXforCenteredText(text);
        int y = gp.tileSize*2;
        int a,b;
        
        g2.setColor(Color.darkGray);
        g2.drawString(text, x+4, y+4);
        
        g2.setColor(Color.blue);
        g2.drawString(text,x,y);
        
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        
        b = y;
        g2.drawImage(playerImage, x-gp.tileSize*5, y+gp.tileSize + 10, gp.tileSize*4, gp.tileSize*4, null);
    
        
        
        g2.setFont(arial_30);
        text = "NEW GAME";
        x = getXforCenteredText(text);
        b += gp.tileSize*3.5;
        
        g2.setColor(Color.black);
        g2.drawString(text, x+4, b+4);
        g2.setColor(Color.white);
        g2.drawString(text, x, b);
        if(commandNum == 0){
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawString(">", x-gp.tileSize, b);
        }
        
        
        g2.setFont(arial_30);
        text = "LEADERBOARDS";
        x = getXforCenteredText(text);
        b += gp.tileSize;
        
        g2.setColor(Color.black);
        g2.drawString(text, x+4, b+4);
        
        g2.setColor(Color.white);
        g2.drawString(text, x, b);
        if(commandNum == 1){
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawString(">", x- gp.tileSize, b);
        }
        
       
        g2.setFont(arial_30);
        text = "QUIT GAME";
        x = getXforCenteredText(text);
        b += gp.tileSize;
        
        g2.setColor(Color.black);
        g2.drawString(text, x+4, b+4);
        
        g2.setColor(Color.white);
        g2.drawString(text, x, b);
        if(commandNum == 2){
            g2.setColor(Color.white);
            g2.drawString(">", x- gp.tileSize, b);
        }
    }
    
    public void drawDialogueScreen(){
        
        int x = gp.tileSize*2;
        int y = gp.tileSize*7;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x,y, width, height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
        x += gp.tileSize / 2;
        y += gp.tileSize;
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    
    public void drawCharacterScreen(){
        //Create A Frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize *4;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize *6;
        
        drawSubWindow(frameX,frameY, frameWidth, frameHeight + 10);
        
        //Text
        g2.setColor(Color.white);
        g2.setFont(arial_15);
        
        int textX = frameX+15;
        int textY = frameY+ (gp.tileSize/2) + 5;
        final int lineHeight =25;
        
        //Names
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("EXP", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;
        
        //Values
        int tailX = (frameX + frameWidth)-30;
        
        //Reset textY
        textY = frameY + (gp.tileSize/2)+5;
        String value;
        
        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.exp+ "/" +gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        
        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight - 20;
        
        int imageSize = gp.tileSize - 20;
        
        g2.drawImage(gp.player.currentWeapon.down1, tailX-gp.tileSize/2 + 5, textY-5,imageSize,imageSize, null);
        textY += gp.tileSize - 21;
        
        g2.drawImage(gp.player.currentShield.down1, tailX-gp.tileSize/2 + 5, textY-5,imageSize,imageSize,null);
    }
    
    public void drawInventory(){
        
        //Frame
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        
        //Slot
        final int slotXstart = frameX+20;
        final int slotYstart = frameY +20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;
        //Draw Player's Items
        for(int i = 0; i < gp.player.inventory.size(); i++){
            
            //Equip Cursor
            if(gp.player.inventory.get(i) == gp.player.currentWeapon ||
                    gp.player.inventory.get(i) == gp.player.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY,null);
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY+= gp.tileSize;
            }
        }
        
        //Cursor
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        
        //draw Cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10,10);
        
        //description Frame
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
        
        
        
        //draw Description Text
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(15F));
        
        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY+=32;
            }
            
        }
    }
    
    public void drawOptionScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int frameX = gp.tileSize*4;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        switch(subState){
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_control(frameX,frameY); break;
            case 3: options_endGameConfirmation(frameX,frameY); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY){
        int textX;
        int textY;
        
        g2.setFont(g2.getFont().deriveFont(20F));
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        //Full Screen
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX- 25, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn == true){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }
        
        //Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX- 25, textY);
        }
        
        // Sound Effect
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX- 25, textY);
        }
        
        //Control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX- 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum =0;
            }
        }
        
        //End Game
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX- 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                commandNum =0;
            }
        }
        
        //Bback to the game
        textY += gp.tileSize *2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5){
            g2.drawString(">", textX- 25, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }
        
        //full screen Check Box
        textX = frameX + (int)(gp.tileSize*5);
        textY = frameY + gp.tileSize*2 + 29;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY, 24, 24);
        }
        
        //Music
        textY+= gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
        
        //Sound effect
        textY+= gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
    }
    
    public void options_fullScreenNotification(int frameX, int frameY){
        int textX = frameX + 25;
        int textY = frameY + gp.tileSize*3;
        
        g2.setFont(g2.getFont().deriveFont(20F));
        currentDialogue = "The change will take effect \nafter restarting the game.";
        for(String line: currentDialogue.split(("\n"))){
            g2.drawString(line, textX, textY);
            textY += 30;
        }
        
        textX += 23;
        textY = frameY + gp.tileSize *9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }
    
    public void options_control(int frameX, int frameY){
        int textX;
        int textY;
        
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Stats", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY);
        
        
        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("Y", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;
       
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }
    
    public void options_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        
        g2.setFont(g2.getFont().deriveFont(20F));
        currentDialogue = "Quit the game and return \nto the title screen?";
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY +=40;
        }
        
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState =0;
                gp.gameState = gp.titleState;
            }
        }
        
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState =0;
                commandNum = 3;
               
            }
        }
    }
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,220);
        
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10, height-10, 25,25);
    }
    
    public void drawPauseScreen(){
        
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);
    }
    
    
    
    //forCentering the Text
    public int getXforCenteredText(String text){ 
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    
    private void drawPlayerHealthBar() {
        int barWidth = 120;
        int barHeight = 15;
        int x = gp.tileSize;  
        int y = gp.tileSize*2 - 10;  
        
        // Draw background bar (red)
        g2.setColor(Color.RED);
        g2.fillRect(x, y, barWidth, barHeight);
        
        

        // Draw current HP (green)
        int currentHealthWidth = (int) ((double) gp.player.life / gp.player.maxLife * barWidth);
        g2.setColor(Color.GREEN);
        g2.fillRect(x, y, currentHealthWidth, barHeight);
        
        //Draw current HP and max HP at the same time
        g2.setFont(gp.ui.arial_15);
        g2.setColor(Color.white);
        g2.drawString(toStringManaAndHP(gp.player.life, gp.player.maxLife), x*2-15, y+13);
        
        // Draw border
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, barWidth, barHeight);
        
        
        
        String text = "HP";
        g2.setFont(gp.ui.arial_15);
        g2.setColor(Color.white);
        g2.drawString(text, x/2-5, y+13);
    }
    
    private void drawPlayerManaBar() {
        int barWidth = 120;
        int barHeight = 15;
        int x = gp.tileSize;  
        int y = gp.tileSize*2+15;  
        
        // Draw background bar (white)
        g2.setColor(Color.black);
        g2.fillRect(x, y, barWidth, barHeight);
        
        
        
        // Draw current Mana (Blue)
        int currentHealthWidth = (int) ((double) gp.player.mana / gp.player.maxMana * barWidth);
        g2.setColor(Color.blue);
        g2.fillRect(x, y, currentHealthWidth, barHeight);
        
        
        //draw current Mana and MaxMana at the same Time
        g2.setFont(gp.ui.arial_15);
        g2.setColor(Color.white);
        g2.drawString(toStringManaAndHP(gp.player.mana,gp.player.maxMana), x*2-8, y+13);
        
        // Draw border
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, barWidth, barHeight);
        
        String text = "MP";
        g2.setFont(gp.ui.arial_15);
        g2.setColor(Color.white);
        g2.drawString(text, x/2-5, y+13);
    }

    public String toStringManaAndHP(int lives, int maxlives){
        return lives+"/"+maxlives;
    }
    
    public int getXforAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
