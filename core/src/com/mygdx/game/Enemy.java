package com.mygdx.game;

public class Enemy {
    protected int life;
    protected float positionX;
    protected float positionY;
    protected int strength;
    protected float moveSpeedX;
    protected float moveSpeedY;


    public Enemy(int life, float positionX, float positionY, int strength, float moveSpeedX, float moveSpeedY)
    {
        this.life = life;
        this.positionX = positionX - 38;
        this.positionY = positionY - 42;
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
}