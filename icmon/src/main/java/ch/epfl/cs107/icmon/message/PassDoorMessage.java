package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class PassDoorMessage extends GamePlayMessage{
    private Door door;
    private ICMon.ICMonGameState gameState;
    public PassDoorMessage(Door door, ICMon.ICMonGameState gameState){
        this.door = door;
        this.gameState = gameState;
    }
    @Override
    public void process() {
        gameState.switchArea(door.getAreaName(), door.getCoordinates());
    }
}
