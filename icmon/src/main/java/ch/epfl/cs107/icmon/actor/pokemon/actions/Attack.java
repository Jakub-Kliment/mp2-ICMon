package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

public class Attack implements ICMonFightAction {
    @Override
    public String name() {
        return "Attack";
    }

    @Override
    public void makeAction(Pokemon attacker, Pokemon target) {
        if (doAction(target)) {
            target.receiveAttack(attacker.properties().damage());
        }
    }
}
