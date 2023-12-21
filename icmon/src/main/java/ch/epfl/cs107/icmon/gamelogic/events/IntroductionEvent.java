package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.OpenDialogAction;

public class IntroductionEvent extends ICMonEvent{

    /**
     * Default IntroductionEvent constructor
     * Start the event by opening an introduction dialog
     *
     * @param player (ICMonPlayer): the player who interact in the event
     */
    public IntroductionEvent(ICMonPlayer player) {
        super(player);
        onStart(new OpenDialogAction(player, "welcome_to_icmon"));
        new LogAction("Welcome to ICMon!");
    }

    /**
     * Update the event
     * Complete the event when the player finish the dialog
     */
    @Override
    public void update(float deltaTime) {
        if (player.isDialogCompleted())
            complete();
    }
}
