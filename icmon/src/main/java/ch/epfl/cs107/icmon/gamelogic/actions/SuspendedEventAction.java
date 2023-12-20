package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class SuspendedEventAction implements Action {

    /** The event to suspend */
    private final ICMonEvent event;

    /**
     * Default SuspendedEventAction constructor
     *
     * @param event (ICMonEvent): the event to suspend
     */
    public SuspendedEventAction(ICMonEvent event) {
        this.event = event;
    }

    /**
     * Perform the action, suspend the event
     */
    @Override
    public void perform() {
        event.suspend();
    }
}