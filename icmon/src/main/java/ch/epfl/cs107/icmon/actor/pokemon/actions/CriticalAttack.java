package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

import java.util.Random;

public class CriticalAttack implements ICMonFightAction {

    @Override
    public String name() {
        return "CriticalAttack";
    }
    @Override
    public void makeAction(Pokemon attacker, Pokemon target) {
        if (doAction(target)) {
            target.receiveAttack(attacker.properties().damage() * 2);
        }
        if (doAction(attacker)) {
            Random rand = new Random();
            if (rand.nextBoolean()) {
                attacker.receiveAttack(target.properties().damage());
            }
        }
    }
}
