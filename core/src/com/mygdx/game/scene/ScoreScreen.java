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


public class ScoreScreen extends ScreenAdapter {

    MyGdxGame game;
    private String[] names = {"Andraste", "Kirles", "Tilda", "Nikita", "Bunnyhop"};
    private int[] scores = {99999, 10000, 1000, 500, 100};
    private Preferences PREFS;
    Stage stage;
    private int playerScore = 0;
    public ScoreScreen(MyGdxGame myGdxGame) {
        this.game = myGdxGame;
        PREFS = Gdx.app.getPreferences("My Preferences");
        stage = new Stage(new FitViewport(400,600, new OrthographicCamera()));
        setupTable();
        PREFS = Gdx.app.getPreferences("My Preferences");
    }

    @Override
    public void show() {
        setupTable();
        Gdx.input.setInputProcessor(stage);

    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setupTable(){
        stage.clear();
        Skin skin = Utility.skin;
        Table table = new Table();
        table.setFillParent(true);

        Label title = new Label("top score", skin);
        table.add(title).spaceBottom(30).row();
        boolean flag = true;
        for(int i = 0, k = 5; i < k; i++){
            Label top;
            if((PREFS.getInteger("You")) > scores[i] && flag){
                top = new Label((PREFS.getInteger("You") + " - You"), skin, "medium");
                flag = false;
                i--;
                k--;
            }
            else{
                top = new Label((scores[i] + " - " + names[i]), skin, "medium");
            }
            table.add(top).spaceBottom(10).row();
        }
        TextButton backButton = new TextButton("Back", skin, "small");

        table.add(backButton).spaceBottom(10).row();
        stage.addActor(table);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menu);
            }
        });
    }

    private void update(float delta){

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Utility.background.render(delta, true);
        update(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
