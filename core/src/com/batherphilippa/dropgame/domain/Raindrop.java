package com.batherphilippa.dropgame.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Raindrop {
    private final Texture texture; // loaded img stored into RAM
    private final Rectangle raindrop;
    private final long lastDropTime;

    public Raindrop(Texture texture) {
        this.texture = texture;
        this.raindrop = new Rectangle();
        this.lastDropTime = TimeUtils.nanoTime(); // current value of system timer in nano seconds
        setInitialCoordinates();
    }

    private void setInitialCoordinates() {
        this.raindrop.x = MathUtils.random(0, 800-64);
        this.raindrop.y = 480;
        this.raindrop.width = 64;
        this.raindrop.height = 64;
    }


    public Rectangle getRaindropRectangle() {
        return raindrop;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public long getLastDropTime() {
        return this.lastDropTime;
    }

    public float getXCoord() {
        return this.raindrop.x;
    }

    public float getYCoord() {
        return this.raindrop.y;
    }

    public void setYCoord(float yCoord) {
        this.raindrop.y -= yCoord;
    }
}
