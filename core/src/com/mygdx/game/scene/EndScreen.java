package com.mygdx.game.scene;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Utility;

public class EndScreen extends ScreenAdapter {
    private Preferences PREFS;
    MyGdxGame game;
    Stage stage;
    private String gameStatus = "You Lose";
    private Label title;
    private Label top1;
    private int score = 0;
    public EndScreen(MyGdxGame myGdxGame) {
        PREFS = Gdx.app.getPreferences("My Preferences");
        this.game = myGdxGame;
        stage = new Stage(new FitViewport(400,600, new OrthographicCamera()));
        setupTable();
    }

    public void setScore(int score) {
        this.score = score;
        top1.setText("score: " + score);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        PREFS.putInteger("You", score);
        PREFS.flush();
    }

    public void setupTable(){
        Skin skin = Utility.skin;
        Table table = new Table();
        table.setFillParent(true);

        title = new Label(gameStatus, skin);
        top1 = new Label("score: " + score, skin, "medium");
        TextButton backButton = new TextButton("main menu", skin, "small");

        table.add(title).spaceBottom(30).row();
        table.add(top1).spaceBottom(20).row();
        table.add(backButton).spaceBottom(10).row();
        stage.addActor(table);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menu);
            }
        });
    }

    public void setGameStatus(String gameStatus) {
        title.setText(gameStatus);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Utility.background.render(delta, true);
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
