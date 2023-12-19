package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;

public class AfterPokemonSelectionFightAction implements Action{
    private ICMonFightableActor opponent;
    private ICMonPlayer player;
    private Pokemon playerPokemon;
    private ICMon.ICMonGameState gameState;
    private boolean retire;
    public AfterPokemonSelectionFightAction(ICMonPlayer player, Pokemon playerPokemon , ICMonFightableActor opponent, ICMon.ICMonGameState gameState){
        this.player = player;
        this.opponent = opponent;
        this.playerPokemon = playerPokemon;
        this.gameState = gameState;
    }
    @Override
    public void perform() {
        PokemonFightEvent combat = new PokemonFightEvent(player, playerPokemon, opponent);
        gameState.send(new SuspendWithEventMessage(combat));
    }
}
