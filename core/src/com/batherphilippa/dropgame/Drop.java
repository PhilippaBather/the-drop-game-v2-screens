package com.batherphilippa.dropgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.batherphilippa.dropgame.manager.GameState;
import com.batherphilippa.dropgame.screen.SplashScreen;

/**
 * Drop class - entry point to the game.
 */
public class Drop extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public GameState gameState;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX' default Arial font
		this.gameState = GameState.RUNNING;
		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
