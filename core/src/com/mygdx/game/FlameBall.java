package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class FlameBall {
    public Animation animation;

    private int positionX;
    private int positionY;
    private Rectangle hitbox;
    boolean active;

    private int dropSpeed;

    private boolean isRight;
    public FlameBall(int positionX, int positionY){
        Texture texture = new Texture("FlameBall/flameball.png");
        animation= new Animation(new TextureRegion(texture), 4, 1f, true);
        active = true;
        hitbox = new Rectangle(positionX, positionY, 16, 16);
        this.positionX = positionX;
        this.positionY = positionY;
        isRight = true;
        dropSpeed = 4;
    }

    public void update(float delta)
    {
        animation.update(delta);
        hitbox.x = positionX + 7;
        hitbox.y = positionY + 2;
        move();
    }

    public void move()
    {
        if(positionY < 50)
        {
            positionY = 500;
            if(isRight)
            {
                positionX -= 800/(10*2);
                isRight = false;
            } else {
                positionX += 800/(10*2);
                isRight = true;
            }
        } else {
            positionY-=dropSpeed;
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public TextureRegion getAnimation() {
        return animation.getFrame();
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
