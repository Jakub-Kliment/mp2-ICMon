package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.Pikachu;
import ch.epfl.cs107.icmon.gamelogic.actions.AddBallAction;
import ch.epfl.cs107.icmon.gamelogic.actions.AddPokemonAction;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.math.Orientation;

public class FirstInteractionWithProfOakEvent extends ICMonEvent {

    /**
     * Default FirstInteractionWithProfOakEvent constructor
     *
     * @param player (ICMonPlayer): the player who interact in the event
     */
    public FirstInteractionWithProfOakEvent(ICMonPlayer player) {
        super(player);
    }

    /**
     * Update the event, does nothing
     */
    @Override
    public void update(float deltaTime) {}

    /**
     * Handle the interaction with the ProfOak during the event
     * Show a dialog when the player interact with the ProfOak and Complete the event by adding a Latios to the player pokemon list
     *
     */
    @Override
    public void interactWith(ProfOak profOak, boolean isCellInteraction) {
        if (!isCellInteraction) {
            player.openDialog(new Dialog("first_interaction_with_prof_oak"));
            onComplete(new AddBallAction(player));
            onComplete(new AddBallAction(player));
            onComplete(new AddPokemonAction(player, new Pikachu(player.getCurrentArea(), Orientation.DOWN, player.getCurrentCells().get(0)), false));
            complete();
        }
    }

    /**
     * Handle the interaction with the ICShopAssistant during the event
     * Show a dialog when the player interact with the ICShopAssistant
     */
    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction){
            player.openDialog(new Dialog("first_interaction_with_oak_event_icshopassistant"));
        }
    }
}
