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

public class Wolf extends Enemy {

    private Animation runAnimation;
    private float timeInAir;

    public Wolf(int life, float positionX, float positionY, int strength)
    {   
        super(life, positionX - 20, positionY - 10, strength,  (float) Math.random() + 3, 0);
        Texture texture = new Texture("Enemies/Wolf/Run.png");
        runAnimation = new Animation(new TextureRegion(texture), 6, 0.5f, true);
        right = false;
        hitBox = new Rectangle(positionX, positionY, 70, 35);
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
                runAnimation.flip();
            }
        } else {
            positionX += moveSpeedX;
            if(!right) {
                right = true;
                runAnimation.flip();
            }
        }
    }

    public void gravityEffect() {
        float newPositionY = positionY - 2 * timeInAir;
        if (hitBox.y > 55) {
            positionY = newPositionY;
            timeInAir += 0.1;
        } else {
            timeInAir = 0;
        }
    }

    private void hitBoxPosition() {
        hitBox.x = positionX + 20;
        hitBox.y = positionY + 10;
    }

    public TextureRegion getAnimation () {
        return runAnimation.getFrame();
    }

    public void update(float dt, Player player) {
        super.update(dt, player);
        runAnimation.update(dt);
        hitBoxPosition();
        gravityEffect();
        movement(player);
    }
}
