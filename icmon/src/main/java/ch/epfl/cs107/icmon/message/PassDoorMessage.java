package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.actor.Door;

public class PassDoorMessage extends GamePlayMessage{
    private Door door;
    public PassDoorMessage(Door door){
        this.door = door;
    }
    @Override
    public void process() {
        
    }
}
