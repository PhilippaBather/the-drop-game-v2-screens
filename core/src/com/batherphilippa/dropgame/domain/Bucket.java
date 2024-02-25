package com.batherphilippa.dropgame.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.batherphilippa.dropgame.utils.KeyDirection;

import static com.batherphilippa.dropgame.utils.KeyDirection.LEFT;
import static com.batherphilippa.dropgame.utils.KeyDirection.RIGHT;
import static com.batherphilippa.dropgame.utils.SpriteConstants.SPRITE_HEIGHT;
import static com.batherphilippa.dropgame.utils.SpriteConstants.SPRITE_WIDTH;
import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_WIDTH;

public class Bucket extends Character {
    private int dropsCollected;

    public Bucket(Texture texture) {
        super(texture);
        this.dropsCollected = 0;
        setInitialCoordinates();
    }

    public void setInitialCoordinates() {
        super.rectangle.x = (VIEWPORT_WIDTH / 2) - (SPRITE_WIDTH / 2);
        super.rectangle.y = 20;
        super.rectangle.width = SPRITE_WIDTH;
        super.rectangle.height = SPRITE_HEIGHT;
        super.coords.x = rectangle.x;

    }

    @Override
    public void move(Vector2 position, KeyDirection dir) {

        if (dir != null) {
            handleKeyPress(position, dir);
            return;
        }

        super.rectangle.x = position.x;
        super.rectangle.y = position.y;
    }

    private void handleKeyPress(Vector2 position, KeyDirection dir) {
        if (dir.equals(LEFT)) {
            super.rectangle.x -= position.x;
        } else if (dir.equals(RIGHT)) {
            super.rectangle.x += position.x;
        }
    }

    @Override
    public void update() {
        checkBucketInBounds();
    }

    public int getDropsCollected() {
        return dropsCollected;
    }

    public void updateDropsCollected() {
        dropsCollected++;
    }

    private void checkBucketInBounds() {
        if (super.rectangle.x < 0) {
            super.rectangle.x = 0;
        }

        if (super.rectangle.x > VIEWPORT_WIDTH - SPRITE_WIDTH) {
            super.rectangle.x = (VIEWPORT_WIDTH - SPRITE_WIDTH);
        }
    }

    public Rectangle getBucketRectangle() {
        return super.rectangle;
    }

    public Texture getTexture() {
        return texture;
    }

}
