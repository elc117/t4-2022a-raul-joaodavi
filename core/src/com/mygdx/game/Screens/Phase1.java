package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MedievalGame;
import com.mygdx.game.Player;

import java.awt.*;
import java.util.ArrayList;

public class Phase1 implements Screen {
    Texture forestBackGround;
    SpriteBatch batch;
    private MedievalGame medievalGame;
    Texture texture;
    // selects what the view port actually displays
    private OrthographicCamera gamecam;
    // how the game fits the devices display
    private Viewport gamePort;
    Player player;

    ArrayList<Rectangle> phasePhysicShapes;


    private void createPhysicShapes()
    {
        phasePhysicShapes = new ArrayList<Rectangle>();
    }

    public Phase1(MedievalGame game, SpriteBatch batch, Player player)
    {
        this.medievalGame = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(medievalGame.V_WIDTH, medievalGame.V_HEIGHT, gamecam);
        forestBackGround = new Texture("Sceneries/forest.jpg");
        this.player = player;
        this.batch = batch;
        createPhysicShapes();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.render();
        batch.begin();
        batch.draw(forestBackGround, 0, 0);
        batch.draw(player.getMainImage(), player.getPositionX(), player.getPositionY());
        batch.end();
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
