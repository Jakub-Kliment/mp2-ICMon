package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.engine.PauseMenu;

public class SetPauseAction implements Action{
    private final ICMon.ICMonGameState gameState;
    private final PauseMenu menu;
    public SetPauseAction(ICMon.ICMonGameState gameState, PauseMenu menu){
        this.gameState = gameState;
        this.menu = menu;
    }
    @Override
    public void perform() {
        gameState.setMenuPause(menu);
    }
}