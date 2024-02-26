package com.batherphilippa.dropgame.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.batherphilippa.dropgame.utils.KeyDirection;

import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_HEIGHT;
import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_WIDTH;
import static com.batherphilippa.dropgame.utils.SpriteConstants.SPRITE_HEIGHT;
import static com.batherphilippa.dropgame.utils.SpriteConstants.SPRITE_WIDTH;

public class Item extends Character {

    private final long lastDropTime;

    public Item(TextureRegion region) {
        super(region);
        this.lastDropTime = TimeUtils.nanoTime();
    }

    @Override
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

    public Rectangle getRectangle() {
        return super.rectangle;
    }
    public long getLastDropTime() {
        return this.lastDropTime;
    }

    public float getYCoord() {
        return super.rectangle.y;
    }

}
