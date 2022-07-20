package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MedievalGame;

import javax.swing.text.View;
import java.awt.*;

public class Hud {
    // stage holds a table that organizes our labels inside it
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label medievalLabel;

    public Hud(SpriteBatch sb)
    {
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(MedievalGame.V_WIDTH, MedievalGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        // table starts at the top of our stage
        table.top();
        // table starts at the size of stage
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer));
        scoreLabel = new Label(String.format("%06d", score));
        timeLabel = new Label("TIME ");
        levelLabel = new Label("1-1");
        worldLabel = new Label("WORLD");
        medievalLabel = new Label("Medieval");

        table.add((CharSequence) medievalLabel).expandX().padTop(10);
        table.add((CharSequence) worldLabel).expandX().padTop(10);
        table.add((CharSequence) timeLabel).expandX().padTop(10);
        table.row();
        table.add((CharSequence) scoreLabel).expandX();
        table.add((CharSequence) levelLabel).expandX();
        table.add((CharSequence) countdownLabel).expandX();

        stage.addActor(table);
    }
}
