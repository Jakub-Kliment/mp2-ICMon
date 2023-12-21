package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Display;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class HouseRightKitchen extends ICMonArea {

    /**
     * Getter for the title of the area
     *
     * @return (String) : The title of the area
     */
    @Override
    public String getTitle() {
        return "house_right_kitchen";
    }

    /** Create the area and register the actors */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Door(this, new DiscreteCoordinates(8,1), "town", new DiscreteCoordinates(20, 7), new DiscreteCoordinates(9,1)));
        registerActor(new Display(this, new DiscreteCoordinates(4,5), "tv_news"));
        registerActor(new Door(this, new DiscreteCoordinates(8,7), "house_right_room", new DiscreteCoordinates(2,2)));
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
