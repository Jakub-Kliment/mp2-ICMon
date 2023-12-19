package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
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
    private final GraphicsEntity[] selectors;
    private final List<Pokemon> pokemonList;
    private final float scaleFactor;
    private Graphics header;
    private Pokemon choice;
    private int currentChoice;
    private boolean hasPokemon;

    public PokemonSelectionMenu(List<Pokemon> pokemonList) {
        assert !pokemonList.isEmpty();
        this.scaleFactor = ICMon.CAMERA_SCALE_FACTOR;
        this.pokemonList = pokemonList;

        selectors = new GraphicsEntity[3];
        currentChoice = pokemonList.size() / 2;

        if (pokemonList.size() % 2 == 0) {
            currentChoice -= 1;
        }
        hasPokemon = true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getKeyboard();

        if (pokemonList.isEmpty()) {
            header = new GraphicsEntity(new Vector(scaleFactor / 2f, scaleFactor / 3 + 5.5f), new TextGraphics("You do not have a Pokemon yet!", FONT_SIZE, Color.WHITE, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003));
            if (keyboard.get(Keyboard.SPACE).isPressed()) {
                hasPokemon = false;
            }
        } else {
            header = new GraphicsEntity(new Vector(scaleFactor / 2f, scaleFactor / 3 + 5.5f), new TextGraphics("Please, select a Pokemon", FONT_SIZE, Color.WHITE, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE, 1f, 1003));

            if (keyboard.get(Keyboard.LEFT).isPressed()) {
                currentChoice = max(0, currentChoice - 1);
            } else if (keyboard.get(Keyboard.RIGHT).isPressed()) {
                currentChoice = min(currentChoice + 1, pokemonList.size() - 1);
            } else if (keyboard.get(Keyboard.ENTER).isPressed()) {
                choice = pokemonList.get(currentChoice);
            }

            if (currentChoice == 0) {
                selectors[0] = null;
            } else {
                var spriteName = "pokemon/" + pokemonList.get(currentChoice - 1).properties().name();
                var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scaleFactor / 2.5f, scaleFactor / 2.5f);
                image.setAlpha(.6f);
                selectors[0] = new GraphicsEntity(new Vector(scaleFactor / 3 - 4f, scaleFactor / 2 - 4f), image);
            }
            {
                var spriteName = "pokemon/" + pokemonList.get(currentChoice).properties().name();
                var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scaleFactor / 2.5f, scaleFactor / 2.5f);
                selectors[1] = new GraphicsEntity(new Vector(scaleFactor / 3, scaleFactor / 2 - 4f), image);
            }
            if (currentChoice == pokemonList.size() - 1 ) {
                selectors[2] = null;
            } else {
                var spriteName = "pokemon/"+ pokemonList.get(currentChoice + 1).properties().name();
                var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scaleFactor / 2.5f, scaleFactor / 2.5f);
                image.setAlpha(.6f);
                selectors[2] = new GraphicsEntity(new Vector(scaleFactor / 3 + 4f, scaleFactor / 2 - 4f), image);
            }
        }
    }

    public Pokemon choice() {
        return choice;
    }

    public boolean hasPokemon() {
        return hasPokemon;
    }

    @Override
    protected void drawMenu(Canvas c) {
        if (header != null) {
            header.draw(c);
        }

        for (var selector : selectors) {
            if (nonNull(selector)) {
                selector.draw(c);
            }
        }
    }
}
