package com.batherphilippa.dropgame.screen.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.kotcrab.vis.ui.widget.VisTable;

public class UIUtils {

    public static void clearScreen(float red, float green, float blue, float alpha) {
        com.badlogic.gdx.utils.ScreenUtils.clear(red, green, blue, alpha);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static VisTable createTableObj() {
        VisTable table = new VisTable(true);
        table.setFillParent(true);
        return table;
    }
}
