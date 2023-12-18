package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class UnregisterEventAction implements Action{
    private ICMon.ICMonEventManager eventManager;
    private ICMonEvent event;
    public UnregisterEventAction(ICMon.ICMonEventManager eventManager, ICMonEvent event){
        this.eventManager = eventManager;
        this.event = event;
    }
    @Override
    public void perform() {
        eventManager.addCompletedEvent(event);
    }
}
