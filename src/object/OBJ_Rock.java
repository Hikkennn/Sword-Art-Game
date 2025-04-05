/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;


import entity.Projectile;
import game.GamePanel;

/**
 *
 * @author Gambet
 */
public class OBJ_Rock extends Projectile{
    GamePanel gp;
    public OBJ_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 4;
        useCost = 1;
        alive = false;
        
        getImage();
    }
    
    public void getImage(){
        up1 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        down1 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        left1 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        right1 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        
    }
}
