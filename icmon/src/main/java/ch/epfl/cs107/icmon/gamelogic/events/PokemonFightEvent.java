package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

public class PokemonFightEvent extends ICMonEvent {

    /** The opponent of the player */
    private final ICMonFightableActor opponent;

    /** The fight menu */
    private final ICMonFight menu;

    /**
     * Default PokemonFightEvent constructor
     * Create a fight menu with the player pokemon and the opponent pokemon
     *
     * @param player (ICMonPlayer): the player who interact in the event
     * @param playerPokemon (Pokemon): the pokemon of the player
     * @param opponent (ICMonFightableActor): the opponent of the player
     */
    public PokemonFightEvent(ICMonPlayer player, Pokemon playerPokemon, ICMonFightableActor opponent) {
        super(player);
        menu = new ICMonFight(playerPokemon, opponent.choosenPokemon());
        this.opponent = opponent;
    }

    /**
     * Getter for the fight menu
     *
     * @return (ICMonFight): the fight menu
     */
    public ICMonFight getMenu() {
        return menu;
    }

    /**
     * Update the event
     * Complete the event when the fight is over and retire the opponent if the player win
     *
     */
    @Override
    public void update(float deltaTime) {
        if(menu.isOver()){
            if(menu.playerWin()){
                onComplete(new LeaveAreaAction((ICMonActor)opponent));
            }
            complete();
        }
    }

    /**
     * Getter to know if the event pause the game
     */
    @Override
    public boolean isMenuPause() {
        return true;
    }
}
