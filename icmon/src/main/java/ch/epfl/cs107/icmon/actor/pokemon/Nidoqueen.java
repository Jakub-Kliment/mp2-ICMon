package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.pokemon.actions.Attack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.RunAway;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Nidoqueen extends Pokemon {

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area         (Area): Owner area. Not null
     * @param orientation  (Orientation): Initial orientation of the entity. Not null
     * @param position     (Coordinate): Initial position of the entity. Not null
     */
    public Nidoqueen(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "nidoqueen", 1, 10);
        addFightAction(new Attack(), new RunAway());
    }
}
