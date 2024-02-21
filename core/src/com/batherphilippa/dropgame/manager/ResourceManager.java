package com.batherphilippa.dropgame.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;
import java.util.Map;

import static com.batherphilippa.dropgame.utils.AssetConstants.*;

public class ResourceManager {

    // TODO - TextureAtlas: at moment assets not in pack - look up TexturePacker

    private Map<String, FileHandle> imageMap;
    private Map<String, Sound> soundMap;
    private Map<String, Music> musicMap;
    public void loadResources() {

        // instantiate hash map and add fx
        soundMap = new HashMap<>();
        soundMap.put(SOUND_DROP, Gdx.audio.newSound(Gdx.files.internal("drop.wav")));

        musicMap = new HashMap<>();
        musicMap.put(MUSIC_THEME, Gdx.audio.newMusic(Gdx.files.internal("rain.mp3")));

        // instantiate hash map and add FileHandles for imgs
        imageMap = new HashMap<>();
        imageMap.put(CHARACTER_PLAYER, Gdx.files.internal("bucket.png"));
        imageMap.put(CHARACTER_ITEM, Gdx.files.internal("drop.png"));
    }

    public FileHandle loadImage(String name) {
        return imageMap.get(name);
    }
    public Music loadMusic(String name) {
        return musicMap.get(name);
    }
    public Sound loadSound(String name) {
        return soundMap.get(name);
    }

    public void playMusic(String name) {
        musicMap.get(name).play();
    }

    public void playSound(String name) {
        soundMap.get(name).play();
    }
    public void dispose() {
        imageMap.clear();
        musicMap.clear();
        soundMap.clear();
    }
}
