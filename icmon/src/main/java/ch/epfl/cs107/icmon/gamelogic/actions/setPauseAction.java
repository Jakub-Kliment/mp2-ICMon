package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.engine.PauseMenu;

public class setPauseAction implements Action{
    private ICMon.ICMonGameState gameState;
    private PauseMenu menu;
    public setPauseAction(ICMon.ICMonGameState gameState, PauseMenu menu){
        this.gameState = gameState;
        this.menu = menu;
    }
    @Override
    public void perform() {
        gameState.setMenuPause(menu);
    }
}
