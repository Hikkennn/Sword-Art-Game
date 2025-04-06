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
        else if(gp.gameState == gp.optionsState){
            optionsState(code);
        }
        else if(gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
        
        
    }
    
    //SCreen or title State
    public void titleState(int code){
        if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                gp.playSE(9);
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                    
                }
            }
            if (code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                gp.playSE(9);
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
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
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
    public void optionsState(int code){
       if(code == KeyEvent.VK_ESCAPE){
           gp.gameState = gp.playState;
           
       } 
       if(code == KeyEvent.VK_ENTER){
           enterPressed = true;
       }
       int maxCommandNum =0;
       switch(gp.ui.subState){
           case 0: maxCommandNum = 5; break;
           case 3: maxCommandNum = 1; break;
       }
       if(code == KeyEvent.VK_W){
           gp.ui.commandNum--;
           gp.playSE(9);
           if(gp.ui.commandNum < 0){
               gp.ui.commandNum = maxCommandNum;
           }
       }
       if(code == KeyEvent.VK_S){
           
           gp.ui.commandNum++;
           gp.playSE(9);
           if(gp.ui.commandNum > maxCommandNum){
               gp.ui.commandNum = 0;
           }
       }
       if(code == KeyEvent.VK_A){
           if(gp.ui.subState == 0){
               if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                   gp.music.volumeScale--;
                   gp.music.checkVolume();
                   gp.playSE(9);
               }
               if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0){
                   gp.se.volumeScale--;
                   gp.playSE(9);
               }
           }
       }
       if(code == KeyEvent.VK_D){
           if(gp.ui.subState == 0){
               if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                   gp.music.volumeScale++;
                   gp.music.checkVolume();
                   gp.playSE(9);
               }
               if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5){
                   gp.se.volumeScale++;
                   gp.playSE(9);
               }
           }
       }
    }

    
    public void gameOverState(int code){
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.titleState;
                gp.restart();
            } 
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
