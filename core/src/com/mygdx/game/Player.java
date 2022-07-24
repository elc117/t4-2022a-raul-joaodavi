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
    private float timeInAir;
    private float startJump;
    private boolean jumping;

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
        timeInAir = 0;
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
        if (Gdx.input.isKeyPressed(Input.Keys.D) && positionX < 620)
        {
            float newPositionX = positionX += moveSpeedX;
            for (int i = 0; i < objects.toArray().length; i++)
            {
                //if () return;
            }
            positionX = newPositionX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && positionX > -25)
        {
            float newPositionX = positionX -= moveSpeedX;
            for (int i = 0; i < objects.toArray().length; i++)
            {
                //if() return;
            }
            positionX = newPositionX;
        }
    }

    public boolean grounded(ArrayList<Rectangle> objects) 
    {
        if(positionY <= 47)
            return true;
        else if(positionX > -15 && positionX < 85 && positionY >= 160 && positionY <= 170)
            return true;
        else if(positionX > 240 && positionX < 340 && positionY >= 245 && positionY <= 255)
            return true;
        else if(positionX > -15 && positionX < 85 && positionY >= 353 && positionY <= 363)
            return true;
        else if(positionX > 485 && positionX < 585 && positionY >= 160 && positionY <= 170)
            return true;
        else if(positionX > 485 && positionX < 585 && positionY >= 353 && positionY <= 363)
            return true;
        else
            return false;
    }

    public void jump(ArrayList<Rectangle> objects)
    {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && grounded(objects)) {
            startJump = positionY;
            jumping = true;
        } else if (!Gdx.input.isKeyPressed(Input.Keys.SPACE) || positionY > startJump + 150){
            jumping = false;
        }

        if(jumping)
            positionY += jumpSpeed;
    }

    public void gravityEffect(ArrayList<Rectangle> objects)
    {
        float newPositionY = positionY - gravity * timeInAir;
        for (int i = 0; i < objects.toArray().length; i++)
        {
            //if () return;
        }
        if(positionY > 47 && !jumping && !grounded(objects)) {
            positionY = newPositionY;
            timeInAir += 0.1;
        } else {
            timeInAir = 0;
        }
    }

    public void render(ArrayList<Rectangle> objects)
    {
        moveX(objects);
        jump(objects);
        gravityEffect(objects);
        System.out.println(positionY);
        System.out.println(positionX);
    }

}
