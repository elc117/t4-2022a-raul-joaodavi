package com.mygdx.game.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.player.Player;

public class Enemy {
    protected int life;
    protected float positionX;
    protected float positionY;
    protected int strength;
    protected float moveSpeedX;
    protected float moveSpeedY;
    protected boolean right;
    protected Rectangle hitBox;


    public Enemy(int life, float positionX, float positionY, int strength, float moveSpeedX, float moveSpeedY)
    {
        this.life = life;
        this.positionX = positionX;
        this.positionY = positionY;
        this.strength = strength;
        this.moveSpeedX = moveSpeedX;
        this.moveSpeedY = moveSpeedY;
    }

    public float getPositionY() {
        return positionY;
    }

    public float getPositionX() {
        return positionX;
    }

    public int getLife() {
        return life;
    }

    public int getStrength() {
        return strength;
    }

    public Rectangle getHitBox () {
        return hitBox;
    }

    public void getHit (float arrowX) {
        life--;
        if(arrowX < hitBox.getX()) {
            positionX += 50;
        } else {
            positionX -= 50;
        }
        positionY += 30;
    }

    public void hit (float playerX) {
        if(playerX < hitBox.getX()) {
            positionX += 50;
        } else {
            positionX -= 50;
        }
        positionY += 30;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void update (float dt, Player player) {

    }
}