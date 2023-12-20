package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;

public class AfterPokemonSelectionFightAction implements Action{

    /** The game state of the game */
    private final ICMon.ICMonGameState gameState;

    /** The opponent of the player */
    private final ICMonFightableActor opponent;

    /** The player pokemon */
    private final Pokemon playerPokemon;

    /** The player */
    private final ICMonPlayer player;

    /**
     * Default AfterPokemonSelectionFightAction constructor
     *
     * @param player (ICMonPlayer): the player
     * @param playerPokemon (Pokemon): the player pokemon
     * @param opponent (ICMonFightableActor): the opponent of the player
     * @param gameState (ICMon.ICMonGameState): the game state of the game
     */
    public AfterPokemonSelectionFightAction(ICMonPlayer player, Pokemon playerPokemon , ICMonFightableActor opponent, ICMon.ICMonGameState gameState){
        this.player = player;
        this.opponent = opponent;
        this.playerPokemon = playerPokemon;
        this.gameState = gameState;
    }

    /**
     * Perform the action, suspend the game and start the fight
     */
    @Override
    public void perform() {
        PokemonFightEvent combat = new PokemonFightEvent(player, playerPokemon, opponent);
        gameState.send(new SuspendWithEventMessage(combat));
    }
}
