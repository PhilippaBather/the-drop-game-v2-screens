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
import com.batherphilippa.dropgame.domain.Item;
import com.batherphilippa.dropgame.domain.Raindrop;
import com.batherphilippa.dropgame.domain.Stone;
import com.batherphilippa.dropgame.screen.GameOverScreen;
import com.batherphilippa.dropgame.screen.GameScreen;
import com.batherphilippa.dropgame.utils.KeyDirection;

import java.util.Iterator;

import static com.batherphilippa.dropgame.utils.AssetConstants.*;
import static com.batherphilippa.dropgame.utils.ConfigConstants.GAME_LENGTH_30S;
import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_HEIGHT;
import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_WIDTH;
import static com.batherphilippa.dropgame.utils.SpriteConstants.SPRITE_WIDTH;

public class SpriteManager {

    private final ResourceManager resourceManager;
    private final Drop game;
    private final GameScreen gameScreen;

    private Bucket player;
    private Array<Item> raindrops;
    private Array<Item> stones;
    private float lastDropTime;
    private float gameTime;

    public SpriteManager(ResourceManager resourceManager, Drop game, GameScreen gameScreen) {
        this.resourceManager = resourceManager;
        this.game = game;
        this.gameScreen = gameScreen;

        if (ConfigurationManager.getGameLength() == 0) {
            this.gameTime = 30;
        } else {
            this.gameTime = ConfigurationManager.getGameLength();
        }

        init();
    }

    private void init() {
        // generate characters
        player = new Bucket(new Texture(resourceManager.loadImage(CHARACTER_PLAYER)));
        raindrops = new Array<>();
        stones = new Array<>();
        spawnRaindrop();
        spawnEnemy();
    }

    private void spawnRaindrop() {
        Raindrop raindrop = new Raindrop(new Texture(resourceManager.loadImage(CHARACTER_ITEM_DROP)));
        raindrops.add(raindrop);
        lastDropTime = raindrop.getLastDropTime();
    }

    private void spawnEnemy() {
        Stone stone = new Stone(new Texture(resourceManager.loadImage(CHARACTER_ITEM_ENEMY)));
        stones.add(stone);
        lastDropTime = stone.getLastDropTime();
    }

    public void draw() {
        game.batch.begin();
        game.font.draw(game.batch, "Drops Collected: " + player.getDropsCollected(), 10, VIEWPORT_HEIGHT - 10);
        game.font.draw(game.batch, "Time: " + (int) gameTime, VIEWPORT_WIDTH - 60, VIEWPORT_HEIGHT - 10);
        player.render(game.batch);
        for (Item raindrop : raindrops) {
            raindrop.render(game.batch);
        }
        for (Item stone : stones) {
            stone.render(game.batch);
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


    public void update(float delta) {
        player.update();
        checkLastDropTime(); // check last time raindrop spawned; create if necessary
        moveItem(raindrops, false);
        moveItem(stones, true);

        gameTime -= delta;
        if (gameTime < 0) {
            resourceManager.playSound(SOUND_GAME_OVER);
            game.setScreen(new GameOverScreen(game, player.getDropsCollected(), resourceManager));
            dispose();
        }

        if (player.getDropsCollected() < 0) {
            resourceManager.stopMusic(MUSIC_THEME);
            resourceManager.playSound(SOUND_GAME_OVER);
            dispose();
            game.setScreen(new GameOverScreen(game, player.getDropsCollected(), resourceManager));
        }
    }

    private void checkLastDropTime() {
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            spawnRaindrop();
            spawnEnemy();
        }
    }

    private void moveItem(Array<Item> items, boolean isEnemy) {
        for (Iterator<Item> iter = items.iterator(); iter.hasNext(); ) {
            Item item = iter.next();
            item.move(new Vector2(0, 200 * Gdx.graphics.getDeltaTime()), null);
            if (item.getYCoord() + 64 < 0) {
                iter.remove();
            }
            if (item.getRectangle().overlaps(player.getBucketRectangle())) {
                player.updateDropsCollected(isEnemy);
                if (!isEnemy) {
                    resourceManager.playSound(SOUND_DROP);
                } else {
                    resourceManager.playSound(SOUND_STONE);
                }
                iter.remove();
            }
        }
    }

    public void dispose() {
        player.getTexture().dispose();
        raindrops.clear();
    }
}
