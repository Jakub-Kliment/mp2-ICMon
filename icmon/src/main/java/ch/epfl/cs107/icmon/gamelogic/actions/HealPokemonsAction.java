package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class HealPokemonsAction implements Action {
    private final List<Pokemon> pokemonList;
    public HealPokemonsAction(List<Pokemon> pokemonArrayList) {
        pokemonList = new ArrayList<Pokemon>();
        pokemonList.addAll(pokemonArrayList);
    }

    @Override
    public void perform() {
        for (Pokemon pokemon : pokemonList) {
            pokemon.heal();
        }
    }
}
