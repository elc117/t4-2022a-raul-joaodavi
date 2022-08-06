package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input;
import com.mygdx.game.enemies.FlameBall;
import com.mygdx.game.enemies.Hydra;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.Projectile;

import java.util.ArrayList;

public class Phase3 extends Phase{
    Hydra hydra;
    ArrayList<Rectangle> phasePhysicShapes;

    // constructor
    public Phase3(MedievalGame game, SpriteBatch batch, Player player) {
        this.medievalGame = game;
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, 800, 600);
        gamePort = new FitViewport(medievalGame.V_WIDTH, medievalGame.V_HEIGHT, gamecam);
        background = new Texture("Sceneries/Phase03.jpg");
        this.player = player;
        this.hydra = new Hydra(3, (float)(100), (float)(100), 20, 10, 5, 50);
        this.batch = batch;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        music = Gdx.audio.newMusic(Gdx.files.internal("SoundEffects/Musics/Music03.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();
        player.setLife(3);
    }

    // game platforms physics
    @Override
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

    // render
    @Override
    public void render(float delta) {
        super.render(delta);
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
        batch.end();
    }

    // game hit and damage system
    @Override
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

    // dispose
    @Override
    public void dispose() {
        music.dispose();
        shapeRenderer.dispose();
    }
}
