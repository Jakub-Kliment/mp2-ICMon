package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class StartEventAction implements Action{
    private final ICMonEvent event;

    public StartEventAction(ICMonEvent event){
        this.event = event;
    }

    @Override
    public void perform() {
        event.start();
    }
}
