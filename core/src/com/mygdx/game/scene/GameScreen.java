package com.mygdx.game.scene;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Utility;
import com.mygdx.game.entity.*;
import com.mygdx.game.LinkedList;

public class GameScreen extends ScreenAdapter {
    MyGdxGame game;

    private Ship playerShip;
    public static LinkedList enemy;
    private final Label scoreLabel;
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Stage stage1;
    private Stage stage2;
    private Stage stage3;
    private Stage stage4;
    private Texture earth;
    private Texture healPoint;
    private int hp = 3;
    private int currentStage = 1;
    public static int score = 0;
    public GameScreen(MyGdxGame game) {
        Skin skin = Utility.skin;
        this.game = game;
        stage1 = new Stage(new FitViewport(400,600, new OrthographicCamera()));
        stage2 = new Stage(new FitViewport(400,600, new OrthographicCamera()));
        stage3 = new Stage(new FitViewport(400,600, new OrthographicCamera()));
        stage4 = new Stage(new FitViewport(400,600, new OrthographicCamera()));
        camera = new OrthographicCamera();
        viewport = new StretchViewport(400, 600, camera);
        playerShip = new Ship(200, 30,
                32, 32);
        scoreLabel = new Label(Integer.toString(score), skin, "default");
        scoreLabel.setPosition(20, 540);
        stage1.addActor(playerShip);
        stage2.addActor(playerShip);
        enemy = new LinkedList();
        earth = new Texture("earth.png");
        healPoint = new Texture("myskins/healPoint.png");
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        switch (currentStage) {
            case 1:
                score = 0;
                scoreLabel.setText(score);
                spawnLvl1();
                break;
            case 2:
                spawnLvl2();
                break;
            case 3:
                spawnLvl3();
                break;
            case 4:
                spawnLvl4();
                break;
        }
    }

    private void spawnLvl1() {
        stage1.addActor(playerShip);
        stage1.addActor(scoreLabel);
        for(int i = 300; i > 100; i -= 40){
            Enemy1 enemy11 = new Enemy1(i, 400, 24);
            enemy.add(enemy11);
            stage1.addActor(enemy11);
            enemy11.toBack();
        }
        playerShip.toBack();
    }

    private void spawnLvl2() {
        stage2.addActor(playerShip);
        stage2.addActor(scoreLabel);
        for(int i = 300; i > 100; i -= 40){
            Enemy1 enemy11 = new Enemy1(i, 350, 24);
            Enemy2 enemy22 = new Enemy2(i, 400, 24);
            Enemy1 enemy33 = new Enemy1(i, 450, 24);
            enemy.add(enemy11);
            enemy.add(enemy22);
            enemy.add(enemy33);
            stage2.addActor(enemy22);
            stage2.addActor(enemy11);
            stage2.addActor(enemy33);
            enemy33.toBack();
            enemy11.toBack();
            enemy22.toBack();
        }
        playerShip.toBack();
    }

    private void spawnLvl3() {
        stage3.addActor(playerShip);
        stage3.addActor(scoreLabel);
        for(int i = 300; i > 100; i -= 40){
            Enemy1 enemy11 = new Enemy1(i, 450, 24);
            Enemy2 enemy22 = new Enemy2(i, 500, 24);
            enemy.add(enemy11);
            enemy.add(enemy22);
            stage3.addActor(enemy22);
            stage3.addActor(enemy11);
            enemy11.toBack();
            enemy22.toBack();
        }
        for(int i = 300; i >= 100; i -= 80){
            Enemy3 enemy11 = new Enemy3(i, 350, 24);
            enemy.add(enemy11);
            stage3.addActor(enemy11);
            enemy11.toBack();
        }
        playerShip.toBack();
    }

    private void spawnLvl4() {
        stage4.addActor(playerShip);
        stage4.addActor(scoreLabel);
        Boss boss = new Boss(200, 500, 90);
        enemy.add(boss);
        stage4.addActor(boss);
        playerShip.toBack();
    }

    @Override
    public void render(float delta) {
        this.update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Utility.background.render(delta, true);
        batch.begin();
        batch.draw(earth,0,0);
        for(int i = 0; i < hp; i++){
            batch.draw(healPoint, 350 - 20 * i, 550);
        }
        batch.end();
        switch (currentStage){
            case 1:
                stage1.draw();
                break;
            case 2:
                stage2.draw();
                break;
            case 3:
                stage3.draw();
                break;
            case 4:
                stage4.draw();
                break;
        }
    }

