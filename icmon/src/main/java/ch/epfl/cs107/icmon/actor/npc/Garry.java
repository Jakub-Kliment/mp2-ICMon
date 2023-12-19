package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

public class Garry extends NPCActor implements ICMonFightableActor {
    private List<Pokemon> pokemonList;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     */
    public Garry(Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position, "actors/garry");
        pokemonList = new ArrayList<>();
        pokemonList.add(new Nidoqueen(area, Orientation.DOWN, position));
    }
}
