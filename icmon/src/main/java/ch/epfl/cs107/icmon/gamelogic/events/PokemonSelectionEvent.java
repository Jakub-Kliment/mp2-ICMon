package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFightAction;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.play.engine.PauseMenu;

public class PokemonSelectionEvent extends ICMonEvent{

    /** The selection menu */
    private final PokemonSelectionMenu selectionMenu;

    /** The opponent of the player */
    private final ICMonFightableActor opponent;

    /** The game state */
    private final ICMon.ICMonGameState gameState;

    /**
     * Default PokemonSelectionEvent constructor
     * Create a selection menu with the list of the player pokemon
     *
     * @param player (ICMonPlayer): the player who interact in the event
     * @param opponent (ICMonFightableActor): the opponent of the player
     * @param gameState (ICMon.ICMonGameState): the game state
     */
    public PokemonSelectionEvent(ICMonPlayer player, ICMonFightableActor opponent, ICMon.ICMonGameState gameState) {
        super(player);
        this.opponent = opponent;
        this.gameState = gameState;
        selectionMenu = new PokemonSelectionMenu(player.getPokemonList());
    }

    /**
     * Update the event
     * Complete the event when the player choose a pokemon or when he has no pokemon
     *
     */
    @Override
    public void update(float deltaTime) {
        if(selectionMenu.choice() != null) {
            onComplete(new AfterPokemonSelectionFightAction(player, selectionMenu.choice(), opponent, gameState));
            complete();
        }
        if (!selectionMenu.hasPokemon()) {
            complete();
        }
    }

    /**
     * Getter to know if the event pause the game
     *
     * @return (boolean): true if the event pause the game, false otherwise
     */
    @Override
    public boolean isMenuPause() {
        return true;
    }

    /**
     * Getter for the selection menu
     *
     * @return (PauseMenu): the selection menu
     */
    @Override
    public PauseMenu getMenu() {
        return selectionMenu;
    }
}
