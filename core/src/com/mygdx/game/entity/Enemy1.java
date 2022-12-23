package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Enemy1 extends BaseEnemy {
    private Animation<Texture>[] animations = new Animation[2];;
    public Enemy1(float x, float y, int size) {
        super(x, y, size);
        this.animations[0] = new Animation<>(0.5F, new Texture("myskins/enemy1/bee124.png"));
        this.animations[1] = new Animation<>(0.5F,  new Texture("myskins/enemy1/bee224.png"));
        super.setShootDelay(200);
        super.setHealPoints(1);
        super.setAnimations(animations);
    }
}
