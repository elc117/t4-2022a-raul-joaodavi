package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Bat;
import com.mygdx.game.Enemy;
import com.mygdx.game.MedievalGame;
import com.mygdx.game.Player;
import com.mygdx.game.Projectile;
import com.mygdx.game.Wolf;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Phase1 implements Screen {
    Texture forestBackGround;
    Texture woodPlatform1;
    SpriteBatch batch;
    private MedievalGame medievalGame;
    Texture texture;
    // selects what the view port actually displays
    private OrthographicCamera gamecam;
    // how the game fits the devices display
    private Viewport gamePort;
    private int enemiesKilled;
    Player player;
    private ShapeRenderer shapeRenderer;
    private ArrayList<Enemy> enemies;
    ArrayList<Rectangle> phasePhysicShapes;
    Music music;

    private void createPhysicShapes() {
        phasePhysicShapes = new ArrayList<Rectangle>();
        // ground
        phasePhysicShapes.add(new Rectangle(0, 0, MedievalGame.V_WIDTH, (float) MedievalGame.V_HEIGHT / 4));
    }

    public void isGrounded() {
        if (player.getHitBox().y <= 55)
            player.setGrounded(true);
        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            player.setGrounded(false);
        else if (player.getHitBox().x > 70 && player.getHitBox().x < 170 && player.getHitBox().y >= 165 && player.getHitBox().y <= 175)
            player.setGrounded(true);
        else if (player.getHitBox().x > 320 && player.getHitBox().x < 420 && player.getHitBox().y >= 250 && player.getHitBox().y <= 260)
            player.setGrounded(true);
        else if (player.getHitBox().x > 70 && player.getHitBox().x < 170 && player.getHitBox().y >= 360 && player.getHitBox().y <= 370)
            player.setGrounded(true);
        else if (player.getHitBox().x > 570 && player.getHitBox().x < 670 && player.getHitBox().y >= 165 && player.getHitBox().y <= 175)
            player.setGrounded(true);
        else if (player.getHitBox().x > 570 && player.getHitBox().x < 670 && player.getHitBox().y >= 360 && player.getHitBox().y <= 370)
            player.setGrounded(true);
        else
            player.setGrounded(false);
    }

    public void verifyColision() {
        ArrayList<Projectile> arrows = player.getProjectiles();
        for (Enemy enemy : enemies) {
            if(player.getHitBox().overlaps(enemy.getHitBox()))
                player.takeHit(enemy.getHitBox().x);
            for (Projectile arrow : arrows) {
                if(enemy.getHitBox().overlaps(arrow.getProjectile())) {
                    enemy.getHit(arrow.getProjectile().x);
                    arrow.hit();
                }
            }
        }
    }  

    private void removeEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getLife() <= 0) {
                enemies.remove(i);
                enemiesKilled++;
            }
        }
    }

    public void drawEnemies (float delta) {
        for (Enemy enemy : enemies) {
            enemy.update(delta, player);
            //shapeRenderer.rect(enemy.getHitBox().getX(), enemy.getHitBox().getY(), enemy.getHitBox().getWidth(), enemy.getHitBox().getHeight());
            if(enemy instanceof Bat) {
                batch.draw(((Bat)enemy).getAnimation(), enemy.getPositionX(), enemy.getPositionY());
            } else if (enemy instanceof Wolf)
                batch.draw(((Wolf)enemy).getAnimation(), enemy.getPositionX(), enemy.getPositionY());
        }
        removeEnemies();
    }

    public Phase1(MedievalGame game, SpriteBatch batch, Player player) {
        this.medievalGame = game;
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, 800, 600);
        gamePort = new FitViewport(medievalGame.V_WIDTH, medievalGame.V_HEIGHT, gamecam);
        forestBackGround = new Texture("Sceneries/Phase01.jpg");
        this.player = player;
        this.batch = batch;
        createPhysicShapes();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        music = Gdx.audio.newMusic(Gdx.files.internal("SoundEffects/Musics/Music01.mp3"));
		music.setLooping(true);
        music.setVolume(0.3f);
        player.setLife(3);
		music.play();
        enemies = new ArrayList<Enemy>();
        enemiesKilled = 0;
        enemies.add(new Bat(3, 70, 500, 1));
        enemies.add(new Bat(3, 500, 500, 1));
        enemies.add(new Wolf(3, 450, 55, 1));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        batch.begin();
        batch.draw(forestBackGround, 0, 0);
        batch.draw(player.getAnimation(), player.getPositionX(), player.getPositionY());
        player.update(phasePhysicShapes, delta, batch);
        isGrounded();
        verifyColision();
        drawEnemies(delta);
        batch.end();
        shapeRenderer.rect(player.getHitBox().getX(), player.getHitBox().getY(), player.getHitBox().getWidth(), player.getHitBox().getHeight());
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        // ajusting viewsoport to devices screen size
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        music.dispose();
        shapeRenderer.dispose();
    }
}
