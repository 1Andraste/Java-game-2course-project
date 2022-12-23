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


public class Enemy2 extends BaseEnemy {
    private Animation<Texture>[] animations = new Animation[2];;
    public Enemy2(float x, float y, int size) {
        super(x, y, size);
        this.animations[0] = new Animation<>(0.5F, new Texture("myskins/enemy1/butterfly124.png"));
        this.animations[1] = new Animation<>(0.5F,  new Texture("myskins/enemy1/butterfly224.png"));
        super.setShootDelay(700);
        super.setHealPoints(2);
        super.setAnimations(animations);
    }
}
