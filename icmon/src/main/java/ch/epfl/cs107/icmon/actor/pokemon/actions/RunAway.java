package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

public class RunAway implements ICMonFightAction {
    @Override
    public String name() {
        return "Run Away";
    }
}
