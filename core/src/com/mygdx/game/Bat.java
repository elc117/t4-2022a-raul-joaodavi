package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Projectile;

import java.util.*;

public class Bat extends Enemy {

    private Animation flyAnimation;

    public Bat(int life, float positionX, float positionY, int strength, float moveSpeedX, float moveSpeedY)
    {   
        super(life, positionX - 64, positionY - 60, strength, moveSpeedX, moveSpeedY);
        Texture texture = new Texture("Enemies/Bat/Fly.png");
        flyAnimation = new Animation(new TextureRegion(texture), 4, 0.5f, true);
        right = true;
        hitBox = new Rectangle(positionX, positionY, 27, 27);
    }

    private void movement(Player player) {

    }

    private void hitBoxPosition() {
        hitBox.x = positionX + 64;
        hitBox.y = positionY + 60;
    }

    public TextureRegion getAnimation () {
        return flyAnimation.getFrame();
    }

    public void update(float dt, Player player) {
        super.update(dt, player);
        flyAnimation.update(dt);
        hitBoxPosition();
        movement(player);
    }
}