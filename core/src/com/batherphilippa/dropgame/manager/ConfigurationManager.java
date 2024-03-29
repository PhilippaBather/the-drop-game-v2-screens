package com.batherphilippa.dropgame.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisList;

import static com.batherphilippa.dropgame.utils.ConfigConstants.*;

public class ConfigurationManager {

    private static final Preferences prefs = Gdx.app.getPreferences(APP_NAME);

    public static void handleDarkModePref(VisCheckBox ckBtn) {
        ckBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                prefs.putBoolean("dark_mode", ckBtn.isChecked());
                prefs.flush();
            }
        });
    }

    public static boolean isDarkModeEnabled() {
        return prefs.getBoolean("dark_mode");
    }

    public static void handleGameLengthPref(VisList gameLengthList) {
        gameLengthList.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                float len = switch (gameLengthList.getSelectedIndex()) {
                    case 0 -> GAME_LENGTH_SHORT;
                    case 1 -> GAME_LENGTH_MEDIUM;
                    case 2 -> GAME_LENGTH_LONG;
                    default ->
                            throw new IllegalStateException("Unexpected value: " + gameLengthList.getSelectedIndex());
                };
                prefs.putFloat("game_length", len);
                prefs.flush();
            }
        });
    }

    public static float getGameLength() {
        return prefs.getFloat("game_length");
    }

    public static String getGameLengthOption() {
        float selected = getGameLength();
        String option = switch((int) selected) {
            case (int) GAME_LENGTH_MEDIUM -> GAME_LENGTH_STR_MEDIUM;
            case (int) GAME_LENGTH_LONG -> GAME_LENGTH_STR_LONG;
            default -> GAME_LENGTH_STR_SHORT;
        };
        return option;
    }
}
