package com.batherphilippa.dropgame.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.batherphilippa.dropgame.utils.KeyDirection;

import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_HEIGHT;
import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_WIDTH;
import static com.batherphilippa.dropgame.utils.SpriteConstants.SPRITE_HEIGHT;
import static com.batherphilippa.dropgame.utils.SpriteConstants.SPRITE_WIDTH;

public class Raindrop extends Character {
    private final long lastDropTime;

    public Raindrop(Texture texture) {
        super(texture);
        this.lastDropTime = TimeUtils.nanoTime(); // current value of system timer in nanoseconds
        setInitialCoordinates();
    }

    public void setInitialCoordinates() {
        super.rectangle.x = MathUtils.random(0, VIEWPORT_WIDTH - SPRITE_WIDTH);
        super.rectangle.y = VIEWPORT_HEIGHT;
        super.rectangle.width = SPRITE_WIDTH;
        super.rectangle.height = SPRITE_HEIGHT;
    }

    @Override
    public void move(Vector2 position, KeyDirection dir) {
        super.rectangle.y -= position.y;
    }

    @Override
    public void update() {
    }

    public Rectangle getRaindropRectangle() {
        return super.rectangle;
    }

    public long getLastDropTime() {
        return this.lastDropTime;
    }


    public float getYCoord() {
        return super.rectangle.y;
    }

}
