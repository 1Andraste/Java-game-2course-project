package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.scene.*;
import com.mygdx.game.scene.MenuScene;

public class MyGdxGame extends Game {
	public static boolean debug = false;
	public SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;
	public MenuScene menu;
	public GameScreen gameScreen;
	public EndScreen endScreen;
	public ScoreScreen score;
	public NextLvlScene nextLvlScene;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		menu = new MenuScene(this);
		gameScreen = new GameScreen(this);
		score = new ScoreScreen(this);
		endScreen = new EndScreen(this);
		nextLvlScene = new NextLvlScene(this);
		setScreen(menu);
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}

//	public void createSkin(){
//		skin.add();
//	}
}
