package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

public class Attack implements ICMonFightAction {
    /**
     * The name of the action
     *
     * @return (String) : The name of the action
     */
    @Override
    public String name() {
        return "Attack";
    }

    /**
     * The target is attacked by the attacker
     *
     * @param attacker The pokemon that makes the action
     * @param target The pokemon that receives the action
     */
    @Override
    public void makeAction(Pokemon attacker, Pokemon target) {
        if (doAction(target)) {
            target.receiveAttack(attacker.properties().damage());
        }
    }
}
