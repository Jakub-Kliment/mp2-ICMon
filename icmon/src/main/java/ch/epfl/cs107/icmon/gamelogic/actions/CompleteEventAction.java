package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class CompleteEventAction implements Action {

    /** The event to complete */
    private final ICMonEvent event;

    /**
     * Default CompleteEventAction constructor
     *
     * @param event (ICMonEvent): the event to complete
     */
    public CompleteEventAction(ICMonEvent event){
        this.event = event;
    }

    /**
     * Perform the action, complete the event
     */
    @Override
    public void perform() {
        event.complete();
    }
}
