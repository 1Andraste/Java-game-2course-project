package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Random;


public class Boss extends BaseEnemy {
    private Animation<Texture>[] animations = new Animation[2];
    public Boss(float x, float y, int size) {
        super(x, y, size);
        this.animations[0] = new Animation<>(0.5F, new Texture("myskins/enemy1/boss190.png"));
        this.animations[1] = new Animation<>(0.5F,  new Texture("myskins/enemy1/boss290.png"));
        super.setShootDelay(150);
        super.setHealPoints(30);
        super.setAnimations(animations);
    }

    @Override
    public void Move(){
        if(getX() + 90 >= x + size * 2|| getX() < x - size - 90) {
            moveBy(0, -10F);
            directionX *= -1;
            moveBy(directionX, 0);
        }
        else if(getX() + 90 < x + size * 2 && getX() > 0){
            moveBy(directionX, 0);
        }
    }

    public void shoot() {
        Random rand = new Random();
        if(rand.nextInt(super.shootDelay) == 2){
            shoot.play(0.5F);
            if (this.shootingTimer < 0.0F) {
                float[] pos1 = new float[]{this.getX(), this.getY() + 55.0F};
                float[] pos2 = new float[]{this.getX() + this.getWidth() - 10.0F - 14.0F, this.getY() + 55.0F};
                float[] pos3 = new float[]{this.getX() + this.getWidth() + 30, this.getY() + 55.0F};
                EnemyLaser laser1 = new EnemyLaser(pos1[0], pos1[1]);
                EnemyLaser laser4 = new EnemyLaser(pos1[0], pos1[1]  + 20);
                this.getStage().addActor(laser4);
                super.getLasers().add(laser4);
                this.getStage().addActor(laser1);
                super.getLasers().add(laser1);
                EnemyLaser laser2 = new EnemyLaser(pos2[0], pos2[1]);
                EnemyLaser laser3 = new EnemyLaser(pos2[0], pos2[1]  + 20);
                this.getStage().addActor(laser2);
                super.getLasers().add(laser2);
                this.getStage().addActor(laser3);
                super.getLasers().add(laser3);
                EnemyLaser laser5 = new EnemyLaser(pos3[0], pos3[1]);
                EnemyLaser laser6 = new EnemyLaser(pos3[0], pos3[1] + 20);
                this.getStage().addActor(laser5);
                super.getLasers().add(laser5);
                this.getStage().addActor(laser6);
                super.getLasers().add(laser6);
            }
        }
    }
}
