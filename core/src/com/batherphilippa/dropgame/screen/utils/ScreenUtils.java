package com.batherphilippa.dropgame.screen.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class ScreenUtils {

    public static void clearScreen(float red, float green, float blue, float alpha) {
        com.badlogic.gdx.utils.ScreenUtils.clear(red, green, blue, alpha);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
