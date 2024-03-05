package com.batherphilippa.dropgame.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

import static com.batherphilippa.dropgame.utils.AssetConstants.*;

public class ResourceManager implements Disposable {

    private TextureAtlas textAtlas;
//    private Map<String, FileHandle> imageMap;
    private Map<String, Sound> soundMap;
    private Map<String, Music> musicMap;
    private Map<String, FileHandle> uiMap;
    public void loadResources() {

        // instantiate hash map and add fx
        soundMap = new HashMap<>();
        soundMap.put(SOUND_DROP, Gdx.audio.newSound(Gdx.files.internal("sound/drop.wav")));
        soundMap.put(SOUND_STONE, Gdx.audio.newSound(Gdx.files.internal("sound/stone.wav")));
        soundMap.put(SOUND_GAME_OVER, Gdx.audio.newSound(Gdx.files.internal("sound/game_over.wav")));
        soundMap.put(SOUND_GAME_OVER_LOSER, Gdx.audio.newSound(Gdx.files.internal("sound/game_over_loser.wav")));

        musicMap = new HashMap<>();
        musicMap.put(MUSIC_THEME, Gdx.audio.newMusic(Gdx.files.internal("music/rain.mp3")));

        // instantiate hash map and add FileHandles for imgs
//        imageMap = new HashMap<>();
//        imageMap.put(CHARACTER_PLAYER, Gdx.files.internal("image/bucket.png"));
//        imageMap.put(CHARACTER_ITEM_DROP, Gdx.files.internal("image/drop.png"));
//        imageMap.put(CHARACTER_ITEM_ENEMY, Gdx.files.internal("image/stone.png"));
        textAtlas = new TextureAtlas(Gdx.files.internal("image/atlas/drop_game_atlas.atlas"));

        // TODO - add to textAtlas
        uiMap = new HashMap<>();
        uiMap.put(UI_SPLASH, Gdx.files.internal("image/ui/splash.png"));
    }

//    public FileHandle loadImage(String name) {
////        return imageMap.get(name);
//        return textAtlas.findRegion(name);
//    }

    public TextureAtlas.AtlasRegion loadRegion(String name) {
        return textAtlas.findRegion(name);
    }
    public Music loadMusic(String name) {
        return musicMap.get(name);
    }
    public void loadSound(String name) {
        soundMap.get(name);
    }
    public void playMusic(String name) {
        musicMap.get(name).play();
    }
    public void stopMusic(String name) {
        musicMap.get(name).stop();
    }
    public void playSound(String name) {
        soundMap.get(name).play();
    }
    public void stopSound(String name) {
        soundMap.get(name).stop();
    }

    public FileHandle loadSplashImg() {
        return uiMap.get(UI_SPLASH);
    }

    @Override
    public void dispose() {
//        imageMap.clear();
        musicMap.clear();
        soundMap.clear();
        uiMap.clear();
        textAtlas.dispose();
    }
}
