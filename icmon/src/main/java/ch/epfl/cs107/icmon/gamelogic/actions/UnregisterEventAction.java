package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class UnregisterEventAction implements Action{

    /** The event manager of the game */
    private final ICMon.ICMonEventManager eventManager;

    /** The event to register */
    private final ICMonEvent event;

    /**
     * Default RegisterEventAction constructor
     *
     * @param eventManager (ICMon.ICMonEventManager): the event manager of the game
     * @param event (ICMonEvent): the event to register
     */
    public UnregisterEventAction(ICMon.ICMonEventManager eventManager, ICMonEvent event) {
        this.eventManager = eventManager;
        this.event = event;
    }

    /**
     * Perform the action, register the event
     */
    @Override
    public void perform() {
        eventManager.addCompletedEvent(event);
    }
}
