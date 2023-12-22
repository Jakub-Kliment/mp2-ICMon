package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.WalkingNPC;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class CapturePokemon extends ICMonEvent{

    /** Boolean to know if it is the first update of the started event */
    private boolean firstUpdate;

    /** Number of pokemon before the event */
    private int nbPokemon;
    /**
     * Default ICMonEvent constructor
     * Add 2 actions to the event: one to register the event when it is started and one to unregister it when it is completed
     *
     * @param player (ICMonPlayer): the player of the game
     */
    public CapturePokemon(ICMonPlayer player) {
        super(player);
        nbPokemon = 0;
        firstUpdate = true;
    }

    /**
     * Update the event
     * Complete the event when the player capture a pokemon
     *
     * @param deltaTime (float): the time between two updates
     */
    @Override
    public void update(float deltaTime) {
        if (isStarted() && firstUpdate){
            firstUpdate = false;
            nbPokemon = player.getPokemonList().size();
        }
        if (nbPokemon < player.getPokemonList().size()) {
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
        player.openDialog(new Dialog("assistant_capture"));
    }

    /**
     * Handle the interaction with the Prof. Oak during the event
     * Show a dialog when the player interact with the Prof. Oak
     *
     * @param profOak (ProfOak): the profOak who interact in the event
     * @param isCellInteraction (boolean): true if the interaction is a cell interaction, false otherwise
     */
    @Override
    public void interactWith(ProfOak profOak, boolean isCellInteraction) {
        player.openDialog(new Dialog("oak_capture"));
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
        player.openDialog(new Dialog("npc_capture"));
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
