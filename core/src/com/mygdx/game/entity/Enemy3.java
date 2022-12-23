package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Random;


public class Enemy3 extends BaseEnemy {
    private Animation<Texture>[] animations = new Animation[2];
    public Enemy3(float x, float y, int size) {
        super(x, y, size);
        this.animations[0] = new Animation<>(0.5F, new Texture("myskins/enemy1/scorpion124.png"));
        this.animations[1] = new Animation<>(0.5F,  new Texture("myskins/enemy1/scorpion224.png"));
        super.setShootDelay(550);
        super.setHealPoints(3);
        super.setAnimations(animations);
    }
    public void shoot() {
        Random rand = new Random();
        if(rand.nextInt(super.shootDelay) == 2){
            shoot.play(0.5F);
            if (this.shootingTimer < 0.0F) {
                float[] pos = this.getLasersSpawnPosEnemy3();
                EnemyLaser laser1 = new EnemyLaser(pos[0], pos[1]);
                this.getStage().addActor(laser1);
                super.getLasers().add(laser1);
                EnemyLaser laser2 = new EnemyLaser(pos[2], pos[3]);
                this.getStage().addActor(laser2);
                super.getLasers().add(laser2);
                System.out.println(getY());
            }

        }
    }
}
