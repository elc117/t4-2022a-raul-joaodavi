package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Hydra extends Enemy {

    private float bodyRadius;

    private Animation animation1;

    public int action;

    private Rectangle hitBox;

    public Hydra(int life, float positionX, float positionY, int strength, float moveSpeedX, float moveSpeedY, float bodyRadius) {
        super(life, positionX, positionY, strength, moveSpeedX, moveSpeedY);
        this.bodyRadius = bodyRadius;
        action = 0;
        Texture texture = new Texture("Enemies/Hydra/tile000.png");
        animation1 = new Animation(new TextureRegion(texture), 8, 0.5f, true);

        hitBox = new Rectangle(positionX, positionY, 55, 55);
        this.positionX = positionX - 38;
        this.positionY = positionY - 42;
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

    public Animation getAnimation1() {
        return animation1;
    }

    public void update(float delta)
    {

    }
}