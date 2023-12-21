package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public interface ICMonFightableActor {
    /**
     * Return the Pokemon that will fight, allows polymorphism
     *
     * @return (Pokemon) : The Pokemon that is chosen to fight
     */
    Pokemon choosenPokemon();
}
