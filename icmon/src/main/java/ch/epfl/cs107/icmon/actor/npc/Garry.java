package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
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
    /** The list of pokemons of Garry */
    private final List<Pokemon> pokemonList;

    /**
     * Default Garry constructor
     * Add a Nidoqueen to the list of pokemons
     *
     * @param area     (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     */
    public Garry(Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position, "actors/garry");
        pokemonList = new ArrayList<>();
        pokemonList.add(new Nidoqueen(area, Orientation.DOWN, position));
    }

    /**
     * Garry will use is only pokemon
     *
     * @return (Pokemon) : The pokemon Garry will use in the fight
     */
    @Override
    public Pokemon choosenPokemon() {
        return pokemonList.get(0);
    }

    /**
     * Delegate interactions to the interaction handler
     *
     * @param v (AreaInteractionVisitor) : the interactor that wants to interact with this interactable
     * @param isCellInteraction : @param isCellInteraction : true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}
