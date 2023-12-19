package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class ResumeMenu implements Action{
    private final ICMon.ICMonGameState gameState;
    public ResumeMenu(ICMon.ICMonGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void perform() {
        gameState.stopMenuPause();
    }
}
