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
        super(life, positionX, positionY, strength, moveSpeedX, moveSpeedY);
        Texture texture = new Texture("Enemies/Bat/Fly.png");
        flyAnimation = new Animation(new TextureRegion(texture), 4, 0.5f, true);
    }

    public TextureRegion getAnimation () {
        return flyAnimation.getFrame();
    }

    public void update(float dt) {
        super.update(dt);
        flyAnimation.update(dt);
    }
}
