package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class RegisterEventAction implements Action {
    private final ICMon.ICMonEventManager eventManager;
    private final ICMonEvent event;
    public RegisterEventAction(ICMon.ICMonEventManager eventManager, ICMonEvent event){
        this.eventManager = eventManager;
        this.event = event;
    }

    @Override
    public void perform() {
        eventManager.addStartedEvent(event);
    }
}
