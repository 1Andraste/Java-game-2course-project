package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public class EnemyLaser extends Actor {

    public static float speed = -200.0F;
    private static Texture texture;
    private Polygon collisionBox = new Polygon();

    EnemyLaser(float x, float y) {
        if (texture == null) {
            texture = new Texture("enemyLaser.png");
        }
        this.setPosition(x, y);
        this.setSize((float)texture.getWidth(), (float)texture.getHeight());
        float[] vertices = new float[]{0.0F, 0.0F, getWidth(), 0.0F, getWidth(), getHeight(), 0.0F, getHeight()};
        collisionBox.setVertices(vertices);
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(this.getColor().r, this.getColor().g, this.getColor().b, this.getColor().a * parentAlpha);
        batch.draw(texture, this.getX(), this.getY());
        batch.end();
//        this.getCollisionBox();
//        MyGdxGame.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        MyGdxGame.shapeRenderer.setColor(Color.CYAN);
//        MyGdxGame.shapeRenderer.polygon(collisionBox.getTransformedVertices());
//        MyGdxGame.shapeRenderer.setColor(Color.WHITE);
//        MyGdxGame.shapeRenderer.end();
        batch.begin();
    }

    public void act(float delta) {
        super.act(delta);
        this.moveBy(0.0F, (speed * delta));
    }

    public Polygon getCollisionBox() {
        this.collisionBox.setPosition(this.getX(), this.getY());
        return this.collisionBox;
    }

    public static void dispose() {
        texture.dispose();
    }

    boolean isOffScreen() {
        return this.getY() + this.getHeight() > (float)Gdx.graphics.getHeight();
    }

}
