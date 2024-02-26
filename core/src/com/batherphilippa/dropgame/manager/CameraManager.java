package com.batherphilippa.dropgame.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.batherphilippa.dropgame.Drop;

import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_HEIGHT;
import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_WIDTH;

public class CameraManager {

    private OrthographicCamera camera;

    public CameraManager() {
        this.camera = new OrthographicCamera();
        init();
    }

    private void init() {
        this.camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    public void update() {
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setProjectionMatrix(Drop game) {
        game.batch.setProjectionMatrix(camera.combined);
    }
}
