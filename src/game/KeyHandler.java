/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Andre Policios
 */
public class KeyHandler implements KeyListener{
    GamePanel gp;
    public boolean enterPressed,upPressed, downPressed, leftPressed, 
            rightPressed, shotKeyPressed;
    
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e){}
    
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        
        //Title State
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        
        //Play State
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        
        //Pause State
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        
        //dialogue state
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        
        //Character State
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }
        
        
    }
    
    //SCreen or title State
    public void titleState(int code){
        if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1){
                    
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
    }
    
    //PlayState
    public void playState(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
            }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_K){
            shotKeyPressed = true;
        }

        if(code == KeyEvent.VK_Y){
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
    }
    
    //Pause State
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    
    //Dialogue State
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }
    
    //characterState
    public void characterState(int code){
        if(code == KeyEvent.VK_Y){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
                gp.playSE(9);
            }
            
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
                gp.playSE(9);
            }
            
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow != 3){
                gp.ui.slotRow++;
                gp.playSE(9);
            }
            
        }
        if(code == KeyEvent.VK_D){
           if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
                gp.playSE(9);
           }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
        if(code == KeyEvent.VK_K){
            shotKeyPressed = false;
            
        }

    }
    
    
    
}
