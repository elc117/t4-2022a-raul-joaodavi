package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Player {
    private String name;
    private Texture mainImage;
    private float positionX;
    private float positionY;
    private float moveSpeedX;
    private float jumpSpeed;

    Rectangle body;

    public float gravity;

    public Player(String name, String imageLink, float positionX, float positionY, float moveSpeedX, float jumpSpeed, float gravity)
    {
        this.name = name;
        mainImage = new Texture(imageLink);
        this.positionX = positionX;
        this.positionY = positionY;
        this.moveSpeedX = moveSpeedX;
        this.jumpSpeed = jumpSpeed;
        this.gravity = gravity;
        body = new Rectangle(positionX, positionY, mainImage.getWidth(), mainImage.getHeight());
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

    public void moveX(ArrayList<Rectangle> objects)
    {
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            float newPositionX = positionX += moveSpeedX;
            for (int i = 0; i < objects.toArray().length; i++)
            {
                //if () return;
            }
            positionX = newPositionX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            float newPositionX = positionX -= moveSpeedX;
            for (int i = 0; i < objects.toArray().length; i++)
            {
                //if() return;
            }
            positionX = newPositionX;
        }
    }

    public void jump(ArrayList<Rectangle> objects)
    {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            positionY += jumpSpeed;
    }

    public void gravityEffect(ArrayList<Rectangle> objects)
    {
        float newPositionY = positionY - gravity;
        for (int i = 0; i < objects.toArray().length; i++)
        {
            //if () return;
        }
        positionY = newPositionY;
    }

    public void render(ArrayList<Rectangle> objects)
    {
        moveX(objects);
        jump(objects);
        //gravityEffect(objects);
    }

}
