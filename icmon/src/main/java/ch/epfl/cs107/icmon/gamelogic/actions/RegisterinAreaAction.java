package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class RegisterinAreaAction implements Action{

    /** The area to register the actor to */
    private final Area area;

    /** The actor to register */
    private final Actor actor;

    /**
     * Default RegisterinAreaAction constructor
     *
     * @param area (Area): the area to register the actor to
     * @param actor (Actor): the actor to register
     */
    public RegisterinAreaAction(Area area, Actor actor){
        this.area = area;
        this.actor = actor;
    }

    /**
     * Perform the action, register the actor to the area
     */
    @Override
    public void perform() {
        area.registerActor(actor);
    }
}
