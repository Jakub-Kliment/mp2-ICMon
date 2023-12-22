package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Desk;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.WalkingNPC;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Shop extends ICMonArea {

    /**
     * Getter for the title of the area
     *
     * @return (String) : The title of the area
     */
    @Override
    public String getTitle() {
        return "shop";
    }

    /** Create the area and register the actor */
    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground((this)));
        registerActor(new Door(this, new DiscreteCoordinates(3,1), "town", new DiscreteCoordinates(25, 19), new DiscreteCoordinates(4, 1)));
        ICShopAssistant assistant = new ICShopAssistant(this, Orientation.DOWN, new DiscreteCoordinates(1,5));
        registerActor(assistant);
        registerActor(new Desk(this, Orientation.DOWN, new DiscreteCoordinates(1, 4), assistant));
        DiscreteCoordinates[] listCoordonne = {new DiscreteCoordinates(8,1), new DiscreteCoordinates(8,6), new DiscreteCoordinates(5,6), new DiscreteCoordinates(5,2)};
        registerActor(new WalkingNPC(this, "actors/NPC_blue", Orientation.UP, listCoordonne));

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
