package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

public class PokemonFightEvent extends ICMonEvent {
    private ICMonFight menu;
    private ICMonFightableActor opponent;
    public PokemonFightEvent(ICMonPlayer player, Pokemon playerPokemon, ICMonFightableActor opponent) {
        super(player);
        menu = new ICMonFight(playerPokemon, opponent.choosenPokemon());
        this.opponent = opponent;
    }
//ATTENTION GETTER
    public ICMonFight getMenu() {
        return menu;
    }

    @Override
    public void update(float deltaTime) {
        if(menu.isOver()){
            if(menu.playerWin()){
                onComplete(new LeaveAreaAction((ICMonActor)opponent));
            }
            complete();
        }
    }

    @Override
    public boolean isMenuPause() {
        return true;
    }
}
