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
public class OBJ_Chest extends Entity{
    
    
    public OBJ_Chest(GamePanel gp){
        
        super(gp);
        name = "Chest";
        
       down1 =setup("/res/objects/chest",gp.tileSize, gp.tileSize);
       
    }
}
