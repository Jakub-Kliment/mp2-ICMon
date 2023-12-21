package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class HealPokemonsAction implements Action {

    /** List of pokemons */
    private final List<Pokemon> pokemonList;

    /**
     * Default HealPokemonsAction constructor
     *
     * @param pokemonArrayList (List<Pokemon>): list of player's pokemons to heal
     */
    public HealPokemonsAction(List<Pokemon> pokemonArrayList) {
        pokemonList = new ArrayList<Pokemon>();
        pokemonList.addAll(pokemonArrayList);
    }

    /**
     * Perform the action, heal all pokemons
     */
    @Override
    public void perform() {
        for (Pokemon pokemon : pokemonList) {
            pokemon.heal();
        }
    }
}
