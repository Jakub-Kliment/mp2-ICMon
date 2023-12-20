package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class CollectItemEvent extends ICMonEvent {

    /** The item which is collect */
    private final ICMonItem item;

    /**
     * Default CollectItemEvent constructor
     *
     * @param item (ICMonItem): the item to collect
     * @param player (ICMonPlayer): the player who collect the item
     */
    public CollectItemEvent(ICMonItem item, ICMonPlayer player) {
        super(player);
        this.item = item;
    }

    /**
     * Complete the event when the item is collected
     */
    @Override
    public void update(float deltaTime) {
        if(item.isCollected()) {
            complete();
        }
    }

    /**
     * Handle the interaction with the ICShopAssistant during the event
     * Show a dialog when the player interact with the ICShopAssistant
     */
    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction) {
            player.openDialog(new Dialog("collect_item_event_interaction_with_icshopassistant"));
        }
    }
}
