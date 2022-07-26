package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Projectile {
    private Rectangle projectile;
    private TextureRegion img;
    private float speed;
    boolean right;

    public Projectile (float x, float y, float speed, boolean right, String textureStr) {
        Texture texture = new Texture(textureStr);
        projectile = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        img = new TextureRegion(texture);
        this.speed = speed;
        this.right = right;
        if(!right) {
            img.flip(true, false);
            projectile.x -= 40;
        }
    }

    public Rectangle getProjectile () {
        return projectile;
    }

    public float getX () {
        return projectile.x;
    }

    private void moveX() {
        if (right)
            projectile.x += speed;
        else 
            projectile.x -= speed;
    }

    public void drawProjectile (SpriteBatch batch, float lim1, float lim2) {
        if (projectile.x > lim1 && projectile.x + projectile.width < lim2) {
            moveX();
            batch.draw(img, projectile.x, projectile.y);
        }
    }
}