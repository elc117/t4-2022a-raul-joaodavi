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

public class Vulture extends Enemy {

    private Animation flyAnimation;

    public Vulture(int life, float positionX, float positionY, int strength)
    {   
        super(life, positionX - 10, positionY - 10, strength, (float) Math.random() + 1, (float) Math.random() + 1);
        Texture texture = new Texture("Enemies/Vulture/Fly.png");
        flyAnimation = new Animation(new TextureRegion(texture), 4, 0.5f, true);
        right = false;
        hitBox = new Rectangle(positionX, positionY, 54, 27);
    }

    private void movement(Player player) {
        if (right && player.getHitBox().x > hitBox.x && player.getHitBox().x + player.getHitBox().width < hitBox.x + hitBox.width) 
            positionX += 0;
        else if (!right && player.getHitBox().x < hitBox.x && player.getHitBox().x + player.getHitBox().width > hitBox.x)
            positionX += 0;
        else if(player.getHitBox().x + player.getHitBox().width / 2 < hitBox.x) {
            positionX -= moveSpeedX;
            if(right) {
                right = false;
                flyAnimation.flip();
            }
        } else {
            positionX += moveSpeedX;
            if(!right) {
                right = true;
                flyAnimation.flip();
            }
        }
        if(player.getHitBox().y + player.getHitBox().height / 2 < hitBox.y) {
            positionY -= moveSpeedY;
        } else {
            positionY += moveSpeedY;
        }
    }

    private void hitBoxPosition() {
        hitBox.x = positionX + 10;
        hitBox.y = positionY + 10;
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
