package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.manager.ConfigurationManager;
import com.batherphilippa.dropgame.manager.OptionManager;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;

import static com.batherphilippa.dropgame.utils.UIConstants.*;

public class ConfigurationScreen implements Screen {

    private final Drop game;
    private Stage stage;
    private VisCheckBox darkModeCkBox;
    private VisList<String> gameLengthList;
    private VisLabel gameLengthLab;
    private VisLabel optionsMenuLab;
    private VisTextButton exitBtn;
    private VisTextButton playBtn;
    private VisTextButton returnBtn;

    public ConfigurationScreen(Drop game) {
        this.game = game;
    }

    @Override
    public void show() {
        ScreenUtils.clear(0.5f, 0, 0.2f, 5);

        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        VisTable optionsTable = createTableObject();
        VisTable actionsTable = createTableObject();

        stage = new Stage();
        stage.addActor(optionsTable);
        stage.addActor(actionsTable);
        createVisUIComponents();
        setPreferences();
        setClickListeners();
        createTableStructure(optionsTable, actionsTable);
        Gdx.input.setInputProcessor(stage);
    }

    private VisTable createTableObject() {
        VisTable table = new VisTable(true);
        table.setFillParent(true);
        return table;
    }

    private void createVisUIComponents() {
        exitBtn = new VisTextButton(BTN_EXIT);
        playBtn = new VisTextButton(BTN_PLAY);
        returnBtn = new VisTextButton(BTN_RETURN);
        darkModeCkBox = new VisCheckBox(LABEL_DARK_MODE);
        gameLengthLab = new VisLabel(LABEL_GAME_LENGTH);
        optionsMenuLab = new VisLabel(LABEL_OPTIONS_MENU_TITLE);
        String[] timeLengths = {"30 seconds", "45 seconds", "1 minute"};
        gameLengthList = new VisList<>();
        gameLengthList.setItems(timeLengths);
    }

    private void setPreferences() {
        if(ConfigurationManager.isDarkModeEnabled()) {
            darkModeCkBox.setChecked(true);
        }

        gameLengthList.setSelected(ConfigurationManager.getGameLengthOption());
    }

    private void setClickListeners() {
        ConfigurationManager.handleDarkModePref(darkModeCkBox);
        ConfigurationManager.handleGameLengthPref(gameLengthList);
        OptionManager.handleExitClicked(exitBtn, this);
        OptionManager.handlePlayClicked(playBtn, this, game);
        OptionManager.handleReturnClicked(returnBtn, this, game);
    }

    private void createTableStructure(VisTable optionsTable, VisTable actionsTable) {
        // menu table
        optionsTable.setPosition(0, 70);
        optionsTable.row();
        optionsTable.add(optionsMenuLab).center().width(250).height(40).pad(5);
        optionsTable.row();
        optionsTable.addSeparator();
        optionsTable.row();
        optionsTable.add(darkModeCkBox).center().width(150).height(40).pad(5);
        optionsTable.row();
        optionsTable.add(gameLengthLab).center().width(150).height(40).pad(5);
        optionsTable.row().center();
        optionsTable.add(gameLengthList).center().width(150).height(75).pad(5);

        // action button table
        actionsTable.setPosition(0, -200);
        actionsTable.row();
        actionsTable.add(playBtn).center().width(150).height(30).pad(5);
        actionsTable.add(returnBtn).center().width(150).height(30).pad(5);
        actionsTable.add(exitBtn).center().width(150).height(30).pad(5);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5f, 0, 0.2f, 5);

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

    }
}
