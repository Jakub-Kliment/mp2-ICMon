package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class ResumeMenu implements Action{

    /** The game state of the game */
    private final ICMon.ICMonGameState gameState;

    /**
     * Default ResumeMenu constructor
     *
     * @param gameState (ICMon.ICMonGameState): the game state of the game
     */
    public ResumeMenu(ICMon.ICMonGameState gameState){
        this.gameState = gameState;
    }

    /**
     * Perform the action, resume the menu
     */
    @Override
    public void perform() {
        gameState.stopMenuPause();
    }
}
