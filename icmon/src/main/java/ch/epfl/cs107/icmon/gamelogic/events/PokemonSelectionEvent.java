package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFightAction;
import ch.epfl.cs107.icmon.graphics.PokemonSelectionMenu;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class PokemonSelectionEvent extends ICMonEvent{
    private PokemonSelectionMenu selectionMenu;
    private ICMonActor opponent;
    public PokemonSelectionEvent(ICMonPlayer player, ICMonActor opponent) {
        super(player);
        selectionMenu = new PokemonSelectionMenu(player.getPokemonList());
        this.opponent = opponent;
    }
    @Override
    public void update(float deltaTime) {
        if(selectionMenu.choice() != null){
            onComplete( new AfterPokemonSelectionFightAction(player, selectionMenu.choice(), opponent));
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
