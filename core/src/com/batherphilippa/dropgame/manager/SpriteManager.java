package com.batherphilippa.dropgame.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.domain.Bucket;
import com.batherphilippa.dropgame.domain.Raindrop;
import com.batherphilippa.dropgame.utils.KeyDirection;

import java.util.Iterator;

import static com.batherphilippa.dropgame.utils.AssetConstants.*;
import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_HEIGHT;
import static com.batherphilippa.dropgame.utils.SpriteConstants.SPRITE_WIDTH;

public class SpriteManager {

    private final ResourceManager resourceManager;
    private final Drop game;

    private Bucket player;
    private Array<Raindrop> raindrops;
    private float lastDropTime;

    public SpriteManager(ResourceManager resourceManager, Drop game) {
        this.resourceManager = resourceManager;
        this.game = game;
        init();
    }

    private void init() {
        // generate characters
        player = new Bucket(new Texture(resourceManager.loadImage(CHARACTER_PLAYER)));
        raindrops = new Array<>();
        spawnRaindrop();
    }

    private void spawnRaindrop() {
        Raindrop raindrop = new Raindrop(new Texture(resourceManager.loadImage(CHARACTER_ITEM)));
        raindrops.add(raindrop);
        lastDropTime = raindrop.getLastDropTime();
    }

    public void draw() {
        game.batch.begin();
        game.font.draw(game.batch, "Drops Collected: " + player.getDropsCollected(), 0, VIEWPORT_HEIGHT);
        player.render(game.batch);
        for (Raindrop raindrop : raindrops) {
            raindrop.render(game.batch);
        }
        game.batch.end();
    }

    public void manageInput(OrthographicCamera camera, Vector3 touchPos) {
        if (!Gdx.input.isTouched()) {
            manageKeyPress();
        } else {
            manageTouchMousePress(camera, touchPos);
        }
    }

    private void manageKeyPress() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            // Gdx.graphics.getDeltaTime() returns the time passed between the last and the current frame in seconds.
            player.move(new Vector2(200 * Gdx.graphics.getDeltaTime(), 0), KeyDirection.LEFT);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            // Gdx.graphics.getDeltaTime() returns the time passed between the last and the current frame in seconds.
            player.move(new Vector2(200 * Gdx.graphics.getDeltaTime(), 0), KeyDirection.RIGHT);
        }
    }

    private void manageTouchMousePress(OrthographicCamera camera, Vector3 touchPos) {
        if (Gdx.input.isTouched()) {
            // transform touch/mouse coordinates to camera's coordinate system.
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // vector now contains touch/mouse coordinates in the coordinate system our bucket lives in
            camera.unproject(touchPos);
            // change position of bucket to be centered around the touch/mouse coordinates
            player.move(new Vector2(touchPos.x - SPRITE_WIDTH / 2, 0), null);
        }
    }


    public void update() {
        player.update();
        checkLastDropTime(); // check last time raindrop spawned; create if necessary
        moveRaindrops();
    }

    private void checkLastDropTime() {
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            spawnRaindrop();
        }
    }

    private void moveRaindrops() {
        for (Iterator<Raindrop> iter = raindrops.iterator(); iter.hasNext(); ) {
            Raindrop raindrop = iter.next();
            raindrop.move(new Vector2(0, 200 * Gdx.graphics.getDeltaTime()), null);
            if (raindrop.getYCoord() + 64 < 0) {
                iter.remove();
            }
            if (raindrop.getRaindropRectangle().overlaps(player.getBucketRectangle())) {
                player.updateDropsCollected();
                resourceManager.playSound(SOUND_DROP);
                iter.remove();
            }
        }
    }

    public void dispose() {
        resourceManager.dispose();
        player.getTexture().dispose();
        raindrops.clear();
    }
}
