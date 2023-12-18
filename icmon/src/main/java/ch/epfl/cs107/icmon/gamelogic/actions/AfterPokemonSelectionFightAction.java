package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;

public class AfterPokemonSelectionFightAction implements Action{
    private ICMonActor opponent;
    private ICMonPlayer player;
    private Pokemon playerPokemon;
    public AfterPokemonSelectionFightAction(ICMonPlayer player, Pokemon playerPokemon ,ICMonActor opponent){
        this.player = player;
        this.opponent = opponent;
        this.playerPokemon = playerPokemon;
    }
    @Override
    public void perform() {
        PokemonFightEvent combat = new PokemonFightEvent(player, playerPokemon, opponent);
        player.getGameState().send(new SuspendWithEventMessage(combat));
    }
}
