package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.UnregisterEventAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.Updatable;

import java.util.ArrayList;

public abstract class ICMonEvent implements Updatable, ICMonInteractionVisitor {

    /** The list of actions to perform when the event is completed */
    private final ArrayList<Action> completedAction;

    /** The list of actions to perform when the event is suspended */
    private final ArrayList<Action> suspendedAction;

    /** The list of actions to perform when the event is started */
    private final ArrayList<Action> startedAction;

    /** The list of actions to perform when the event is resumed */
    private final ArrayList<Action> resumedAction;

    /** The player of the game */
    protected final ICMonPlayer player;

    /** True if the event has been started */
    private boolean started;

    /** True if the event has been completed */
    private boolean completed;

    /** True if the event has been suspended */
    private boolean suspended;

    /**
     * Default ICMonEvent constructor
     * Add 2 actions to the event: one to register the event when it is started and one to unregister it when it is completed
     *
     * @param player (ICMonPlayer): the player of the game
     */
    public ICMonEvent(ICMonPlayer player){
        started = false;
        completed = false;
        suspended = false;

        startedAction = new ArrayList<>();
        completedAction = new ArrayList<>();
        suspendedAction = new ArrayList<>();
        resumedAction = new ArrayList<>();

        this.player = player;

        onStart(new RegisterEventAction(player.getEventManager(), this));
        onComplete(new UnregisterEventAction(player.getEventManager(), this));
    }

    /**
     * Start the event by performing all the actions in the startedAction list
     */
    public final void start() {
        if(!started) {
            for (Action action : startedAction) {
                action.perform();
            }
            started = true;
        }
    }

    /**
     * Complete the event by performing all the actions in the completedAction list
     */
    public final void complete() {
        if(started && !completed) {
            for(Action action : completedAction) {
                action.perform();
            }
            completed = true;
        }
    }

    /**
     * Suspend the event by performing all the actions in the suspendedAction list
     */
    public final void suspend() {
        if(started && !completed && !suspended) {
            for (Action action : suspendedAction) {
                action.perform();
            }
            suspended = true;
        }
    }

    /**
     * Resume the event by performing all the actions in the resumedAction list
     */
    public final void resume() {
        if(started && !completed && suspended) {
            for (Action action : resumedAction) {
                action.perform();
            }
            suspended = false;
        }
    }

    /**
     * Add an action to the startedAction list
     *
     * @param action (Action): the action to add
     */
    public final void onStart(Action action){
        startedAction.add(action);
    }

    /**
     * Add an action to the completedAction list
     *
     * @param action (Action): the action to add
     */
    public final void onComplete(Action action){
        completedAction.add(action);
    }

    /**
     * Add an action to the suspendedAction list
     *
     * @param action (Action): the action to add
     */
    public final void onSuspension(Action action){
        suspendedAction.add(action);
    }

    /**
     * Add an action to the resumedAction list
     *
     * @param action (Action): the action to add
     */
    public final void onResume(Action action){
        resumedAction.add(action);
    }

    /**
     * Getter to know if the event is completed
     *
     * @return (boolean): true if the event is completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Getter to know if the event is started
     *
     * @return (boolean): true if the event is started, false otherwise
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Getter to know if the event is suspended
     *
     * @return (boolean): true if the event is suspended, false otherwise
     */
    public boolean isSuspended() {
        return suspended;
    }

    /**
     * Getter to know if the event has a pause menu
     *
     * @return (boolean): true if the event has a pause menu, false otherwise
     */
    public boolean isMenuPause() { return false; }

    /**
     * Get the pause menu of the event if it has one, null otherwise
     *
     * @return (PauseMenu): the pause menu of the event
     */
    public PauseMenu getMenu(){
        return null;
    }
}
