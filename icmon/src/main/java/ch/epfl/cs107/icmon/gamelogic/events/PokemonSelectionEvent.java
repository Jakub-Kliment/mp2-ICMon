package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFightAction;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.play.engine.PauseMenu;

public class PokemonSelectionEvent extends ICMonEvent{
    private PokemonSelectionMenu selectionMenu;
    private ICMonFightableActor opponent;
    private ICMon.ICMonGameState gameState;
    public PokemonSelectionEvent(ICMonPlayer player, ICMonFightableActor opponent, ICMon.ICMonGameState gameState) {
        super(player);
        selectionMenu = new PokemonSelectionMenu(player.getPokemonList());
        this.opponent = opponent;
        this.gameState = gameState;
    }
    @Override
    public void update(float deltaTime) {
        if(selectionMenu.choice() != null) {
            onComplete(new AfterPokemonSelectionFightAction(player, selectionMenu.choice(), opponent, gameState));
            complete();
        }
        if (!selectionMenu.hasPokemon()) {
            complete();
        }
    }

    @Override
    public boolean isMenuPause() {
        return true;
    }

    @Override
    public PauseMenu getMenu() {
        return selectionMenu;
    }
}
