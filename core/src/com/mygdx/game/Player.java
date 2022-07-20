package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player {
    private String name;
    private Texture mainImage;
    private float positionX;
    private float positionY;
    private float moveSpeedX;
    private float jumpSpeed;

    public Player(String name, String imageLink, float positionX, float positionY, float moveSpeedX, float jumpSpeed)
    {
        this.name = name;
        mainImage = new Texture(imageLink);
        this.positionX = positionX;
        this.positionY = positionY;
        this.moveSpeedX = moveSpeedX;
        this.jumpSpeed = jumpSpeed;
    }

    public Texture getMainImage() {
        return mainImage;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public void moveX()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            positionX += moveSpeedX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            positionX -= moveSpeedX;
        }
    }

    public void jump()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            positionY += jumpSpeed;
    }

    public void render()
    {
        moveX();
        jump();
    }



}
