package com.batherphilippa.dropgame.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;

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

    public OrthographicCamera getCamera() {
        return camera;
    }

}
