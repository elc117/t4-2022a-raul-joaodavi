package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Phase3 implements Screen {
    Texture forestBackGround;
    Texture woodPlatform1;
    SpriteBatch batch;
    private MedievalGame medievalGame;
    Texture texture;
    // selects what the view port actually displays
    private OrthographicCamera gamecam;
    // how the game fits the devices display
    private Viewport gamePort;
    Player player;

    Hydra hydra;
    private ShapeRenderer shapeRenderer;
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

    public Phase3(MedievalGame game, SpriteBatch batch, Player player) {
        this.medievalGame = game;
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, 800, 600);
        gamePort = new FitViewport(medievalGame.V_WIDTH, medievalGame.V_HEIGHT, gamecam);
        forestBackGround = new Texture("Sceneries/Phase03.jpg");
        this.player = player;
        this.hydra = new Hydra(3, (float)(100), (float)(100), 20, 10, 5, 50);
        this.batch = batch;
        createPhysicShapes();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        music = Gdx.audio.newMusic(Gdx.files.internal("SoundEffects/Musics/Music03.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();
        player.setLife(3);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(forestBackGround, 0, 0);
        batch.draw(player.getAnimation(), player.getPositionX(), player.getPositionY());
        if (hydra.getLife() > 0)
        {
            batch.draw(hydra.getCurrentAnimation(), hydra.getPositionX(), hydra.getPositionY());
            if(hydra.getAction() == 1)
            {
                for (int i = 0; i < 6; i++)
                {
                    batch.draw(hydra.getListOfBalls().get(i).getAnimation(), hydra.getListOfBalls().get(i).getPositionX(), hydra.getListOfBalls().get(i).getPositionY());
                    hydra.getListOfBalls().get(i).update(delta);
                }
            }
            hydra.update(delta, player);
        }
        player.update(phasePhysicShapes, delta, batch);
        isGrounded();
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.end();
        verifyColision();
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

    public void verifyColision() {
        if (hydra.getLife() > 0)
        {
            ArrayList<Projectile> arrows = player.getProjectiles();
            if (player.getHitBox().overlaps(hydra.getHitBox()))
                player.takeHit(hydra);
            for (Projectile arrow : arrows) {
                if (hydra.getHitBox().overlaps(arrow.getProjectile())) {
                    hydra.getHit(arrow.getProjectile().x);
                    arrow.hit();
                }
            }
            for (FlameBall f: hydra.getListOfBalls()){
                if (player.getHitBox().overlaps(f.getHitbox()))
                {
                    player.takeHitNokb(f.getHitbox().x + f.getHitbox().width / 2);
                }
            }
        }
    }

    @Override
    public void dispose() {
        music.dispose();
        shapeRenderer.dispose();
    }
}
