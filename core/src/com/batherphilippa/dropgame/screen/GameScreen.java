package com.batherphilippa.dropgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.manager.*;
import com.batherphilippa.dropgame.screen.utils.UIUtils;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import static com.batherphilippa.dropgame.screen.MenuType.GAME_MENU;
import static com.batherphilippa.dropgame.utils.AssetConstants.MUSIC_THEME;
import static com.batherphilippa.dropgame.utils.AssetConstants.SOUND_DROP;
import static com.batherphilippa.dropgame.utils.UIConstants.BTN_MENU;
import static com.batherphilippa.dropgame.utils.UIConstants.BTN_PAUSE;

public class GameScreen implements Screen {
    private final Drop game;
    private final CameraManager cameraManager;
    private final ResourceManager resourceManager;
    private final SpriteManager spriteManager;
    private final Vector3 touchPos;
    private VisTextButton menuBtn;
    private VisTextButton pauseBtn;
    private final Stage stage;

    public GameScreen(Drop game, float[] gameStats) {
        this.game = game;
        this.cameraManager = new CameraManager();
        this.resourceManager = new ResourceManager();
        this.resourceManager.loadResources();
        this.spriteManager = new SpriteManager(this.resourceManager, this.game);
        this.stage = new Stage();
        // use 3D vector as OrthographicCamera is actually a 3D camera (x, y, and z coords)
        this.touchPos = new Vector3();

        init(gameStats);
    }

    private void init(float[] gameStats) {
        if(gameStats != null) {
            this.spriteManager.setPoints((int) gameStats[0]);  // for more complicated game use key-value pairs
            this.spriteManager.setTime(gameStats[1]);
        }
        loadSoundFX();
    }

    private void loadSoundFX() {
        resourceManager.loadResources();
        resourceManager.loadMusic(MUSIC_THEME).setLooping(true);
        resourceManager.loadSound(SOUND_DROP);
    }

    @Override
    public void show() {
        clearScreen();

        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        VisTable table = UIUtils.createTableObj();
        initUIComponents();
        addClickListeners();
        createTableStructure(table);
        stage.addActor(table);

        resourceManager.playMusic(MUSIC_THEME);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        checkForPause();

        if (!game.gameState.equals(GameState.PAUSED)) {
            cameraManager.update();
            cameraManager.setProjectionMatrix(game);
            spriteManager.manageInput(cameraManager.getCamera(), touchPos);
            spriteManager.update(delta);
            spriteManager.draw();
            stage.act(delta);
            stage.draw();
        } else {
            // draw pause screen
            game.batch.begin();
            game.font.draw(game.batch, "Paused", 250, 250);
            game.font.draw(game.batch, "Press 'Down' arrow to continue.", 250, 200);
            game.batch.end();
        }

        checkForResume();
    }

    private void checkForPause() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            game.gameState = GameState.PAUSED;
        }
    }

    private void checkForResume() {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            game.gameState = GameState.RUNNING;
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
        resourceManager.dispose();
        spriteManager.dispose();
        stage.dispose();
    }

    private void clearScreen() {
        if (ConfigurationManager.isDarkModeEnabled()) {
            UIUtils.clearScreen(0, 0, 0f, 0);
        } else {
            UIUtils.clearScreen(0, 0, 0.2f, 1);
        }
    }

    private void initUIComponents() {
        menuBtn = new VisTextButton(BTN_MENU);
        pauseBtn = new VisTextButton(BTN_PAUSE);
    }

    private void addClickListeners() {
        OptionManager.handleMenuReturnClicked(menuBtn, this, game, GAME_MENU, spriteManager);
        OptionManager.handlePauseClicked(pauseBtn, game);
    }

    private void createTableStructure(VisTable table) {
        table.setPosition(0, 230);
        table.add(pauseBtn).height(20).width(75).pad(5);
        table.add(menuBtn).height(20).width(75).pad(5);
    }
}
