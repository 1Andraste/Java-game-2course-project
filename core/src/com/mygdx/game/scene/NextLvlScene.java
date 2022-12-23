package com.mygdx.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
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

public class NextLvlScene extends ScreenAdapter {

    MyGdxGame game;
    Stage stage;
    private String gameStatus = "You win lvl";
    private Label title;
    private Label top1;
    public NextLvlScene(MyGdxGame myGdxGame) {
        this.game = myGdxGame;
        stage = new Stage(new FitViewport(400,600, new OrthographicCamera()));
        setupTable();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void setupTable(){
        Skin skin = Utility.skin;
        Table table = new Table();
        table.setFillParent(true);

        title = new Label(gameStatus, skin);
        top1 = new Label("your score now:" + 0, skin, "small");
        TextButton backButton = new TextButton("Next LVL", skin, "small");

        table.add(title).spaceBottom(30).row();
        table.add(top1).spaceBottom(20).row();
        table.add(backButton).spaceBottom(10).row();
        stage.addActor(table);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.gameScreen);
            }
        });
    }

    public void setScore(int score) {
        top1.setText("your score now:" + score);
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
