package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import java.math.*;
import java.util.ArrayList;

public class Hydra extends Enemy {

    private float bodyRadius;

    private Animation facingSideAnimation;
    private Animation specialAttackAnimation;
    private Animation currentAnimation;

    private boolean isPlayerLeft;

    private ArrayList<FlameBall> listOfBalls;

    private boolean flameBallsIniciated;


    public int action; // controla estado do boss

    private int isFacingRight; // controla movimento e animação horizontal
    private int isGoingUp; // controla movimento horizontal

    private int xAxeBiggerThenCenter;
    private int yAxeBiggerThenCenter;
    private boolean transitionStarted;


    public Hydra(int life, float positionX, float positionY, int strength, float moveSpeedX, float moveSpeedY, float bodyRadius) {
        super(life, positionX, positionY, strength, moveSpeedX, moveSpeedY);
        this.bodyRadius = bodyRadius;
        action = 0;
        Texture side = new Texture("Enemies/Hydra/tile000.png");
        Texture special = new Texture("Enemies/Hydra/tile002.png");
        facingSideAnimation = new Animation(new TextureRegion(side), 8, 0.5f, true);
        specialAttackAnimation = new Animation(new TextureRegion(special), 4, 1f, true);
        isFacingRight = 1;
        isGoingUp = 1;
        hitBox = new Rectangle(positionX, positionY, 100, 100);
        currentAnimation = facingSideAnimation;
        right = true;
        listOfBalls = new ArrayList<FlameBall>();
        flameBallsIniciated = false;
    life = 10;
    }

    public void move(Player player) {
        if (action == 0) // moving
        {
            if(player.getHitBox().x < hitBox.x)
            {
                isPlayerLeft = true;
            } else {
                isPlayerLeft = false;
            }
            if(isPlayerLeft && right)
            {
                currentAnimation.flip();
                right = false;
            } else if (!isPlayerLeft && !right)
            {
                currentAnimation.flip();
                right = true;
            }

            currentAnimation = facingSideAnimation;
            if(hitBox.x + hitBox.width > 750)
            {
                isFacingRight = -1;
            } else if (hitBox.x < 50)
            {
                isFacingRight = 1;
            }
            if(hitBox.y + hitBox.height > 550)
            {
                isGoingUp = -1;
            } else if (hitBox.y < 50)
            {
                isGoingUp = 1;
            }

            positionX = positionX + moveSpeedX * isFacingRight;
            positionY = positionY + moveSpeedY * isGoingUp;


        } else if (action == 1) // tentacles
        {
            currentAnimation = specialAttackAnimation;
            transitionToCenter();
            if (!flameBallsIniciated)
            {
                iniciateFlameBalls();
            }

        }
        else if (action == 2) // flame rain (statue if we find a decent sprite) 0u0
        {
            currentAnimation = specialAttackAnimation;
            transitionToCenter();
        }

    }

    private void iniciateFlameBalls()
    {
        for (int i = 0; i < 6; i++)
        {
            listOfBalls.add(new FlameBall(130 + i * 800/7, 500));
        }
        flameBallsIniciated = true;
    }


    private void transitionToCenter()
    {
        if (!transitionStarted)
        {
            if (positionX > 315){
                xAxeBiggerThenCenter = -1;
            } else {
                xAxeBiggerThenCenter = 1;
            }
            if (positionY > 150)
            {
                yAxeBiggerThenCenter = -1;
            } else {
                yAxeBiggerThenCenter = 1;
            }
            transitionStarted = true;
        }
        if (transitionStarted)
        {
            if(positionY != 150)
            {
                positionY += yAxeBiggerThenCenter;
            }
            if (positionX != 315)
            {
                positionX += xAxeBiggerThenCenter;
            }
        }
    }

    private void hitBoxPosition () {
        hitBox.x = positionX + 35;
        hitBox.y = positionY + 120;
    }

    public TextureRegion getCurrentAnimation() {
        return currentAnimation.getFrame();
    }

    public void update(float dt, Player player)
    {
        super.update(dt, player);
        currentAnimation.update(dt);
        hitBoxPosition();
        if (Gdx.input.isKeyJustPressed(Input.Keys.L))
        {
            if(action < 2)
                action++;
            else
                action = 0;
        }
        move(player);
    }


    public ArrayList<FlameBall> getListOfBalls() {
        return listOfBalls;
    }

    public int getAction() {
        return action;
    }
}