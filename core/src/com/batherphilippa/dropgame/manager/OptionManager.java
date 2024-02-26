package com.batherphilippa.dropgame.manager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.screen.ConfigurationScreen;
import com.batherphilippa.dropgame.screen.GameScreen;
import com.batherphilippa.dropgame.screen.MainMenuScreen;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class OptionManager {

    private final ConfigurationManager configManager;

    public OptionManager() {
        this.configManager = new ConfigurationManager();
    }

    public static void handleConfigClicked(VisTextButton btn, Screen screen, Drop game) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                screen.dispose();
                game.setScreen(new ConfigurationScreen(game));
            }
        });
    }

    public static void handleExitClicked(VisTextButton btn, Screen screen) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                screen.dispose();
                System.exit(0);
            }
        });
    }

    public static void handlePlayClicked(VisTextButton btn, Screen screen, Drop game) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                screen.dispose();
                game.setScreen(new GameScreen(game));
            }
        });
    }

    public static void handleReturnClicked(VisTextButton btn, Screen screen, Drop game) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                screen.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }
}
