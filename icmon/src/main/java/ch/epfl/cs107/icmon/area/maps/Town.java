package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Town extends ICMonArea {

    /**
     * Getter for the spawn position of the player
     *
     * @return (DiscreteCoordinates): the spawn position of the player
     */
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 5);
    }

    /**
     * Create the area and register the actors
     */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door(this, new DiscreteCoordinates(15,24), "lab", new DiscreteCoordinates(6,2)));
        registerActor(new Door(this, new DiscreteCoordinates(20,16), "arena", new DiscreteCoordinates(4,2)));
        registerActor(new Door(this, new DiscreteCoordinates(7,27), "house", new DiscreteCoordinates(2,2)));
        registerActor(new Door(this, new DiscreteCoordinates(25,20), "shop", new DiscreteCoordinates(3,2)));
    }

    /**
     * Getter for the title of the area
     *
     * @return (String) : The title of the area
     */
    @Override
    public String getTitle() {
        return "town";
    }
}
