package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.manager.GameState;
import com.batherphilippa.dropgame.manager.ResourceManager;
import com.batherphilippa.dropgame.screen.utils.UIUtils;

public class SplashScreen implements Screen {

    private final Drop game;
    private final ResourceManager resourceManager;
    private final Stage stage;
    private final Image splashImg;
    private final Texture splashTexture;
    private boolean splashDone;

    public SplashScreen(Drop game) {
        this.game = game;
        this.resourceManager = new ResourceManager();
        this.resourceManager.loadResources();

        // load splash image
        this.splashTexture = new Texture(resourceManager.loadSplashImg());
        this.splashImg = new Image(splashTexture);
        this.splashDone = false;
        this.stage = new Stage();
    }

    @Override
    public void show() {
        UIUtils.clearScreen(0.8f, 0, 0.1f, 3);

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        splashImg.addAction(Actions.sequence(Actions.alpha(0),
                Actions.fadeIn(1.5f),
                Actions.delay(2.5f),
                Actions.run(new Runnable() {
            @Override
            public void run() {
                splashDone = true;
            }
        })));

        table.row().height(splashTexture.getHeight());
        table.add(splashImg).center();
        stage.addActor(table);
    }


    @Override
    public void render(float delta) {
        UIUtils.clearScreen(0.8f, 0, 0.1f, 3);

        stage.act();
        stage.draw();


        if (splashDone) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        game.gameState = GameState.PAUSED;
    }

    @Override
    public void resume() {
        game.gameState = GameState.RUNNING;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        splashImg.clear();
        resourceManager.dispose();
        splashTexture.dispose();
        stage.dispose();
    }
}
