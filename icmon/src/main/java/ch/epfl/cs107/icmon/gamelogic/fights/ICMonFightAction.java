package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public interface ICMonFightAction {

    String name();

    default boolean doAction(Pokemon target) {
        return target != null && target.isAlive();
    }

    default void makeAction(Pokemon attacker, Pokemon target) {}
}
