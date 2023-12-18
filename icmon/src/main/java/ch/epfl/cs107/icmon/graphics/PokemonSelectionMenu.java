package ch.epfl.cs107.icmon.graphics;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.Graphics;
import ch.epfl.cs107.play.engine.actor.GraphicsEntity;
import ch.epfl.cs107.play.engine.actor.ImageGraphics;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.io.ResourcePath;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Objects.nonNull;

public class PokemonSelectionMenu extends PauseMenu {

    private static final float FONT_SIZE = .6f;

    private final float scaleFactor;
    private final GraphicsEntity[] selectors;
    private final List<Pokemon> pokemonList;
    private final Graphics header;

    private Pokemon choice;

    private int currentChoice;

    public PokemonSelectionMenu(List<Pokemon> pokemonList) {
        assert !pokemonList.isEmpty();
        this.scaleFactor = ICMon.CAMERA_SCALE_FACTOR;
        this.pokemonList = pokemonList;
        selectors = new GraphicsEntity[3];
        header = new GraphicsEntity(new Vector(scaleFactor / 2f, scaleFactor / 3 + 5.5f), new TextGraphics("Please, select a Pokemon", FONT_SIZE, Color.WHITE, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003));
        currentChoice = 1;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getKeyboard();
        // HR : Keyboard management
        if (keyboard.get(Keyboard.LEFT).isPressed()){
            currentChoice = max(0, currentChoice - 1);
        } else if (keyboard.get(Keyboard.RIGHT).isPressed())
            currentChoice = min(currentChoice + 1, pokemonList.size() - 1);
        else if (keyboard.get(Keyboard.ENTER).isPressed())
            choice = pokemonList.get(currentChoice);
        // HR : Prepare the left selector
        if (currentChoice == 0){
            selectors[0] = null;
        } else {
            var spriteName = "pokemon/" + pokemonList.get(currentChoice-1).properties().name();
            var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scaleFactor / 2.5f, scaleFactor / 2.5f);
            image.setAlpha(.6f);
            selectors[0] = new GraphicsEntity(new Vector(scaleFactor / 3 - 4f, scaleFactor / 2 - 4f), image);
        }
        // HR : Prepare the middle selector
        {
            var spriteName = "pokemon/" + pokemonList.get(currentChoice).properties().name();
            var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scaleFactor / 2.5f, scaleFactor / 2.5f);
            selectors[1] = new GraphicsEntity(new Vector(scaleFactor / 3, scaleFactor / 2 - 4f), image);
        }
        // HR : Prepare the Right selector
        if (currentChoice == pokemonList.size() - 1 ){
            selectors[2] = null;
        } else {
            var spriteName = "pokemon/"+ pokemonList.get(currentChoice + 1).properties().name();
            var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scaleFactor / 2.5f, scaleFactor / 2.5f);
            image.setAlpha(.6f);
            selectors[2] = new GraphicsEntity(new Vector(scaleFactor / 3 + 4f, scaleFactor / 2 - 4f), image);
        }
    }

    public Pokemon choice(){
        return choice;
    }


    @Override
    protected void drawMenu(Canvas c) {
        // HR : Draw the header
        header.draw(c);
        // HR : Draw the selectors that are visible (not null)
        for (var selector : selectors)
            if(nonNull(selector)) {
                selector.draw(c);
            }
    }
}
