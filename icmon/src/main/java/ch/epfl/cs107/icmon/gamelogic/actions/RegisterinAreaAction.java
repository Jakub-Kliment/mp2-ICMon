package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class RegisterinAreaAction implements Action{
    private Area area;
    private Actor actor;
    public RegisterinAreaAction(Area area, Actor actor){
        this.area = area;
        this.actor = actor;
    }
    @Override
    public void perform() {
        //A DEMANDER !!!!!!!!!
        area.registerActor(actor);
    }
}
