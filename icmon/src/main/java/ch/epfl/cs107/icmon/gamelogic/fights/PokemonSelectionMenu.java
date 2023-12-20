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

    /** The front size of the text !!!!!!!!!!!!!!!!! */
    private static final float FRONT_SIZE = .6f;

    /** List of the pokemon display on the screen*/
    private final GraphicsEntity[] selectors;

    /** List of the pokemon of the player */
    private final List<Pokemon> pokemonList;

    /** The scale factor of the camera */
    private final float scaleFactor;

    /** The header of the menu */
    private Graphics header;

    /** The pokemon selected by the player */
    private Pokemon choice;

    /** The current choice of the player */
    private int currentChoice;

    /** Boolean to know if the player has a pokemon */
    private boolean hasPokemon;

    /**
     * Constructor for the PokemonSelectionMenu
     * Initialize the current choice in the middle of the list
     *
     * @param pokemonList (List<Pokemon>) : The list of the pokemon of the player
     */
    public PokemonSelectionMenu(List<Pokemon> pokemonList) {
        this.scaleFactor = ICMon.CAMERA_SCALE_FACTOR;
        this.pokemonList = pokemonList;

        selectors = new GraphicsEntity[3];
        currentChoice = pokemonList.size() / 2;

        if (pokemonList.size() % 2 == 0) {
            currentChoice -= 1;
        }
        hasPokemon = true;
    }

    /**
     * Update the menu
     * Update the current choice of the player and the list of the pokemon display on the screen
     *
     *
     * @param deltaTime (float) : The time between two updates
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getKeyboard();

        if (pokemonList.isEmpty()) {
            header = new GraphicsEntity(new Vector(scaleFactor / 2f, scaleFactor / 3 + 5.5f), new TextGraphics("You do not have a Pokemon yet!", FRONT_SIZE, Color.WHITE, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003));
            if (keyboard.get(Keyboard.SPACE).isPressed()) {
                hasPokemon = false;
            }
        } else {
            header = new GraphicsEntity(new Vector(scaleFactor / 2f, scaleFactor / 3 + 5.5f), new TextGraphics("Please, select a Pokemon", FRONT_SIZE, Color.WHITE, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE, 1f, 1003));

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

    /**
     * Getter for the pokemon selected by the player
     *
     * @return (Pokemon) : The pokemon selected by the player
     */
    public Pokemon choice() {
        return choice;
    }

    /**
     * Getter to know if the player has a pokemon
     *
     * @return (boolean) : True if the player has a pokemon, false otherwise
     */
    public boolean hasPokemon() {
        return hasPokemon;
    }

    /**
     * Draw the menu
     * Draw the header and the list of the pokemon display on the screen
     *
     * @param c (Canvas) : The canvas where the menu is drawn
     */
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
