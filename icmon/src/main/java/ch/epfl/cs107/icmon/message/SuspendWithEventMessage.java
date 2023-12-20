package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class SuspendWithEventMessage extends GamePlayMessage{

    /** The event wich suspend the game */
    private final ICMonEvent event;

    /**
     * Default SuspendWithEventMessage constructor
     *
     * @param event (ICMonEvent): the event wich suspend the game
     */
    public SuspendWithEventMessage(ICMonEvent event){
        this.event = event;
    }

    /**
     * Process the purpose of the message
     * Do nothing, the message is just a container for the event
     */
    @Override
    public void process() {}

    /**
     * Getter for the event
     *
     * @return (ICMonEvent): the event wich suspend the game
     */
    public ICMonEvent getEvent() {
        return event;
    }
}
