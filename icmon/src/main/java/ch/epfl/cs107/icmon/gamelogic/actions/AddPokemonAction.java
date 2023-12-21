package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class AddPokemonAction implements Action {

    /** The player to add the pokemon to */
    private final ICMonPlayer player;

    /** The pokemon to add */
    private final Pokemon pokemon;

    /**
     * Default AddPokemonAction constructor
     *
     * @param player (ICMonPlayer): the player to add the pokemon to
     * @param pokemon (Pokemon): the pokemon to add
     */
    public AddPokemonAction(ICMonPlayer player, Pokemon pokemon){
        this.player = player;
        this.pokemon = pokemon;
    }

    /**
     * Perform the action, add the pokemon to the player pokemon list
     */
    @Override
    public void perform() {
        player.addPokemon(pokemon);
    }
}
