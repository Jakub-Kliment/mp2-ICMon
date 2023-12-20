package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.engine.PauseMenu;

public class SetPauseAction implements Action{

    /** The game state of the game */
    private final ICMon.ICMonGameState gameState;

    /** The pause menu */
    private final PauseMenu menu;

    /**
     * Default SetPauseAction constructor
     *
     * @param gameState (ICMon.ICMonGameState): the game state of the game
     * @param menu (PauseMenu): the pause menu
     */
    public SetPauseAction(ICMon.ICMonGameState gameState, PauseMenu menu){
        this.gameState = gameState;
        this.menu = menu;
    }

    /**
     * Pause the game and show pause menu
     */
    @Override
    public void perform() {
        gameState.setMenuPause(menu);
    }
}