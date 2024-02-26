package com.batherphilippa.dropgame.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Raindrop extends Item {
    public Raindrop(TextureRegion region) {
        super(region);  // TextureRegion not Texture, as using TextureAtlas
        setInitialCoordinates();
    }
}
