package com.batherphilippa.dropgame.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Stone extends Item {

    public Stone(TextureRegion region) {
        super(region); // TextureRegion passed for Texture, as using a TextureAtlas
        setInitialCoordinates();
    }
}
