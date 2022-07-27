package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MedievalGame;
import com.mygdx.game.Player;
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
    Player player;
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

    public Phase1(MedievalGame game, SpriteBatch batch, Player player) {
        this.medievalGame = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(medievalGame.V_WIDTH, medievalGame.V_HEIGHT, gamecam);
        forestBackGround = new Texture("Sceneries/Phase01.jpg");
        this.player = player;
        this.batch = batch;
        createPhysicShapes();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        music = Gdx.audio.newMusic(Gdx.files.internal("SoundEffects/Music01.mp3"));
		music.setLooping(true);
        music.setVolume(0.3f);
		music.play();
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
        player.update(phasePhysicShapes, delta, batch);
        isGrounded();
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        //shapeRenderer.rect(player.getHitBox().getX(), player.getHitBox().getY(), player.getHitBox().getWidth(), player.getHitBox().getHeight());
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

    }
}
