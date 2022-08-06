package com.mygdx.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.MedievalGame;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.Projectile;

import java.util.ArrayList;

public abstract class Phase implements Screen {
    protected Texture background;
    SpriteBatch batch;
    protected MedievalGame medievalGame;
    // selects what the view port actually displays
    protected OrthographicCamera gamecam;
    // how the game fits the devices display
    protected Viewport gamePort;
    protected Player player;
    protected ShapeRenderer shapeRenderer;
    protected ArrayList<Enemy> enemies;
    protected int enemiesKilled;
    protected Music music;

    // game player and enemies attack colision sistem
    public void verifyColision() {
        ArrayList<Projectile> arrows = player.getProjectiles();
        for (Enemy enemy : enemies) {
            if(player.getHitBox().overlaps(enemy.getHitBox()))
                player.takeHit(enemy);
            for (Projectile arrow : arrows) {
                if(enemy.getHitBox().overlaps(arrow.getProjectile())) {
                    enemy.getHit(arrow.getProjectile().x);
                    arrow.hit();
                }
            }
        }
    }

    // killing enemies
    protected void removeEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getLife() <= 0) {
                enemies.remove(i);
                enemiesKilled++;
            }
        }
    }

    @Override
    public void show() {

    }

    // render
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(player.getAnimation(), player.getPositionX(), player.getPositionY());
        isGrounded();
        verifyColision();
    }

    // ajusts screen to window size
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }
    public void isGrounded(){

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
