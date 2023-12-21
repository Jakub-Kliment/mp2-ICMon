package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class StartEventAction implements Action {

    /** The event to start */
    private final ICMonEvent event;

    /**
     * Default StartEventAction constructor
     *
     * @param event (ICMonEvent): the event to start
     */
    public StartEventAction(ICMonEvent event){
        this.event = event;
    }

    /**
     * Perform the action, start the event
     */
    @Override
    public void perform() {
        event.start();
    }
}
