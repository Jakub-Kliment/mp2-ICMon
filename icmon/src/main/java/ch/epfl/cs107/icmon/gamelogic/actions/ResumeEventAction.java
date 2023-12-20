package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class ResumeEventAction implements Action{

    /** The event to resume */
    private final ICMonEvent event;

    /**
     * Default ResumeEventAction constructor
     *
     * @param event (ICMonEvent): the event to resume
     */
    public ResumeEventAction(ICMonEvent event){
        this.event = event;
    }

    /**
     * Perform the action, resume the event
     */
    @Override
    public void perform() {
        event.resume();
    }
}
