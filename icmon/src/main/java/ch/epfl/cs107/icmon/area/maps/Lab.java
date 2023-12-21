package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Desk;
import ch.epfl.cs107.icmon.actor.Display;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Lab extends ICMonArea {

    /**
     * Getter for the title of the area
     *
     * @return (String) : The title of the area
     */
    @Override
    public String getTitle() {
        return "lab";
    }

    /** Create the area and register the actors */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground((this)));
        ProfOak profOak = new ProfOak(this);
        registerActor(profOak);
        registerActor(new Desk(this, Orientation.DOWN, new DiscreteCoordinates(7, 6), profOak));
        registerActor(new Door(this, new DiscreteCoordinates(7,1), "town", new DiscreteCoordinates(15, 23), new DiscreteCoordinates(6, 1)));
        registerActor(new Display(this, new DiscreteCoordinates(12, 8), "lab_map"));
        registerActor(new Display(this, new DiscreteCoordinates(11, 8), "lab_map"));
        registerActor(new Display(this, new DiscreteCoordinates(10, 8), "lab_pc"));
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
