package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

import java.util.Random;

public class CriticalAttack implements ICMonFightAction {

    /**@return (String) : The name of the action */
    @Override
    public String name() {
        return "CriticalAttack";
    }

    /**
     * Assures the critical attack action:
     * The attacker does twice the damage (of his attack) to the target
     * but has a random 50% chance of getting hit back by the target
     *
     * @param attacker (Pokemon): The pokemon that attacks
     * @param target   (Pokemon): The pokemon that is attacked
     */
    @Override
    public void makeAction(Pokemon attacker, Pokemon target) {
        if (doAction(target)) {
            target.receiveAttack(attacker.properties().damage() * 2);
        }
        // Random 50% chance of getting hit back
        if (doAction(attacker)) {
            Random rand = new Random();
            if (rand.nextBoolean()) {
                attacker.receiveAttack(target.properties().damage());
            }
        }
    }
}
