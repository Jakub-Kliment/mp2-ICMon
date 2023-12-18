package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class ProfOak extends NPCActor {
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     */
    public ProfOak(Area area) {
        super(area, Orientation.DOWN, new DiscreteCoordinates(11,7), "actors/prof-oak");
    }
}
