package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public interface ICMonFightableActor {
    public Pokemon choosenPokemon();
    public boolean retire();
}
