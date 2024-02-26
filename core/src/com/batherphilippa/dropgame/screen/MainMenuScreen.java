package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.manager.CameraManager;
import com.batherphilippa.dropgame.manager.ConfigurationManager;
import com.batherphilippa.dropgame.manager.OptionManager;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import static com.batherphilippa.dropgame.utils.UIConstants.*;

public class MainMenuScreen implements Screen {

    private final Drop game;
    private final CameraManager cameraManager;
    private Stage stage;
    private VisLabel titleLab;
    private VisTextButton configBtn;
    private VisTextButton exitBtn;
    private VisTextButton playBtn;

    public MainMenuScreen(Drop game) {
        this.game = game;
        this.cameraManager = new CameraManager();
    }

    @Override
    public void show() {
        if (ConfigurationManager.isDarkModeEnabled()) {
            ScreenUtils.clear(0.5f, 0, 0.2f, 5);
        } else {
            ScreenUtils.clear(0.8f, 0, 0.1f, 3);
        }


        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        VisTable table = createTableObject();

        stage = new Stage();
        stage.addActor(table);
        createComponents();
        setClickListeners();
        createTableStructure(table);
        Gdx.input.setInputProcessor(stage);

    }

    private VisTable createTableObject() {
        VisTable table = new VisTable(true);
        table.setFillParent(true);
        return table;
    }

    private void createComponents() {
        this.configBtn = new VisTextButton(BTN_CONFIG);
        this.exitBtn = new VisTextButton(BTN_EXIT);
        this.playBtn = new VisTextButton(BTN_PLAY);
        this.titleLab = new VisLabel(LABEL_WELCOME);
    }

    private void createTableStructure(VisTable table) {
        table.row();
        table.add(titleLab).center().height(50);
        table.row();
        table.add(playBtn).center().width(200).height(50).pad(5);
        table.row();
        table.add(configBtn).center().width(200).height(50).pad(5);
        table.row();
        table.add(exitBtn).center().width(200).height(50).pad(5);
    }

    private void setClickListeners() {
        OptionManager.handleConfigClicked(configBtn, this, game);
        OptionManager.handleExitClicked(exitBtn, this);
        OptionManager.handlePlayClicked(playBtn, this, game, null);
    }

    @Override
    public void render(float delta) {
        if (ConfigurationManager.isDarkModeEnabled()) {
            ScreenUtils.clear(0.5f, 0, 0.2f, 5);
        } else {
            ScreenUtils.clear(0.8f, 0, 0.1f, 3);
        }

        cameraManager.update();
        cameraManager.setProjectionMatrix(game);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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
        stage.dispose();
    }
}
