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
    private Rectangle hitBox;

    public float gravity;

    public Player(String name, String imageLink, float positionX, float positionY, float moveSpeedX, float jumpSpeed, float gravity)
    {
        this.name = name;
        mainImage = new Texture(imageLink);
        hitBox = new Rectangle(positionX, positionY, 55, 55);
        this.positionX = positionX - 38;
        this.positionY = positionY - 42;
        this.moveSpeedX = moveSpeedX;
        this.jumpSpeed = jumpSpeed;
        this.gravity = gravity;
        timeInAir = 0;
    }

    public Texture getMainImage() {
        return mainImage;
    }

    public float getPositionX() {
        return positionX;
    }

    public Rectangle getHitBox() {
        return hitBox;
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
        if (Gdx.input.isKeyPressed(Input.Keys.D) && hitBox.x + hitBox.width < 750)
        {
            float newPositionX = positionX += moveSpeedX;
            for (int i = 0; i < objects.toArray().length; i++)
            {
                //if () return;
            }
            positionX = newPositionX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && hitBox.x > 50)
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
        if(hitBox.y <= 55)
            return true;
        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            return false;
        else if(hitBox.x > 70 && hitBox.x < 170 && hitBox.y >= 165 && hitBox.y <= 175)
            return true;
        else if(hitBox.x > 320 && hitBox.x < 420 && hitBox.y >= 250 && hitBox.y <= 260)
            return true;
        else if(hitBox.x > 70 && hitBox.x < 170 && hitBox.y >= 360 && hitBox.y <= 370)
            return true;
        else if(hitBox.x > 570 && hitBox.x < 670 && hitBox.y >= 165 && hitBox.y <= 175)
            return true;
        else if(hitBox.x > 570 && hitBox.x < 670 && hitBox.y >= 360 && hitBox.y <= 370)
            return true;
        else
            return false;
    }

    public void jump(ArrayList<Rectangle> objects)
    {   
        if(hitBox.y + hitBox.height > 550) {
            jumping = false;
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && grounded(objects)) {
            startJump = positionY;
            jumping = true;
        } else if (!Gdx.input.isKeyPressed(Input.Keys.SPACE) || positionY > startJump + 150) {
            jumping = false;
        }

        if(jumping)
            positionY += jumpSpeed;
    }

    public void gravityEffect(ArrayList<Rectangle> objects)
    {
        float newPositionY = positionY - gravity * timeInAir;
        if(hitBox.y > 55 && !jumping && !grounded(objects)) {
            positionY = newPositionY;
            timeInAir += 0.1;
        } else {
            timeInAir = 0;
        }
    }

    private void hitBoxPosition(ArrayList<Rectangle> objects) {
        hitBox.x = positionX + 38;
        hitBox.y = positionY + 42;
    }

    public void render(ArrayList<Rectangle> objects)
    {
        moveX(objects);
        jump(objects);
        gravityEffect(objects);
        hitBoxPosition(objects);
        //System.out.println(hitBox.x);
        //System.out.println(hitBox.y);
    }

}
