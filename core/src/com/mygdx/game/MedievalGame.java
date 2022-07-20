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
	Texture image;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		// setting up screen of the game
		int a = 1;
		if (a == 0)
		{
			setScreen(new Phase1(this, batch));
		}
		else if (a == 1)
		{
			setScreen(new Phase2(this, batch));
		}
	}

	@Override
	public void render () {
		super.render(); // delegates render method to whatever screen is occuring at the time
	}

}
