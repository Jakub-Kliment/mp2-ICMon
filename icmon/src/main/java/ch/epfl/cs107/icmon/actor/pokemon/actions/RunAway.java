package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

public class RunAway implements ICMonFightAction {

    /**@return (String) : The name of the action */
    @Override
    public String name() {
        return "Run Away";
    }
}
