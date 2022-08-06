package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.enemies.Bat;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.MedievalGame;
import com.mygdx.game.player.Player;
import com.mygdx.game.enemies.Wolf;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Phase1 extends Phase implements Screen{
    ArrayList<Rectangle> phasePhysicShapes;
    private boolean spawned;
    private float waitingTime;

    // constructor
    public Phase1(MedievalGame game, SpriteBatch batch, Player player) {
        this.medievalGame = game;
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, 800, 600);
        gamePort = new FitViewport(medievalGame.V_WIDTH, medievalGame.V_HEIGHT, gamecam);
        background = new Texture("Sceneries/Phase01.jpg");
        this.player = player;
        this.batch = batch;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        music = Gdx.audio.newMusic(Gdx.files.internal("SoundEffects/Musics/Music01.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        player.setLife(3);
        music.play();
        enemies = new ArrayList<Enemy>();
        enemiesKilled = 0;
        waitingTime = 0;
        spawned = false;
    }

    // game platforms colision
    @Override
    public void isGrounded() {
        if (player.getHitBox().y <= 55)
            player.setGrounded(true);
        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            player.setGrounded(false);
        else if (player.getHitBox().x > 70 && player.getHitBox().x < 170 && player.getHitBox().y >= 165
                && player.getHitBox().y <= 175)
            player.setGrounded(true);
        else if (player.getHitBox().x > 320 && player.getHitBox().x < 420 && player.getHitBox().y >= 250
                && player.getHitBox().y <= 260)
            player.setGrounded(true);
        else if (player.getHitBox().x > 70 && player.getHitBox().x < 170 && player.getHitBox().y >= 360
                && player.getHitBox().y <= 370)
            player.setGrounded(true);
        else if (player.getHitBox().x > 570 && player.getHitBox().x < 670 && player.getHitBox().y >= 165
                && player.getHitBox().y <= 175)
            player.setGrounded(true);
        else if (player.getHitBox().x > 570 && player.getHitBox().x < 670 && player.getHitBox().y >= 360
                && player.getHitBox().y <= 370)
            player.setGrounded(true);
        else
            player.setGrounded(false);
    }

    // render enemies
    public void drawEnemies(float delta) {
        for (Enemy enemy : enemies) {
            enemy.update(delta, player);
            // shapeRenderer.rect(enemy.getHitBox().getX(), enemy.getHitBox().getY(),
            // enemy.getHitBox().getWidth(), enemy.getHitBox().getHeight());
            if (enemy instanceof Bat) {
                batch.draw(((Bat) enemy).getAnimation(), enemy.getPositionX(), enemy.getPositionY());
            } else if (enemy instanceof Wolf)
                batch.draw(((Wolf) enemy).getAnimation(), enemy.getPositionX(), enemy.getPositionY());
        }
        removeEnemies();
    }

    // creating and spawning enemies in phase
    public void spawnEnemies(float delta) {
        float totalWaitingTime = 5;
        if (waitingTime < totalWaitingTime)
            waitingTime += delta;
        else if (!spawned) {
            spawned = true;
            enemies.add(new Bat(3, 70, 500, 1));
            enemies.add(new Bat(3, 500, 500, 1));
            enemies.add(new Wolf(3, 450, 55, 1));
        }
    }

    // changing level
    private void nextlevel () {
        if (enemiesKilled >= 3) {
            this.dispose();
            medievalGame.setScreen(new Phase2(medievalGame, batch, player));
        }
    }

    // render
    @Override
    public void render(float delta) {
        super.render(delta);
        player.update(phasePhysicShapes, delta, batch);
        spawnEnemies(delta);
        drawEnemies(delta);
        batch.end();
        nextlevel();
    }

    // dispose
    @Override
    public void dispose() {
        music.dispose();
        shapeRenderer.dispose();
        enemies.clear();
    }
}
