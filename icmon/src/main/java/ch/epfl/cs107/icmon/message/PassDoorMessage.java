package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;

public class PassDoorMessage extends GamePlayMessage{

    /** The game state of the game */
    private final ICMon.ICMonGameState gameState;

    /** The door to pass */
    private final Door door;

    /**
     * Default PassDoorMessage constructor
     *
     * @param door (Door): the door to pass
     * @param gameState (ICMon.ICMonGameState): the game state of the game
     */
    public PassDoorMessage(Door door, ICMon.ICMonGameState gameState) {
        this.door = door;
        this.gameState = gameState;
    }

    /**
     * Process the purpose of the message
     * Switch the current area of the game from the area of the door to the area of the destination
     */
    @Override
    public void process() {
        gameState.switchArea(door.getAreaName(), door.getCoordinates());
    }
}
