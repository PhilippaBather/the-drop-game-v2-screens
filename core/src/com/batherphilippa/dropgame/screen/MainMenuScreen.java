package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.manager.CameraManager;

public class MainMenuScreen implements Screen {

    private final Drop game;
    private CameraManager cameraManager;

    public MainMenuScreen(Drop game) {
        this.game = game;
        this.cameraManager = new CameraManager();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5f, 0, 0.2f, 5);

        cameraManager.update();
        cameraManager.setProjectionMatrix(game);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Drop!!!", 250, 250); // x and y: position of font on screen
        game.font.draw(game.batch, "Tap anywhere to begin!", 250, 200);
        game.font.draw(game.batch, "Press ESCAPE to exit", 250, 150);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose(); // dispose of MainMenuScreen
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
            System.exit(0);  // zero indicates non abnormal termination
        }
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

    }
}