    private void update(float delta){
        switch (currentStage){
            case 1:
                checkGameStatus();
                handleCollisions();
                stage1.act(delta);
                break;
            case 2:
                checkGameStatus();
                handleCollisions();
                stage2.act(delta);
                break;
            case 3:
                checkGameStatus();
                handleCollisions();
                stage3.act(delta);
                break;
            case 4:
                checkGameStatus();
                handleCollisions();
                stage4.act(delta);
                break;
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    public void checkGameStatus(){
        if(enemy.getSize() <= 0){
            if(currentStage < 4){
                System.out.println("Next lvl");
                currentStage++;
                game.nextLvlScene.setScore(score);
                game.setScreen(game.nextLvlScene);
            }
            else {
                System.out.println("Game is end");
                game.endScreen.setGameStatus("You win");
                game.endScreen.setScore(score);
                game.setScreen(game.endScreen);
            }
        }
        else{
            for(int i = 0; i < enemy.getSize(); i++){
                Actor actor = enemy.get(i);
                BaseEnemy enemy = (BaseEnemy) actor;
                if (enemy.getY() <= 80) {
                    System.out.println("Game is end");
                    removeAllEnemys();
                    game.endScreen.setGameStatus("You lose");
                    game.endScreen.setScore(score);
                    game.setScreen(game.endScreen);
                    break;
                }
            }
        }
    }

    private void removeAllEnemys() {
        for(int i = 0; i < enemy.getSize(); i++) {
            Actor actor = enemy.get(i);
            BaseEnemy enemy = (BaseEnemy) actor;
            enemy.remove();
        }
        enemy.removeAll();
    }

    private void handleCollisions() {
        int i;
        for(i = 0; i < enemy.getSize(); ++i) {
            BaseEnemy enemy11 = (BaseEnemy)enemy.get(i);
            for(int j = 0; j < playerShip.getLasers().getSize(); ++j) {
                Laser laser = (Laser)playerShip.getLasers().get(j);
                if (Intersector.overlapConvexPolygons(enemy11.getCollisionBox(), laser.getCollisionBox())) {
                    if(enemy11.getHealPoints() == 1){
                        Explosion explosion = new Explosion(enemy11.getX(), enemy11.getY(), enemy11.getWidth(), enemy11.getHeight());
                        switch(currentStage){
                            case 1:
                                explosion.toBack();
                                stage1.addActor(explosion);
                                break;
                            case 2:
                                explosion.toBack();
                                stage2.addActor(explosion);
                                break;
                            case 3:
                                explosion.toBack();
                                stage3.addActor(explosion);
                                break;
                            case 4:
                                explosion.toBack();
                                stage4.addActor(explosion);
                                break;
                            default:
                                System.out.println("Error stage");
                        }
                        score += 200;
                        scoreLabel.setText(score);
                        enemy11.remove();
                        enemy.remove(enemy11);
                    }
                    else{
                        enemy11.setHealPoints(enemy11.getHealPoints() - 1);
                    }

                    playerShip.getLasers().remove(laser);
                    laser.remove();
                    break;
                }
            }
            for(int k = 0; k < enemy11.getLasers().getSize(); k++){
                EnemyLaser laser = (EnemyLaser)enemy11.getLasers().get(k);
                if(Intersector.overlapConvexPolygons(playerShip.getCollisionBox(), laser.getCollisionBox())){
                    if(playerShip.getHealPoints() <= 0) {
                        removeAllEnemys();
                        System.out.println("You destroyed");
                        game.endScreen.setGameStatus("You lose");
                        game.endScreen.setScore(score);
                        game.setScreen(game.endScreen);
                    }
                    else{
                        playerShip.setHealPoints(playerShip.getHealPoints() - 1);
                        hp--;
                        enemy11.getLasers().remove(laser);
                        laser.remove();
                    }
                    break;
                }
            }
        }
    }
    public void setLvl(int currentStage){
        this.currentStage = currentStage;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        batch.dispose();
        Laser.dispose();
    }
}