package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;

public class EndOfTheGameEvent extends ICMonEvent{

    public EndOfTheGameEvent(ICMonPlayer player) {
        super(player);
    }
    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction){
            System.out.println("I heard that you were able to implement this step successfully. Congrats !");
        }
    }
}
