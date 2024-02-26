package com.batherphilippa.dropgame.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class Raindrop extends Item {
    private final long lastDropTime;

    public Raindrop(Texture texture) {
        super(texture);
        this.lastDropTime = TimeUtils.nanoTime(); // current value of system timer in nanoseconds
        setInitialCoordinates();
    }
}
