package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.manager.CameraManager;

public class GameOverScreen implements Screen {

    private final Drop game;
    private CameraManager cameraManager;
    private int points;

    public GameOverScreen(Drop game, int points) {
        this.game = game;
        this.cameraManager = new CameraManager();
        this.points = points;
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
        game.font.draw(game.batch, "Game Over!!!", 250, 250);
        game.font.draw(game.batch, "Points collected: " + points, 250, 200);
        game.font.draw(game.batch, "Press Escape to Exit", 250, 150);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
            System.exit(0);
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
