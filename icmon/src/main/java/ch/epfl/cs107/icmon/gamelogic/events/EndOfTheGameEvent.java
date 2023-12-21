package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class EndOfTheGameEvent extends ICMonEvent {

    /**
     * Default EndOfTheGameEvent constructor
     *
     * @param player (ICMonPlayer): the player who interacts in the event
     */
    public EndOfTheGameEvent(ICMonPlayer player) {
        super(player);
    }

    /**
     * Handle the interaction with the ICShopAssistant during the event
     * Show a dialog when the player interact with the ICShopAssistant
     */
    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction) {
            player.openDialog(new Dialog("end_of_game_event_interaction_with_icshopassistant"));
        }
    }

    /**
     * Update the event, does nothing because the event is a dialog
     */
    @Override
    public void update(float deltaTime) {}
}
