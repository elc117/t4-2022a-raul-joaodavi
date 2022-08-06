package com.mygdx.game.midGameUI;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MedievalGame;
import com.mygdx.game.player.Player;

import java.util.ArrayList;

public class Story implements Screen {
    private int current;
    private int currentPhase;
    private SpriteBatch batch;
    ArrayList<Texture> images;
    public Story(MedievalGame game, SpriteBatch batch, Player player, int currentPhase)
    {
        images = new ArrayList<>();
        images.add(new Texture("Story/1.png"));
        images.add(new Texture("Story/2.png"));
        images.add(new Texture("Story/archer.png"));
        images.add(new Texture("Story/castelo.jpg"));
        images.add(new Texture("Story/desertt.jpg"));
        images.add(new Texture("Story/forest.jpeg"));
        images.add(new Texture("Story/hydra.png"));
        images.add(new Texture("Story/majaro.png"));
        images.add(new Texture("Story/queen.jpg"));
        images.add(new Texture("Story/ubuntu.jpg"));
        this.currentPhase = currentPhase;
        this.batch = batch;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(images.get(current), 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
