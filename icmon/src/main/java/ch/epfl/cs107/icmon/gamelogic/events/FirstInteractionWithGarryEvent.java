package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.WalkingNPC;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class FirstInteractionWithGarryEvent extends ICMonEvent {

    /** The garry who interact in the event */
    private Garry garry;

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
    public void update(float deltaTime) {
        if (garry != null && player.isDialogCompleted()) {
            player.fight(garry);
            complete();
        }
    }

    /**
     * Handle the interaction with the Garry during the event
     * Enter into a fight with Garry
     *
     * @param garry (Garry): the garry who interact in the event
     * @param isCellInteraction (boolean): true if the interaction is a cell interaction, false otherwise
     */
    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        if (!isCellInteraction) {
            player.openDialog(new Dialog("garry_fight"));
            this.garry = garry;
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
        player.openDialog(new Dialog("garry_fight_npc"));
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
            player.openDialog(new Dialog("garry_fight_npc"));
        }
    }

    /**
     * Handle the interaction with the ProfOak during the event
     * Show a dialog when the player interact with the ProfOak
     *
     * @param profOak (ProfOak): the profOak who interact in the event
     * @param isCellInteraction (boolean): true if the interaction is a cell interaction, false otherwise
     */
    @Override
    public void interactWith(ProfOak profOak, boolean isCellInteraction) {
        if (!isCellInteraction) {
            player.openDialog(new Dialog("garry_fight_npc"));
        }
    }
}
