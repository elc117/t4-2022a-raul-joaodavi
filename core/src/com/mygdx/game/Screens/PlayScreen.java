package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MedievalGame;

public class PlayScreen implements Screen {
    Texture image;
    SpriteBatch batch;
    private MedievalGame medievalGame;
    Texture texture;
    // selects what the view port actually displays
    private OrthographicCamera gamecam;
    // how the game fits the devices display
    private Viewport gamePort;


    public PlayScreen(MedievalGame game, SpriteBatch batch)
    {
        this.medievalGame = game;
        gamecam = new OrthographicCamera();
        // fit viewport is a viewport that mantains the game's aspect ratio
        gamePort = new FitViewport(medievalGame.V_WIDTH, medievalGame.V_HEIGHT, gamecam);
        image = new Texture("badlogic.jpg");
        this.batch = batch;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(image, 50, 50);
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
