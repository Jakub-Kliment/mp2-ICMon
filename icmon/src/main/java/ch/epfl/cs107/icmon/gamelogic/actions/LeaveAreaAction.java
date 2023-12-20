package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonActor;

public class LeaveAreaAction implements Action{

    /** The actor who leave the area */
    private final ICMonActor actor;

    /**
     * Default LeaveAreaAction constructor
     *
     * @param actor (ICMonActor): the actor who leave the area
     */
    public LeaveAreaAction(ICMonActor actor){
        this.actor = actor;
    }

    /**
     * Perform the action, make the actor leave the area
     */
    @Override
    public void perform() {
        actor.leaveArea();
    }
}
