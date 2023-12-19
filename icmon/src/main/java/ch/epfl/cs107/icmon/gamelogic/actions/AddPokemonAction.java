package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class AddPokemonAction implements Action{
    private final ICMonPlayer player;
    private final Pokemon pokemon;
    public AddPokemonAction(ICMonPlayer player, Pokemon pokemon){
        this.player = player;
        this.pokemon = pokemon;
    }

    @Override
    public void perform() {
        player.addPokemon(pokemon);
    }
}
