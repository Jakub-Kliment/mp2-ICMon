package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class EndOfTheGameEvent extends ICMonEvent{

    public EndOfTheGameEvent(ICMonPlayer player) {
        super(player);
    }

    @Override
    public void update(float deltaTime) {}

    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction) {
            player.openDialog(new Dialog("end_of_game_event_interaction_with_icshopassistant"));
        }
    }
}
