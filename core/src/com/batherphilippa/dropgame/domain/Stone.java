package com.batherphilippa.dropgame.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class Stone extends Item {

    private long lastDropTime;

    public Stone(Texture texture) {
        super(texture);
        this.lastDropTime = TimeUtils.nanoTime();
        setInitialCoordinates();
    }
}
