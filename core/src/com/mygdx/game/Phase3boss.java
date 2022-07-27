package com.mygdx.game;

public class Phase3boss extends Enemy {

    private float bodyRadius;

    public Phase3boss(int life, float positionX, float positionY, int strength, float moveSpeedX, float moveSpeedY, float bodyRadius) {
        super(life, positionX, positionY, strength, moveSpeedX, moveSpeedY);
        this.bodyRadius = bodyRadius;
    }

    public void moveX() {

    }

}