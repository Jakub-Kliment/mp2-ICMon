package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.CompleteEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ICMonChainedEvent extends ICMonEvent {
    private List<ICMonEvent> eventList;
    public ICMonChainedEvent(ICMonPlayer player, ICMonEvent initial, ICMonEvent ... chain) {
        super(player);
        eventList = new ArrayList<ICMonEvent>();
        eventList.add(initial);
        Collections.addAll(eventList, chain);
        onStart(new StartEventAction(eventList.get(0)));
        for(int i = 0; i < eventList.size()-1; i++){
            eventList.get(i).onComplete(new StartEventAction(eventList.get(i+1)));
        }
        eventList.get(eventList.size()-1).onComplete(new CompleteEventAction(this));
    }

    @Override
    public void update(float deltaTime) {}
}
