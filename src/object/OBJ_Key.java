/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import game.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Gambet
 */
public class OBJ_Key extends Entity{
    
    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Key";
        
        down1 =setup("/res/objects/key",gp.tileSize, gp.tileSize);
        description = "[" + name+"]\nUse to open the door";
    }
}
