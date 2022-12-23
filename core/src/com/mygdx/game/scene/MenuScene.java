package com.mygdx.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
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


public class MenuScene extends ScreenAdapter {
    MyGdxGame game;
    private Stage stage;
    public MenuScene(MyGdxGame myGdxGame) {
        this.game = myGdxGame;
        stage = new Stage(new FitViewport(400,800, new OrthographicCamera()));
        setupTable();
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("music/galagaTheme.mp3"));
    }
    private Music bgMusic;

    @Override
    public void show(){
        bgMusic.setVolume(1.0F);
        bgMusic.play();
        Gdx.input.setInputProcessor(stage);
    }

    void setupTable(){
        Skin skin = Utility.skin;
        Table table = new Table();
        table.setFillParent(true);

        Label title = new Label("myNOTgalaga", skin);
        TextButton loadGameButton = new TextButton("Start", skin, "small");
        TextButton scoreButton = new TextButton("Score", skin, "small");
        TextButton exitButton = new TextButton("Exit", skin, "small");
        table.add(title).spaceBottom(75).row();
        table.add(loadGameButton).spaceBottom(13).row();
        table.add(scoreButton).spaceBottom(13).row();
        table.add(exitButton).spaceBottom(13).row();
        stage.addActor(table);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        loadGameButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.gameScreen.setLvl(1);
                bgMusic.stop();
                game.setScreen(game.gameScreen);
            }
        });

        scoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.score);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Utility.background.render(delta, true);
        stage.draw();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
