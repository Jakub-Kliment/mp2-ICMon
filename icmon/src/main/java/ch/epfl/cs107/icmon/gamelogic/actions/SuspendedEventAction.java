package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

import javax.annotation.processing.SupportedSourceVersion;

public class SuspendedEventAction implements Action{
    private ICMonEvent event;
    public SuspendedEventAction(ICMonEvent event){
        this.event = event;
    }
    @Override
    public void perform() {
        event.suspend();
    }
}