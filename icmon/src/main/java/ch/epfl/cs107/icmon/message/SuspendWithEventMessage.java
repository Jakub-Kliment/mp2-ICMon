package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;


public class SuspendWithEventMessage extends GamePlayMessage{
    private ICMonEvent event;
    public SuspendWithEventMessage(ICMonEvent event){
        this.event = event;
    }
    @Override
    public void process() {}
    public ICMonEvent getEvent() {
        return event;
    }
}
