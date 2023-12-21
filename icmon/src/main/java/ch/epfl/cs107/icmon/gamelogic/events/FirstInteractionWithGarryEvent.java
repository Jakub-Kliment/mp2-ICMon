package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.Garry;

public class FirstInteractionWithGarryEvent extends ICMonEvent {

    /**
     * Default FirstInteractionWithGarryEvent constructor
     *
     * @param player (ICMonPlayer): the player who interact in the event
     */
    public FirstInteractionWithGarryEvent(ICMonPlayer player) {
        super(player);
    }

    /**
     * Update the event, does nothing
     */
    @Override
    public void update(float deltaTime) {}

    /**
     * Handle the interaction with the Garry during the event
     * Enter into a fight with Garry
     */
    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        player.fight(garry);
        complete();
    }
}
