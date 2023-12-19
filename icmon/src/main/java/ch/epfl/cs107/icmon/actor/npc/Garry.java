package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

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

    @Override
    public Pokemon choosenPokemon() {
        return pokemonList.get(0);
    }

    @Override
    public boolean retire() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}
