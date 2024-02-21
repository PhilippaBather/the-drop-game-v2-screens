package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.manager.CameraManager;
import com.batherphilippa.dropgame.manager.ResourceManager;
import com.batherphilippa.dropgame.manager.SpriteManager;

import static com.batherphilippa.dropgame.utils.AssetConstants.MUSIC_THEME;
import static com.batherphilippa.dropgame.utils.AssetConstants.SOUND_DROP;

public class GameScreen implements Screen {
    private final Drop game;
    private final CameraManager cameraManager;
    private final ResourceManager resourceManager;
    private final SpriteManager spriteManager;

    private final Vector3 touchPos;

    public GameScreen(Drop game) {
        this.game = game;

        this.cameraManager = new CameraManager();
        this.resourceManager = new ResourceManager();
        this.resourceManager.loadResources();
        this.spriteManager = new SpriteManager(this.resourceManager, this.game);

        // use 3D vector as OrthographicCamera is actually a 3D camera
        // that also takes into account the z coordinates
        this.touchPos = new Vector3();

        loadSoundFX();

    }

    private void loadSoundFX() {
        resourceManager.loadResources();
        resourceManager.loadMusic(MUSIC_THEME).setLooping(true);
        resourceManager.loadSound(SOUND_DROP);
    }

    @Override
    public void show() {
        resourceManager.playMusic(MUSIC_THEME);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        cameraManager.getCamera().update();
        game.batch.setProjectionMatrix(cameraManager.getCamera().combined);
        spriteManager.manageInput(cameraManager.getCamera(), touchPos);
        spriteManager.update();
        spriteManager.draw();
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
        resourceManager.dispose();
        spriteManager.dispose();
    }

}
