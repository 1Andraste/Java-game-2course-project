package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.LinkedList;
import com.mygdx.game.MyGdxGame;

import java.util.Random;

public class BaseEnemy extends Actor{
    //texture
    private Animation<Texture>[] animations =  new Animation[2];
    static Sound shoot = Gdx.audio.newSound(Gdx.files.internal("music/shoot.mp3"));
    private int frame;
    int size;
    int shootDelay;

    //config
    private int hp;
    private static float speed;
    private float stateTime;
    private final Polygon collisionBox = new Polygon();
    protected float x, y;
    private int healPoints;
    //laser
    private LinkedList lasers = new LinkedList();
    float shootingTimer = 0.0F;
    private float shootingDelay = 0.75F;

    //movement
    float directionX = 2F;

    //util
    private int count1 = 0;
    private int count2 = 0;

    public BaseEnemy(float x, float y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        setPosition(x, y);
        lasers.removeAll();
        setSize(size, size);
        float[] vertices = new float[]{0.0F, 0.0F, getWidth(), 0.0F, getWidth(), getHeight(), 0.0F, getHeight()};
        this.collisionBox.setVertices(vertices);
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(this.getColor().r, this.getColor().g, this.getColor().b, this.getColor().a * parentAlpha);
        Texture currentFrame = animations[frame].getKeyFrame(stateTime, true);
        batch.draw(currentFrame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        batch.end();
        this.getCollisionBox();
        if(MyGdxGame.debug) {
            MyGdxGame.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            MyGdxGame.shapeRenderer.setColor(Color.GREEN);
            MyGdxGame.shapeRenderer.polygon(collisionBox.getTransformedVertices());
            MyGdxGame.shapeRenderer.setColor(Color.WHITE);
            MyGdxGame.shapeRenderer.end();
        }
        batch.begin();
    }

    public void act(float delta) {
        super.act(delta);
        removeOffScreenLasers();
        shootingTimer -= delta;
        if(count1 < 11 && frame == 0){
            if(count1 == 10){
                frame = 1;
            }
            count1 += 1;
            count2 = 0;
        }
        else if(count2 < 11 && frame == 1){
            if(count2 == 10){
                frame = 0;
            }
            count2 += 1;
            count1 = 0;
        }
        stateTime += delta;
        shoot();
        if(count1 % 2 == 0 || count2 % 2 == 0) {
            if (getActions().size == 0) {
                Move();
            }
        }
    }

    private void removeOffScreenLasers() {
        for(int j = 0; j < this.getLasers().getSize(); ++j) {
            EnemyLaser laser = (EnemyLaser)this.getLasers().get(j);
            if (laser.isOffScreen()) {
                laser.remove();
                this.getLasers().remove(laser);
            }
        }
    }

    public void Move(){
        if(getX() > x + size * 2 || getX() < x - size * 5) {
            moveBy(0, -10F);
            directionX *= -1;
            moveBy(directionX, 0);
        }
        else if(getX() + 24 < x + 100 && getX() > 0){
            moveBy(directionX, 0);
        }
    }

    public void shoot() {
        Random rand = new Random();
        if(rand.nextInt(shootDelay) == 2){
            shoot.play(0.5F);
            if (this.shootingTimer < 0.0F) {
                float[] pos = this.getLasersSpawnPos();
                EnemyLaser laser1 = new EnemyLaser(pos[0], pos[1]);
                this.getStage().addActor(laser1);
                this.lasers.add(laser1);
                shootingTimer = shootingDelay;
            }

        }
    }


    //getter`s and setter`s
    public void setHealPoints(int healPoints){
        this.healPoints = healPoints;
    }

    public int getHealPoints(){
        return healPoints;
    }

    public void setShootDelay(int shootDelay){
        this.shootDelay = shootDelay;
    }

    public void setAnimations(Animation<Texture>[] animations){
        this.animations = animations;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed){
        BaseEnemy.speed = speed;
    }

    public Polygon getCollisionBox() {
        this.collisionBox.setPosition(this.getX(), this.getY());
        return this.collisionBox;
    }

    public LinkedList getLasers() {
        return this.lasers;
    }

    float[] getLasersSpawnPos() {
        return new float[]{this.getX() + 9.0F, this.getY() + 20.0F, this.getX() + this.getWidth() - 10.0F - 14.0F, this.getY() + 55.0F};
    }
    float[] getLasersSpawnPosEnemy3() {
        return new float[]{this.getX(), this.getY(), this.getX() + this.getWidth() - 10.0F - 14.0F, this.getY() + 30.0F};
    }

}
