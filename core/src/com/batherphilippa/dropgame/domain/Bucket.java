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

//    public void manageInput(OrthographicCamera camera, Vector3 touchPos) {
//        if (!Gdx.input.isTouched()) {
//            manageKeyPress();
//        } else {
//            manageTouchMousePress(camera, touchPos);
//        }
//    }
//
//    private void manageKeyPress() {
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            // Gdx.graphics.getDeltaTime() returns the time passed between the last and the current frame in seconds.
//            super.rectangle.x -= 200 * Gdx.graphics.getDeltaTime();
//        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            // Gdx.graphics.getDeltaTime() returns the time passed between the last and the current frame in seconds.
//            super.rectangle.x += 200 * Gdx.graphics.getDeltaTime();
//        }
//    }
//
//    private void manageTouchMousePress(OrthographicCamera camera, Vector3 touchPos) {
//        if (Gdx.input.isTouched()) {
//            // transform touch/mouse coordinates to camera's coordinate system.
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            // vector now contains touch/mouse coordinates in the coordinate system our bucket lives in
//            camera.unproject(touchPos);
//            // change position of bucket to be centered around the touch/mouse coordinates
//            super.rectangle.x = touchPos.x - SPRITE_WIDTH / 2;
//        }
//    }

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
