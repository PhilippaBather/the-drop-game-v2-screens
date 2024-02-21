package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.batherphilippa.dropgame.Drop;

import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_HEIGHT;
import static com.batherphilippa.dropgame.utils.ScreenConstants.VIEWPORT_WIDTH;

public class MainMenuScreen implements Screen {

    private final Drop game;
    private OrthographicCamera camera;

    public MainMenuScreen(Drop game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5f, 0, 0.2f, 5);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

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
