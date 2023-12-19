package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;

public class PassDoorMessage extends GamePlayMessage{
    private final ICMon.ICMonGameState gameState;
    private final Door door;
    public PassDoorMessage(Door door, ICMon.ICMonGameState gameState) {
        this.door = door;
        this.gameState = gameState;
    }

    @Override
    public void process() {
        gameState.switchArea(door.getAreaName(), door.getCoordinates());
    }
}
