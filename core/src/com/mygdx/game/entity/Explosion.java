package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Explosion extends Actor {
    private static Sound explosion;
    private Texture explosionSheet;

    public Explosion(float x, float y, float width, float height) {
        if (explosion == null) {
            explosion = Gdx.audio.newSound(Gdx.files.internal("music/killEnemy.mp3"));
        }

        setSize(width, height);
        setPosition(x, y);
        explosionSheet = new Texture("shipExplosion.png");
        addAction(Actions.sequence(Actions.delay(0.5F), Actions.removeActor()));
        explosion.play(1.0F);
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(explosionSheet, getX(), getY(), getWidth(), getHeight());
    }

    public void act(float delta) {
        super.act(delta);
    }
}
