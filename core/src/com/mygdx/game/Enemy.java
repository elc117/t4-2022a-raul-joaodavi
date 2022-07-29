package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

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
            positionX += 20;
        } else {
            positionX -= 20;
        }
        positionY += 20;
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