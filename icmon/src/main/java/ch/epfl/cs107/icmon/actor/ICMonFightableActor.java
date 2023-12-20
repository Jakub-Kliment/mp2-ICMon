package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public interface ICMonFightableActor {
    /**
     * Return the Pokemon that will fight, allow polymorphism
     *
     * @return (Pokemon) : The Pokemon that is choosen to fight
     */
    Pokemon choosenPokemon();
}
