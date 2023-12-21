package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.CompleteEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ICMonChainedEvent extends ICMonEvent {

    /**
     * Default ICMonChainedEvent constructor
     * Create a chain of events that will be executed one after the other
     *
     * @param player (ICMonPlayer): the player who interact in the event
     * @param initial (ICMonEvent): the first event of the chain
     * @param chain (ICMonEvent): the other events of the chain
     */
    public ICMonChainedEvent(ICMonPlayer player, ICMonEvent initial, ICMonEvent ... chain) {
        super(player);
        List<ICMonEvent> eventList = new ArrayList<ICMonEvent>();
        eventList.add(initial);
        Collections.addAll(eventList, chain);

        onStart(new StartEventAction(eventList.get(0)));

        for(int i = 0; i < eventList.size() - 1; i++) {
            eventList.get(i).onComplete(new StartEventAction(eventList.get(i+1)));
        }
        eventList.get(eventList.size() - 1).onComplete(new CompleteEventAction(this));
    }

    /**
     * Update the event, does nothing
     */
    @Override
    public void update(float deltaTime) {}
}
