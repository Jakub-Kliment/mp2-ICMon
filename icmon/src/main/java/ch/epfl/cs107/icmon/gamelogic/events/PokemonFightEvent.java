package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

public class PokemonFightEvent extends ICMonEvent {
    private ICMonFight menu;
    public PokemonFightEvent(ICMonPlayer player, ICMonActor actor) {
        super(player);
        menu = new ICMonFight();
        onComplete(new LeaveAreaAction(actor));
    }
//ATTENTION GETTER
    public ICMonFight getMenu() {
        return menu;
    }

    @Override
    public void update(float deltaTime) {
        if(!menu.isRunning()){
            complete();
        }
    }

    @Override
    public boolean isMenuPause() {
        return true;
    }
}
