package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class CollectItemEvent extends ICMonEvent {
    private final ICMonItem item;
    public CollectItemEvent(ICMonItem item, ICMonPlayer player) {
        super(player);
        this.item = item;
    }

    @Override
    public void update(float deltaTime) {
        if(item.isCollected()) {
            complete();
        }
    }

    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction) {
            player.openDialog(new Dialog("collect_item_event_interaction_with_icshopassistant"));
        }
    }
}
