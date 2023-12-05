package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.Updatable;

import java.util.ArrayList;

public abstract class ICMonEvent implements Updatable, ICMonInteractionVisitor {
    private boolean started;
    private boolean completed;
    private boolean suspended;
    private final ArrayList<Action> startedAction;
    private final ArrayList<Action> completedAction;
    private final ArrayList<Action> suspendedAction;
    private final ArrayList<Action> resumedAction;
    private ICMonPlayer player;

    public ICMonEvent(ICMonPlayer player){
        started = false;
        completed = false;
        suspended = false;
        //A demander
        startedAction = new ArrayList<>();
        completedAction = new ArrayList<>();
        suspendedAction = new ArrayList<>();
        resumedAction = new ArrayList<>();
        this.player = player;
    }

    public final void start(){
        if (!started){
            for (Action action : startedAction){
                action.perform();
            }
            started = true;
        }
    }

    public final void complete(){
        if(started && !completed){
            for (Action action : completedAction){
                action.perform();
            }
            completed = true;
        }
    }

    public final void suspend(){
        if(started && !completed && !suspended){
            for (Action action : suspendedAction){
                action.perform();
            }
            suspended = true;
        }
    }

    public final void resume(){
        if(started && !completed && suspended){
            for (Action action : resumedAction){
                action.perform();
            }
            suspended = false;
        }
    }

    public final void onStart(Action action){
        startedAction.add(action);
    }

    public final void onComplete(Action action){
        completedAction.add(action);
    }

    public final void onSuspension(Action action){
        suspendedAction.add(action);
    }

    public final void onResume(Action action){
        resumedAction.add(action);
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isSuspended() {
        return suspended;
    }
}
