package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

public class RunAway implements ICMonFightAction {
    /**
     * The name of the action
     *
     * @return (String) : The name of the action
     */
    @Override
    public String name() {
        return "Run Away";
    }
}
