package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Display;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.actor.pokemon.Pikachu;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Arena extends ICMonArea {

    /**
     * Getter for the title of the area
     *
     * @return (String) : The title of the area
     */
    @Override
    public String getTitle() {
        return "arena";
    }

    /**
     * Create the area and register the actors
     */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground((this)));
        // All pokemons can be fought
        registerActor(new Bulbizarre(this, Orientation.DOWN, new DiscreteCoordinates(1, 4)));
        registerActor(new Latios(this, Orientation.DOWN, new DiscreteCoordinates(2, 6)));
        registerActor(new Nidoqueen(this, Orientation.DOWN, new DiscreteCoordinates(6, 6)));
        registerActor(new Pikachu(this, Orientation.DOWN, new DiscreteCoordinates(7, 4)));

        registerActor(new Door(this, new DiscreteCoordinates(4,1), "town", new DiscreteCoordinates(20, 15), new DiscreteCoordinates(5, 1)));
        registerActor(new Display(this, new DiscreteCoordinates(1, 7), "arena_door"));
        registerActor(new Display(this, new DiscreteCoordinates(7, 7), "arena_door"));
    }

    /**
     * Getter for the spawn position of the player
     *
     * @return (DiscreteCoordinates): the spawn position of the player
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return null;
    }
}
