package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFightAction;
import ch.epfl.cs107.icmon.gamelogic.actions.CompleteEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.graphics.PokemonSelectionMenu;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class PokemonSelectionEvent extends ICMonEvent{
    private PokemonSelectionMenu selectionMenu;
    private ICMonActor opponent;
    private ICMon.ICMonGameState gameState;
    public PokemonSelectionEvent(ICMonPlayer player, ICMonActor opponent, ICMon.ICMonGameState gameState) {
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
