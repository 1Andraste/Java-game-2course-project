package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Background implements Screen {
    private static final String TAG = Background.class.getSimpleName();
    ShapeRenderer shaper;
    int width;
    int height;
    int numStars;
    int starX[];
    int starY[];
    int bgFrameCounter = 0;
    float accel = 0;
    final private float ACCEL_INC=0.001f;
    public Background() {
        shaper = new ShapeRenderer();

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        numStars = width / 4;
        starX = new int[numStars];
        starY = new int[numStars];

        // create initial random star pattern;
        for (int i = 0; i < starX.length; i++) {
            starX[i] = (int) (Math.random() * (width - 1) + 1);
        }

        for (int i = 0; i < starY.length; i++) {
            starY[i] = (int) (Math.random() * (height - 1) + 1);
        }

    }

    @Override
    public void show() {

    }

    public void render(float delta, boolean paused) {
        shaper.begin(ShapeType.Filled);

        // draw each star
        for (int i = 0; i < numStars; i++) {

            // choose either bright or dim
            if (Math.round(Math.random()) == 1) {
                shaper.setColor(Color.GRAY);
            } else {
                shaper.setColor(Color.DARK_GRAY);
            }

            // draw actual star.
            shaper.rect(starX[i], starY[i], 2, 2);

            // move star down random amounts.
            if (!paused) {
                starY[i] -= (int) ((Math.random() * (5 - 3) + 3) * accel);
                if (accel < 1) {
                    accel += ACCEL_INC;
                } else {
                    accel = 1;
                }
            } else {
                starY[i] -= (int) ((Math.random() * (5 - 3) + 3) * accel);
                if (accel > 0) {
                    accel -= ACCEL_INC;
                } else {
                    accel = 0;
                }
            }
            /*if (accel > 0 && accel < 1) {
                Gdx.app.debug(TAG, "Accel:" + accel);
            }*/
            // move star to top & random x if below screen.
            if (starY[i] < 1) {
                starY[i] = height;
                starX[i] = (int) (Math.random() * (width - 1) + 1);
            }
        }

        shaper.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        render(delta, false);
    }

}