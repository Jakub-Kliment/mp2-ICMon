package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.gamelogic.actions.OpenDialogAction;

public class IntroductionEvent extends ICMonEvent{
    public IntroductionEvent(ICMonPlayer player) {
        super(player);
        onStart(new OpenDialogAction(player, "welcome_to_icmon"));
    }

    @Override
    public void update(float deltaTime) {
        if (player.isDialogCompleted())
            complete();
    }
}
