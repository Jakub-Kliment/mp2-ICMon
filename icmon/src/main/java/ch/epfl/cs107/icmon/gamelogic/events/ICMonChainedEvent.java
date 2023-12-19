package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;

public class ICMonChainedEvent extends ICMonEvent {
    public ICMonChainedEvent(ICMonPlayer player, ICMonEvent initial, ICMonEvent ... chain) {
        super(player);
        StartEventAction event = new StartEventAction(initial);
    }

    @Override
    public void update(float deltaTime) {
    }
}
