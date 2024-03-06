package com.batherphilippa.dropgame.manager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.batherphilippa.dropgame.Drop;
import com.batherphilippa.dropgame.screen.*;
import com.kotcrab.vis.ui.widget.VisTextButton;

import static com.batherphilippa.dropgame.utils.AssetConstants.SOUND_GAME_OVER_LOSER;

public class OptionManager {

    public static void handleConfigClicked(VisTextButton btn, Screen screen, Drop game, MenuType menu) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                screen.dispose();
                game.setScreen(new ConfigurationScreen(game, menu));
            }
        });
    }

    public static void handleContClicked(VisTextButton btn, Screen screen, Drop game, float[] gameStats) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                screen.dispose();
                game.setScreen(new GameScreen(game, gameStats));
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

    public static void handlePauseClicked(VisTextButton btn, Drop game) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.gameState = GameState.PAUSED;
            }
        });
    }

    public static void handlePlayClicked(VisTextButton btn, Screen screen, Drop game, ResourceManager resourceManager) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (resourceManager != null) {
                    resourceManager.stopSound(SOUND_GAME_OVER_LOSER);
                }
                screen.dispose();
                game.setScreen(new GameScreen(game, null));
            }
        });
    }

    public static void handleMenuReturnClicked(VisTextButton btn, Screen screen, Drop game, MenuType menu, SpriteManager spriteManager) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                float[] gameStats = null;
                if (spriteManager != null) {
                    gameStats = spriteManager.getStats();
                }
                screen.dispose();
                switch (menu) {
                    case GAME_MENU -> game.setScreen(new GameMenuScreen(game, gameStats));
                    case MAIN_MENU -> game.setScreen(new MainMenuScreen(game));
                }
            }
        });
    }

    public static void handleSaveClicked(VisTextButton btn, Screen screen, Drop game) {
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                // TODO
            }
        });
    }
}
