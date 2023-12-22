package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class AddPokemonAction implements Action {

    /** The player to add the pokemon to */
    private final ICMonPlayer player;

    /** The pokemon to add */
    private final Pokemon pokemon;

    /** Boolean to know if you need to diplay a dialog*/
    private final boolean dialog;

    /**
     * Default AddPokemonAction constructor
     *
     * @param player (ICMonPlayer): the player to add the pokemon to
     * @param pokemon (Pokemon): the pokemon to add
     * @param dialog  (boolean): true if the dialog need to be display, false otherwise
     */
    public AddPokemonAction(ICMonPlayer player, Pokemon pokemon, boolean dialog){
        this.player = player;
        this.pokemon = pokemon;
        this.dialog = dialog;
    }

    /**
     * Perform the action, add the pokemon to the player pokemon list
     */
    @Override
    public void perform() {
        player.addPokemon(pokemon, dialog);
    }
}
