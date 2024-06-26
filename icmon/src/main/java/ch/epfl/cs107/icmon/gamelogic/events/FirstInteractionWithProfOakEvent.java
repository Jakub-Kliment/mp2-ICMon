package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.WalkingNPC;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
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
     * Show a dialog when the player interact with the ProfOak and Complete the event by adding a Pikachu to the player pokemon list
     *
     * @param profOak (ProfOak): the profOak who interact in the event
     * @param isCellInteraction (boolean): true if the interaction is a cell interaction, false otherwise
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
     *
     * @param assistant (ICShopAssistant): the assistant who interact in the event
     * @param isCellInteraction (boolean): true if the interaction is a cell interaction, false otherwise
     */
    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction){
            player.openDialog(new Dialog("first_interaction_with_oak_event_icshopassistant"));
        }
    }

    /**
     * Handle the interaction with the NPC during the event
     * Show a dialog when the player interact with the NPC
     *
     * @param npc (WalkingNPC): the NPC who interact in the event
     * @param isCellInteraction (boolean): true if the interaction is a cell interaction, false otherwise
     */
    @Override
    public void interactWith(WalkingNPC npc, boolean isCellInteraction) {
        player.openDialog(new Dialog("npc_first_interaction"));
    }

    /**
     * Handle the interaction with the Garry during the event
     * Show a dialog when the player interact with the Garry
     *
     * @param garry (Garry): the garry who interact in the event
     * @param isCellInteraction (boolean): true if the interaction is a cell interaction, false otherwise
     */
    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        if (!isCellInteraction) {
            player.openDialog(new Dialog("garry_pre_fight"));
        }
    }
}
