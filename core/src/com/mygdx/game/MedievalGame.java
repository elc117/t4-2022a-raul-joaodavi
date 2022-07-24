package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.Phase1;
import com.mygdx.game.Screens.Phase2;

public class MedievalGame extends Game {
	// virtual height and width
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 600;
	public SpriteBatch batch;

	public Player player;

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player("XAOZIN", "Character/Run/tile001.png", 50, 50, 5, 6, 2);
		// setting up screen of the game
		int a = 1;
		setScreen(new Phase1(this, batch, player));
	}

	@Override
	public void render () {
		super.render(); // delegates render method to whatever screen is occuring at the time
	}

}
