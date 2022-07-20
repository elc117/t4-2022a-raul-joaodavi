package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Player {
    private String name;
    private Texture mainImage;
    private float positionX;
    private float positionY;
    public Player(String name, String imageLink, float positionX, float positionY)
    {
        this.name = name;
        mainImage = new Texture(imageLink);
        this.positionX = positionX;
        this.positionY = positionY;
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
}
