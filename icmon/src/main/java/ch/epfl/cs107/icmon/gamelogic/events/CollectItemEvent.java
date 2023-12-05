package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;

public class CollectItemEvent extends ICMonEvent {
    private ICMonItem item;
    public CollectItemEvent(ICMonItem item, ICMonPlayer player) {
        super(player);
        this.item = item;
    }
    @Override
    public void update(float deltaTime) {
        if(item.isCollected()){
            complete();
        }
    }
    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction){
            System.out.println("This is an interaction between the player and ICShopAssistant based on events !");
        }
    }
}
