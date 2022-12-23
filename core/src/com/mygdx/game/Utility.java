package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Utility {
    public static Background background = new Background();
    public final static String PATH_ATLAS = "myskins/galagaSkin.atlas";
    public static TextureAtlas ATLAS = new TextureAtlas(PATH_ATLAS);
    public static String PATH_SKIN =  "myskins/galagaSkin.json";
    public static Skin skin = new Skin(Gdx.files.internal(PATH_SKIN), ATLAS);
}


