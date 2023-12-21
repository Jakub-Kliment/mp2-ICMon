package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

public class Attack implements ICMonFightAction {

    /**@return (String) : The name of the action*/
    @Override
    public String name() {
        return "Attack";
    }

    /**
     * Assures the attack action
     *
     * @param attacker (Pokemon): The pokemon that attacks
     * @param target   (Pokemon): The pokemon that is attacked
     */
    @Override
    public void makeAction(Pokemon attacker, Pokemon target) {
        if (doAction(target)) {
            target.receiveAttack(attacker.properties().damage());
        }
    }
}
