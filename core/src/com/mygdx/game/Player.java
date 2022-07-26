package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Projectile;

import java.util.*;

public class Player {
    private float positionX;
    private float positionY;
    private float moveSpeedX;
    private float jumpSpeed;
    private float timeInAir;
    private float startJump;
    private float rollSpeed;
    private boolean jumping;
    private boolean right;
    private boolean running;
    private boolean attacking;
    private boolean rolling;
    private boolean shooted;
    private Rectangle hitBox;
    private Animation runAnimation;
    private Animation idleAnimation;
    private Animation jumpAnimation;
    private Animation attackAnimation;
    private Animation rollAnimation;
    List<Projectile> projectiles;

    public float gravity;

    public Player(float positionX, float positionY, float moveSpeedX, float jumpSpeed, float gravity) {
        Texture texture = new Texture("Character/Archer/SpriteSheets/Run.png");
        runAnimation = new Animation(new TextureRegion(texture), 8, 0.5f, true);
        texture = new Texture("Character/Archer/SpriteSheets/Idle.png");
        idleAnimation = new Animation(new TextureRegion(texture), 4, 0.5f, true);
        texture = new Texture("Character/Archer/SpriteSheets/Jump2.png");
        jumpAnimation = new Animation(new TextureRegion(texture), 4, 0.5f, true);
        texture = new Texture("Character/Archer/SpriteSheets/Attack.png");
        attackAnimation = new Animation(new TextureRegion(texture), 7, 0.5f, true);
        texture = new Texture("Character/Archer/SpriteSheets/Rolling.png");
        rollAnimation = new Animation(new TextureRegion(texture), 7, 0.5f, true);
        hitBox = new Rectangle(positionX, positionY, 55, 55);
        this.positionX = positionX - 38;
        this.positionY = positionY - 42;
        this.moveSpeedX = moveSpeedX;
        this.jumpSpeed = jumpSpeed;
        this.gravity = gravity;
        right = true;
        running = false;
        attacking = false;
        timeInAir = 0;
        shooted = false;
        rolling = false;
        rollSpeed = 10;
        projectiles = new ArrayList<Projectile>();
    }

    public TextureRegion getAnimation() {
        if (rolling)
            return rollAnimation.getFrame();
        else if (running && grounded())
            return runAnimation.getFrame();
        else if (!grounded())
            return jumpAnimation.getFrame();
        else if (attacking)
            return attackAnimation.getFrame();
        else
            return idleAnimation.getFrame();
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

    public void moveX(ArrayList<Rectangle> objects) {
        if (Gdx.input.isKeyPressed(Input.Keys.D) && hitBox.x + hitBox.width < 750) {
            float newPositionX;
            if (rolling)
                newPositionX = positionX + rollSpeed;
            else
                newPositionX = positionX + moveSpeedX;
            if (!right) {
                runAnimation.flip();
                idleAnimation.flip();
                jumpAnimation.flip();
                attackAnimation.flip();
                rollAnimation.flip();
            }
            right = true;
            positionX = newPositionX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && hitBox.x > 50) {
            float newPositionX;
            if (rolling)
                newPositionX = positionX - rollSpeed;
            else
                newPositionX = positionX - moveSpeedX;
            if (right) {
                runAnimation.flip();
                idleAnimation.flip();
                jumpAnimation.flip();
                attackAnimation.flip();
                rollAnimation.flip();
            }
            right = false;
            positionX = newPositionX;
        }
    }

    public boolean grounded() {
        if (hitBox.y <= 55)
            return true;
        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            return false;
        else if (hitBox.x > 70 && hitBox.x < 170 && hitBox.y >= 165 && hitBox.y <= 175)
            return true;
        else if (hitBox.x > 320 && hitBox.x < 420 && hitBox.y >= 250 && hitBox.y <= 260)
            return true;
        else if (hitBox.x > 70 && hitBox.x < 170 && hitBox.y >= 360 && hitBox.y <= 370)
            return true;
        else if (hitBox.x > 570 && hitBox.x < 670 && hitBox.y >= 165 && hitBox.y <= 175)
            return true;
        else if (hitBox.x > 570 && hitBox.x < 670 && hitBox.y >= 360 && hitBox.y <= 370)
            return true;
        else
            return false;
    }

    public void jump(ArrayList<Rectangle> objects) {
        if (hitBox.y + hitBox.height > 550) {
            jumping = false;
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && grounded()) {
            jumpAnimation.reset();
            startJump = positionY;
            jumping = true;
        } else if (!Gdx.input.isKeyPressed(Input.Keys.SPACE) || positionY > startJump + 150) {
            jumping = false;
        }

        if (jumping)
            positionY += jumpSpeed;
    }

    public void gravityEffect(ArrayList<Rectangle> objects) {
        float newPositionY = positionY - gravity * timeInAir;
        if (hitBox.y > 55 && !jumping && !grounded()) {
            positionY = newPositionY;
            timeInAir += 0.1;
        } else {
            timeInAir = 0;
        }
    }

    private void shoot() {
        projectiles.add(new Projectile(hitBox.x + 15, hitBox.y + 30, 10, right, "Character/Archer/Projectile.png"));
    }

    private void attack() {
        if (Gdx.input.isKeyPressed(Input.Keys.Q) && grounded() && !running) {
            if (!attacking)
                attackAnimation.reset();
            attacking = true;
            if (attackAnimation.getFrameIdx() == 6 && !shooted) {
                shoot();
                shooted = true;
            } else if (attackAnimation.getFrameIdx() != 6) {
                shooted = false;
            }
        } else
            attacking = false;
    }

    private void roll() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT) && grounded()) {
            if (!rolling)
                rollAnimation.reset();
            rolling = true;
        }
        if (rollAnimation.getFrameIdx() == 6) {
            rolling = false;
        }
    }

    private void isRunning(ArrayList<Rectangle> objects) {
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            running = true;
        } else {
            running = false;
        }
    }

    private void hitBoxPosition(ArrayList<Rectangle> objects) {
        hitBox.x = positionX + 38;
        hitBox.y = positionY + 42;
    }

    public void update(ArrayList<Rectangle> objects, float dt, SpriteBatch batch) {
        runAnimation.update(dt);
        idleAnimation.update(dt);
        jumpAnimation.update(dt);
        attackAnimation.update(dt);
        rollAnimation.update(dt);
        for (Projectile projectile : projectiles) {
            projectile.drawProjectile(batch, 50, 750);
        }
        roll();
        attack();
        moveX(objects);
        jump(objects);
        gravityEffect(objects);
        hitBoxPosition(objects);
        isRunning(objects);
        // System.out.println(hitBox.x);
        // System.out.println(hitBox.y);
    }

}
