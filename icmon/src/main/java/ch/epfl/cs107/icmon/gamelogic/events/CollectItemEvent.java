package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class CollectItemEvent extends ICMonEvent {
    private ICMonItem item;
    public CollectItemEvent(ICMonItem item, ICMonPlayer player) {
        super(player);
        this.item = item;
        onStart(new RegisterinAreaAction(player.getCurrentArea(), item));
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
            player.openDialog(new Dialog("collect_item_event_interaction_with_icshopassistant"));
        }
    }
}
