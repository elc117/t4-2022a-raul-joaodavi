package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Hydra extends Enemy {

    private float bodyRadius;
    private Texture sprite;

    public int action;

    public Hydra(int life, float positionX, float positionY, int strength, float moveSpeedX, float moveSpeedY, float bodyRadius) {
        super(life, positionX, positionY, strength, moveSpeedX, moveSpeedY);
        this.bodyRadius = bodyRadius;
        sprite = new Texture("Character/Archer/Idle/tile040.png");
        action = 0;
    }

    public void moveX() {
        if (action == 0) // moving
        {

        } else if (action == 1) // tentacles
        {

        }
        else if (action == 2) // flame rain (statue if we find a decent sprite) 0u0
        {

        }

    }

    public Texture getSprite() {
        return sprite;
    }
}