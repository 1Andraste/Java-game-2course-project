package com.mygdx.game.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.LinkedList;
import com.mygdx.game.MyGdxGame;

public class Ship extends Actor {

    public float movementSpeed;
    private static Sound shooting = Gdx.audio.newSound(Gdx.files.internal("music/shotFired.mp3"));;
    private float shootingDelay;
    private int score;
    int lives;
    private float x, y;
    private Polygon collisionBox = new Polygon();
    private LinkedList lasers = new LinkedList();
    Texture shipTexture = new Texture("ship32.png");
    private float actualHealth;
    private float speed;
    private float shootingTimer = 0.0F;
    private int healPoints = 3;

    public Ship(float x, float y,
                float width, float height){
        this.reset();
        this.x = x;
        this.y = y;
        setPosition(x, y);
        float[] vertices = new float[]{0, 0, width, 0.0F, width/2, height};
        this.collisionBox.setVertices(vertices);
        lives = 3;
    }
    public void reset() {
        setX((float)(Gdx.graphics.getWidth() / 2 - 32));
        lasers.removeAll();
        Laser.speed = 500.0F;
        movementSpeed = 275.0F;
        shootingDelay = 0.75F;
        score = 0;
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(this.getColor().r, this.getColor().g, this.getColor().b, this.getColor().a * parentAlpha);
        batch.draw(shipTexture, this.getX(), this.getY());
        batch.end();
        this.getCollisionBox();
        batch.begin();
    }

    public void act(float delta) {
        super.act(delta);
        shootingTimer -= delta;
        handleInput(delta);
        removeOffScreenLasers();
    }

    public Polygon getCollisionBox() {
        this.collisionBox.setPosition(this.getX(), this.getY());
        return this.collisionBox;
    }

    private void removeOffScreenLasers() {
        for(int j = 0; j < this.getLasers().getSize(); ++j) {
            Laser laser = (Laser)this.getLasers().get(j);
            if (laser.isOffScreen()) {
                laser.remove();
                this.getLasers().remove(laser);
            }
        }
    }

    public LinkedList getLasers() {
        return this.lasers;
    }

    private void handleInput(float delta) {
        if ((Gdx.input.isKeyPressed(21) || Gdx.input.isKeyPressed(29)) && !Gdx.input.isKeyPressed(22) && !Gdx.input.isKeyPressed(32)) {
            if(getX() > 0){
                this.moveBy(-this.speed * delta, 0.0F);
                this.setX(this.getX() - 3);
            }
            if (Gdx.input.isKeyPressed(62)){
                shoot();
            }
        }
        else if ((Gdx.input.isKeyPressed(22) || Gdx.input.isKeyPressed(32)) && !Gdx.input.isKeyPressed(21) && !Gdx.input.isKeyPressed(29)) {
            if(getX() < 370){
                this.moveBy(-this.speed * delta, 0.0F);
                this.setX(this.getX() + 3);
            }
            if (Gdx.input.isKeyPressed(62)){
                shoot();
            }
        }
        if (Gdx.input.isKeyPressed(62)){
            shoot();
        }

    }

    private void shoot() {
        if (this.shootingTimer < 0.0F) {
            float[] pos = this.getLasersSpawnPos();
            Laser laser1 = new Laser(pos[0], pos[1]);
            this.getStage().addActor(laser1);
            this.lasers.add(laser1);
            shootingTimer = shootingDelay;
            shooting.play(1.0F);
        }
    }

    private float[] getLasersSpawnPos() {
        return new float[]{this.getX() + 9.0F, this.getY() + 20.0F, this.getX() + this.getWidth() - 10.0F - 14.0F, this.getY() + 55.0F};
    }

    public int getHealPoints(){
        return healPoints;
    }

    public void setHealPoints(int healPoints){
        this.healPoints = healPoints;
    }
}



































