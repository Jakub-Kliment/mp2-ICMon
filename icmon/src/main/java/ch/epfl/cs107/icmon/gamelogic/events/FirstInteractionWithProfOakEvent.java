package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.gamelogic.actions.AddPokemonAction;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.math.Orientation;

public class FirstInteractionWithProfOakEvent extends ICMonEvent{

    public FirstInteractionWithProfOakEvent(ICMonPlayer player) {
        super(player);
        //onComplete(new OpenDialogAction(player, "first_interaction_with_prof_oak"));
    }

    @Override
    public void update(float deltaTime) {
    }
    @Override
    public void interactWith(ProfOak profOak, boolean isCellInteraction) {
        if (!isCellInteraction){
            onComplete(new AddPokemonAction(player, new Latios(player.getCurrentArea(), Orientation.DOWN, player.getCurrentCells().get(0))));
            player.openDialog(new Dialog("first_interaction_with_prof_oak"));
            complete();
        }
    }

    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction){
            player.openDialog(new Dialog("first_interaction_with_oak_event_icshopassistant"));
        }
    }
}
