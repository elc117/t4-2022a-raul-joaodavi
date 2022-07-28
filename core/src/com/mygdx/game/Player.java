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

public class Player {
    // ESTADO E CARACTERISTICAS DO PLAYER
    private int life;
    private float positionX;
    private float positionY;
    private float moveSpeedX; // velocidade horizontal
    private float jumpSpeed; // velocidade do pulo
    private float timeInAir; // tempo no ar - usado para gravidade
    private float startJump; // posicao do comeco do pulo (y)
    private float rollSpeed; // velocidade de rolamento
    private boolean jumping; // player esta pulando
    private boolean isFacingRight; // player virado para a direita
    private boolean running; // player esta se movendo horizontalmente
    private boolean attacking; // player esta atacando
    private boolean rolling; // player esta em rolamento
    private boolean shooted; // player atirou
    private boolean grounded; // player esta no chao

    private Rectangle hitBox; // fisica (corpo) do jogador

    // TEXTURAS, ANIMACOES, E SONS
    private Texture arrowTexture;
    private Texture lifeTexture;
    private Texture avatarTexture;
    private Sound jumpSound;
    private Sound shootSound;
    private Sound rollSound;
    private Animation runAnimation;
    private Animation idleAnimation;
    private Animation jumpAnimation;
    private Animation fallAnimation;
    private Animation attackAnimation;
    private Animation rollAnimation;
    private List<Projectile> projectiles; // Spawnados quando atira, somem no contato

    public float gravity; // forca da gravidade


    // CONSTRUTOR
    public Player(float positionX, float positionY, float moveSpeedX, float jumpSpeed, float gravity) {
        lifeTexture = new Texture("Character/Archer/Life.png");
        avatarTexture = new Texture("Character/Archer/Avatar.png");
        Texture texture = new Texture("Character/Archer/SpriteSheets/Run.png");
        runAnimation = new Animation(new TextureRegion(texture), 8, 0.5f, true);
        texture = new Texture("Character/Archer/SpriteSheets/Idle.png");
        idleAnimation = new Animation(new TextureRegion(texture), 4, 0.5f, true);
        texture = new Texture("Character/Archer/SpriteSheets/Jump.png");
        jumpAnimation = new Animation(new TextureRegion(texture), 4, 0.5f, true);
        texture = new Texture("Character/Archer/SpriteSheets/Fall.png");
        fallAnimation = new Animation(new TextureRegion(texture), 2, 0.5f, false);
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
        isFacingRight = true;
        running = false;
        attacking = false;
        timeInAir = 0;
        shooted = false;
        rolling = false;
        grounded = true;
        rollSpeed = 10;
        projectiles = new ArrayList<Projectile>();
        shootSound = Gdx.audio.newSound(Gdx.files.internal("SoundEffects/SFX/ShootSound.mp3"));
        arrowTexture = new Texture("Projectiles/Arrow.png");
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("SoundEffects/SFX/JumpSound.mp3"));
        rollSound = Gdx.audio.newSound(Gdx.files.internal("SoundEffects/SFX/RollSound.mp3"));
    }

    // retorna animacao de acordo com o estado do jogador
    public TextureRegion getAnimation() {
        if (rolling)
            return rollAnimation.getFrame();
        else if (running && grounded)
            return runAnimation.getFrame();
        else if (jumping)
            return jumpAnimation.getFrame();
        else if (!grounded)
            return fallAnimation.getFrame();
        else if (attacking)
            return attackAnimation.getFrame();
        else
            return idleAnimation.getFrame();
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
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

    public void setGrounded (boolean grounded) {
        this.grounded = grounded;
    }

    // Movimento horizontal
    public void moveX(ArrayList<Rectangle> objects) {
        if (Gdx.input.isKeyPressed(Input.Keys.D) && hitBox.x + hitBox.width < 750) {
            float newPositionX;
            if (rolling)
                newPositionX = positionX + rollSpeed;
            else
                newPositionX = positionX + moveSpeedX;
            if (!isFacingRight) {
                runAnimation.flip();
                idleAnimation.flip();
                jumpAnimation.flip();
                attackAnimation.flip();
                rollAnimation.flip();
                fallAnimation.flip();
            }
            isFacingRight = true;
            positionX = newPositionX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && hitBox.x > 50) {
            float newPositionX;
            if (rolling)
                newPositionX = positionX - rollSpeed;
            else
                newPositionX = positionX - moveSpeedX;
            if (isFacingRight) {
                runAnimation.flip();
                idleAnimation.flip();
                jumpAnimation.flip();
                attackAnimation.flip();
                rollAnimation.flip();
                fallAnimation.flip();
            }
            isFacingRight = false;
            positionX = newPositionX;
        }
    }

    public void jump(ArrayList<Rectangle> objects) {
        if (hitBox.y + hitBox.height > 550) {
            jumping = false;
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && grounded) {
            jumpSound.play();
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
        if (hitBox.y > 55 && !jumping && !grounded) {
            positionY = newPositionY;
            timeInAir += 0.1;
        } else {
            timeInAir = 0;
        }
    }

    // Spawner de projeteis
    private void shoot() {
        projectiles.add(new Projectile(hitBox.x + 15, hitBox.y + 30, 10, isFacingRight, arrowTexture));
        shootSound.play(0.3f);
    }

    // Ataque por inpuit
    private void attack() {
        if (Gdx.input.isKeyPressed(Input.Keys.P) && grounded && !running) {
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT) && grounded) {
            if (!rolling)
                rollAnimation.reset();
            rolling = true;
            rollSound.play();
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

    private void removeShoots() {
        for (int i = 0; i < projectiles.size(); i++) {
            if (!projectiles.get(i).getActivity()) {
                projectiles.remove(i);
            }
        }
    }

    private void drawLife(SpriteBatch batch) {
        batch.draw(avatarTexture, 5, 555);
        int posX = 55;
        for (int i = 0; i < life; i++) {
            batch.draw(lifeTexture, posX, 560);
            posX += 35;
        }
    }

    public void update(ArrayList<Rectangle> objects, float dt, SpriteBatch batch) {
        runAnimation.update(dt);
        idleAnimation.update(dt);
        jumpAnimation.update(dt);
        attackAnimation.update(dt);
        rollAnimation.update(dt);
        fallAnimation.update(dt);
        for (Projectile projectile : projectiles) {
            projectile.drawProjectile(batch, 50, 750);
        }
        removeShoots();
        drawLife(batch);
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
