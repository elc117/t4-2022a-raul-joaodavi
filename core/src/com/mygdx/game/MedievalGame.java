package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;
import com.mygdx.game.Screens.Phase1;
import com.mygdx.game.Screens.Phase2;
import com.mygdx.game.Screens.Phase3;

public class MedievalGame extends Game {
	// virtual height and width
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 600;
	private Phase1 phase1;
	private Phase2 phase2;
	private Phase3 phase3;
	private int currentPhase;
	private boolean changedPhase;
	public SpriteBatch batch;

	public Player player;

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(55, 55, 5, 6, 2);
		// setting up screen of the game
		currentPhase = 1;
		changedPhase = false;
		phase1 = new Phase1(this, batch, player);
		setScreen(phase1);
	}

	@Override
	public void render () {
		temporaryScreenChange();
		super.render(); // delegates render method to whatever screen is occuring at the time
	}

	private void temporaryScreenChange () {
		if(Gdx.input.isKeyJustPressed(Input.Keys.N)) {
			currentPhase++;
			changedPhase = true;
		} else {
			changedPhase = false;
		}
		
		if(currentPhase == 2 && changedPhase) {
			phase1.dispose();
			phase2 = new Phase2(this, batch, player);
			setScreen(phase2);
		} else if(currentPhase == 3 && changedPhase) {
			phase2.dispose();
			phase3 = new Phase3(this, batch, player);
			setScreen(phase3);
		}
	}

}
