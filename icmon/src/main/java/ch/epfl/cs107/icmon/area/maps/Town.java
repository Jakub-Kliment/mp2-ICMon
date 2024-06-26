package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Display;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.WalkingNPC;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Town extends ICMonArea {

    /**
     * Getter for the spawn position of the player
     *
     * @return (DiscreteCoordinates): the spawn position of the player
     */
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 5);
    }

    /** Create the area and register the actors */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));

        registerActor(new Door(this, new DiscreteCoordinates(15,24), "lab", new DiscreteCoordinates(6,2)));
        registerActor(new Door(this, new DiscreteCoordinates(20,16), "arena", new DiscreteCoordinates(4,2)));
        registerActor(new Door(this, new DiscreteCoordinates(7,27), "house", new DiscreteCoordinates(3,2)));
        registerActor(new Door(this, new DiscreteCoordinates(25,20), "shop", new DiscreteCoordinates(3,2)));
        registerActor(new Door(this, new DiscreteCoordinates(10, 13), "house_left_kitchen", new DiscreteCoordinates(8,2)));
        registerActor(new Door(this, new DiscreteCoordinates(20, 8), "house_right_kitchen", new DiscreteCoordinates(8,2)));
        registerActor(new Display(this, new DiscreteCoordinates(8, 23), "town_sign_sacha"));
        registerActor(new Display(this, new DiscreteCoordinates(17, 22), "town_sign_lab"));
        registerActor(new Display(this, new DiscreteCoordinates(17, 16), "town_sign_arena"));
        DiscreteCoordinates[] coordinates = {new DiscreteCoordinates(10, 12), new DiscreteCoordinates(15, 12), new DiscreteCoordinates(15, 20), new DiscreteCoordinates(15, 7), new DiscreteCoordinates(15, 12)};
        registerActor(new WalkingNPC(this, "actors/NPC_red", Orientation.RIGHT, coordinates));
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
