package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;

public class CollectItemEvent extends ICMonEvent {

    /** The item which is to collect */
    private final ICMonItem item;

    /**
     * Default CollectItemEvent constructor
     *
     * @param item (ICMonItem): the item to collect
     * @param player (ICMonPlayer): the player who collects the item
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
}
