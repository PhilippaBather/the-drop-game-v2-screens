package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.manager.CameraManager;
import com.batherphilippa.dropgame.manager.ConfigurationManager;
import com.batherphilippa.dropgame.manager.OptionManager;
import com.batherphilippa.dropgame.manager.ResourceManager;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import static com.batherphilippa.dropgame.utils.UIConstants.*;

public class GameOverScreen implements Screen {

    private final Drop game;
    private CameraManager cameraManager;
    private ResourceManager resourceManager;
    private Stage stage;
    private VisLabel titleLabel;
    private VisTextButton exitBtn;
    private VisTextButton mainMenuBtn;
    private VisTextButton playBtn;
    private int points;

    public GameOverScreen(Drop game, int points, ResourceManager resourceManager) {
        this.game = game;
        this.cameraManager = new CameraManager();
        this.resourceManager = resourceManager;
        this.points = points;
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

        VisTable infoTable = createTableObject();
        VisTable actionsTable = createTableObject();

        stage = new Stage();
        stage.addActor(infoTable);
        stage.addActor(actionsTable);

        createComponents();
        setClickListeners();
        createTableStructure(infoTable, actionsTable);
        Gdx.input.setInputProcessor(stage);

    }

    private VisTable createTableObject() {
        VisTable table = new VisTable(true);
        table.setFillParent(true);
        return table;
    }

    private void createComponents() {
        this.titleLabel = new VisLabel(LABEL_GAMEOVER_TITLE);
        this.exitBtn = new VisTextButton(BTN_EXIT);
        this.mainMenuBtn = new VisTextButton(BTN_RETURN);
        this.playBtn = new VisTextButton(BTN_PLAY);
    }

    private void setClickListeners() {
        OptionManager.handleExitClicked(exitBtn, this);
        OptionManager.handleMainMenuClicked(mainMenuBtn, this, game);
        OptionManager.handlePlayClicked(playBtn, this, game, resourceManager);
    }

    private void createTableStructure(VisTable infoTable, VisTable actionsTable){
        // info table
        infoTable.setPosition(0, 70);
        infoTable.row();
        infoTable.add(titleLabel).center().width(50).height(40).pad(5);
        infoTable.row();
        infoTable.add("Points: " + points).center().width(50).height(40).pad(5);
        // action button table
        actionsTable.setPosition(0, -200);
        actionsTable.row();
        actionsTable.add(playBtn).center().width(150).height(30).pad(5);
        actionsTable.add(mainMenuBtn).center().width(150).height(30).pad(5);
        actionsTable.add(exitBtn).center().width(150).height(30).pad(5);
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
    }
}
