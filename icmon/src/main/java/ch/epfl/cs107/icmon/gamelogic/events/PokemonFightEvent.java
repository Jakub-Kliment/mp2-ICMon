package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

public class PokemonFightEvent extends ICMonEvent {
    private ICMonFight menu;
    public PokemonFightEvent(ICMonPlayer player) {
        super(player);
        menu = new ICMonFight();
        onComplete(new LeaveAreaAction(player));
    }

    @Override
    public void update(float deltaTime) {}
}
