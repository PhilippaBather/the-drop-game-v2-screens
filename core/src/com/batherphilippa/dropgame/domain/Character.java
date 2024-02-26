package com.batherphilippa.dropgame.domain;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.batherphilippa.dropgame.utils.KeyDirection;

public abstract class Character {

//    Texture texture;
    TextureRegion region;
    Rectangle rectangle;
    Vector2 coords;

    public Character(TextureRegion region) {
        this.region = region;  // TextureRegion instead of Texture, as using TextureAtlas
        this.rectangle = new Rectangle();
        this.coords = new Vector2();
    }

    public abstract void setInitialCoordinates();
    public void render(SpriteBatch batch) {
//        batch.draw(texture, rectangle.x, rectangle.y);  // previous code
        batch.draw(region, rectangle.x, rectangle.y);   // using TextureAtlas
    }
    public abstract void move(Vector2 position, KeyDirection dir);
    public abstract void update();
}
