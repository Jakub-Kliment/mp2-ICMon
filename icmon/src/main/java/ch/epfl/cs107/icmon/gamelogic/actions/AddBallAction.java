package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class AddBallAction implements Action{

    /** The player to add the ball to */
    private final ICMonPlayer player;


    /**
     * Default AddBallAction constructor
     *
     * @param player (ICMonPlayer): the player to add the pokemon to
     */
    public AddBallAction(ICMonPlayer player){
        this.player = player;
    }

    /**
     * Perform the action, add the pokemon to the player pokemon list
     */
    @Override
    public void perform() {
        player.addBall();
    }
}
