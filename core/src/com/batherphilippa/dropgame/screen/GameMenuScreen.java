package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.manager.CameraManager;
import com.batherphilippa.dropgame.manager.ConfigurationManager;
import com.batherphilippa.dropgame.manager.GameState;
import com.batherphilippa.dropgame.manager.OptionManager;
import com.batherphilippa.dropgame.screen.utils.UIUtils;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import static com.batherphilippa.dropgame.utils.UIConstants.*;

public class GameMenuScreen implements Screen {

    private final Drop game;
    private final CameraManager cameraManager;
    private VisLabel titleLabel;
    private VisTextButton configBtn;
    private VisTextButton contBtn;
    private VisTextButton exitBtn;
    private VisTextButton newGameBtn;
    private VisTextButton saveBtn;
    private final Stage stage;
    private final float[] gameStats;

    public GameMenuScreen(Drop game, float[] gameStats) {
        this.game = game;
        this.stage = new Stage();
        this.cameraManager = new CameraManager();
        this.gameStats = gameStats;
    }

    @Override
    public void show() {
        clearScreen();

        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        VisTable table = UIUtils.createTableObj();
        stage.addActor(table);
        createComponents();
        setClickListeners();
        createTableStructure(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        cameraManager.update();
        cameraManager.setProjectionMatrix(game);
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
    }

    private void clearScreen() {
        if (ConfigurationManager.isDarkModeEnabled()) {
            UIUtils.clearScreen(0.5f, 0, 0.2f, 5);
        } else {
            UIUtils.clearScreen(0.8f, 0, 0.1f, 3);
        }
    }
    private void createComponents() {
        this.titleLabel = new VisLabel(LABEL_GAME_MENU);
        this.configBtn = new VisTextButton(BTN_CONFIG);
        this.contBtn = new VisTextButton(BTN_CONT);
        this.exitBtn = new VisTextButton(BTN_EXIT);
        this.newGameBtn = new VisTextButton(BTN_NEW_GAME);
        this.saveBtn = new VisTextButton(BTN_SAVE);
    }

    private void setClickListeners() {
        OptionManager.handleConfigClicked(configBtn, this, game, MenuType.GAME_MENU);
        OptionManager.handleContClicked(contBtn, this, game, gameStats);
        OptionManager.handleExitClicked(exitBtn, this);
        OptionManager.handlePlayClicked(newGameBtn, this, game, null);
        OptionManager.handleSaveClicked(saveBtn, this, game);
    }

    private void createTableStructure(VisTable table) {
        table.row();
        table.add(titleLabel).center().height(50);
        table.row();
        table.add(contBtn).center().width(200).height(50).pad(5);
        table.row();
        table.add(newGameBtn).center().width(200).height(50).pad(5);
        table.row();
        table.add(saveBtn).center().width(200).height(50).pad(5);
        table.row();
        table.add(configBtn).center().width(200).height(50).pad(5);
        table.row();
        table.add(exitBtn).center().width(200).height(50).pad(5);
    }
}
