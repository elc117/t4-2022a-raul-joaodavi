package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PlayScreen;

public class MedievalGame extends Game {
	Texture img;
	// virtual height and width
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public SpriteBatch batch;
	Texture image;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		// setting up screen of the game
		setScreen(new PlayScreen(this));
		image = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		super.render(); // delegates render method to whatever screen is occuring at the time
		batch.begin();
		batch.draw(image, 50, 50);
		batch.end();
	}

}
