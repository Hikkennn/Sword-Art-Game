/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import game.GamePanel;

/**
 *
 * @author Gambet
 */
public class Monster extends Entity{
    
    protected int exp;  // Experience gained by player for defeating this monster
    protected boolean dying;

    // Constructor
    public Monster(GamePanel gp, int maxLife, int attack, int defense, int exp) {
        super(gp);
        this.maxLife = maxLife;
        this.life = maxLife;
        this.attack = attack;
        this.defense = defense;
        this.exp = exp;
        this.dying = false;
    }

    // Abstract update method for monster-specific behavior
    
    public void update() {
        // Monster-specific AI or behavior logic (e.g., moving toward the player, attacking)
    }

    // Method to handle the monster's attack
    
    public void attack() {
        // Monster-specific attack logic (e.g., melee or projectile)
    }
    
}
