package com.batherphilippa.dropgame.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraManager {

    private OrthographicCamera camera;

    public CameraManager() {
        this.camera = new OrthographicCamera();
        init();
    }

    private void init() {
        this.camera.setToOrtho(false, 800, 400);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

}
