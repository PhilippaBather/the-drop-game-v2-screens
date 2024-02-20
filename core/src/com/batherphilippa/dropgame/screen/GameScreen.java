package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.domain.Bucket;
import com.batherphilippa.dropgame.domain.Raindrop;

import java.util.Iterator;

public class GameScreen implements Screen {

    private final Drop game;
    private OrthographicCamera camera;
    private Vector3 touchPos;
    private Bucket bucket;
    private Music rainMusic;
    private Sound dropSound;
    private Texture raindropImg;
    private Array<Raindrop> raindrops;
    private long lastDropTime;
    private int dropsCollected = 0; // TODO

    public GameScreen(Drop game) {
        this.game = game;

        loadSoundFX();

        // create camera and SpriteBatch
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 400);

        this.bucket = new Bucket(new Texture(Gdx.files.internal("bucket.png")));
        this.raindrops = new Array<>();
        spawnRaindrop();

        // use 3D vector as OrthographicCamera is actually a 3D camera that takes into account the z coordinates also
        touchPos = new Vector3();

    }

    private void loadSoundFX() {
        // load sound effects, which stored in memory - use sound if music is < 10sec
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        // load music, which is streamed from where its is stored (too big to be kept in memory)
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        // start playback of background music immediately
        rainMusic.setLooping(true);
    }

    @Override
    public void show() {
        rainMusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();

        // tell SpriteBatch to render in the coordinate system specified by the camera
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Drops Collected: " + dropsCollected, 0, 400);
        game.batch.draw(bucket.getTexture(), bucket.getXCoord(), bucket.getYCoord(), bucket.getWidth(), bucket.getHeight());
        for (Raindrop raindrop : raindrops) {
            game.batch.draw(raindrop.getTexture(), raindrop.getXCoord(), raindrop.getYCoord());
        }
        game.batch.end();

        bucket.manageInput(camera, touchPos); // manage bucket movement
        bucket.checkBucketInBounds();

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
            raindrop.setYCoord(200 * Gdx.graphics.getDeltaTime());
            if (raindrop.getYCoord() + 64 < 0) {
                iter.remove();
            }
            if (raindrop.getRaindropRectangle().overlaps(bucket.getBucketRectangle())) {
                dropsCollected++;
                dropSound.play();
                iter.remove();
            }
        }
    }

    private void spawnRaindrop() {
        raindropImg = new Texture(Gdx.files.internal("drop.png"));
        Raindrop raindrop = new Raindrop(raindropImg);
        raindrops.add(raindrop);
        lastDropTime = raindrop.getLastDropTime();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bucket.getTexture().dispose();
        raindropImg.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }
}
