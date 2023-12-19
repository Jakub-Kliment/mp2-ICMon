package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.pokemon.actions.Attack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.RunAway;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Latios extends Pokemon {

    /**
     * Default Latios constructor
     * A Latios is a Pokemon that can attack and run away
     *
     * @param area     (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     */
    public Latios(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "latios", 2, 10);
        addFightActions(new Attack(), new RunAway());
    }
}