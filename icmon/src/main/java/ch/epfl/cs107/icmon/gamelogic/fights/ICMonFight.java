package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.actions.Attack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.RunAway;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class ICMonFight extends PauseMenu {

    /** Pokemon of the player */
    private final Pokemon player;

    /** Pokemon of the opponent */
    private final Pokemon opponent;

    /** The graphic arena of the fight */
    private final ICMonFightArenaGraphics arena;

    /** The selector for the pokemon */
    private ICMonFightActionSelectionGraphics selectionGraphics;

    /** Action performed by the player */
    //!!!!!!!!!!!!!!!!!!!!!!!!!
    private ICMonFightAction fightAction;

    /** Message to display */
    private String message;

    /** Boolean to know if the player has won */
    private boolean winner;

    /** State of the fight */
    private State state;

    /** Boolean to know if the fight is over */
    private boolean over;

    /**
     * Constructor for the ICMonFight
     * Create the arena with the pokemon of the player and the opponent
     *
     * @param player (Pokemon) : The pokemon of the player
     * @param opponent (Pokemon) : The pokemon of the opponent
     */
    public ICMonFight(Pokemon player, Pokemon opponent){
        this.player = player;
        this.opponent = opponent;

        arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR, player.properties(), opponent.properties());
        state = State.INTRODUCTION;
        over = false;
        winner = false;
    }

    /**
     * update the fight
     * Alternate between the different states of the fight depending on the action of the player
     *
     * @param deltaTime (float) : The time between two updates
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getKeyboard();

        switch (state) {
            case INTRODUCTION -> {
                setGraphic("Welcome to the arena");
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    state = State.ACTION_SELECTION;
                }
            }

            case ACTION_SELECTION -> {
                if (selectionGraphics == null) {
                    selectionGraphics = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, player.getFightActions());
                }

                arena.setInteractionGraphics(selectionGraphics);
                selectionGraphics.update(deltaTime);

                if (selectionGraphics.choice() != null) {
                    fightAction = selectionGraphics.choice();
                    state = State.ACTION_EXECUTION;
                    selectionGraphics = null;
                }
            }

            case ACTION_EXECUTION -> {
                if (fightAction instanceof Attack) {
                    fightAction.makeAction(player, opponent);

                    if (!opponent.isAlive()) {
                        message = "The player has won the fight";
                        winner = true;
                        state = State.CONCLUSION;
                    } else {
                        state = State.COUNTER;
                    }
                } else if (fightAction instanceof RunAway) {
                    message = "The player has left the fight";
                    winner = false;
                    state = State.CONCLUSION;
                }
            }

            case COUNTER -> {
                for (ICMonFightAction opponentAction : opponent.getFightActions()) {
                    if (opponentAction instanceof Attack) {
                        fightAction = opponentAction;
                        fightAction.makeAction(opponent, player);
                    }
                }
                if (!player.isAlive()) {
                    message = "The opponent has won the fight";
                    winner = false;
                    state = State.CONCLUSION;
                } else {
                    state = State.ACTION_SELECTION;
                }
            }

            case CONCLUSION -> {
                setGraphic(message);
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    end();
                }
            }

        }
    }

    /**
     * Set the graphic message of the arena
     *
     * @param message (String) : The message to display
     */
    private void setGraphic(String message) {
        arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, message));
    }

    /**
     * Draw the arena
     *
     * @param c (Canvas) : The canvas where the arena is drawn
     */
    @Override
    public void drawMenu(Canvas c) {
        arena.draw(c);
    }

    /**
     * Getter for the boolean over
     * Allow to know if the fight is over
     *
     * @return (boolean) : True if the fight is over, false otherwise
     */
    public boolean isOver(){
        return over;
    }

    /**
     * End the fight !!!!!!!!!!!!!!!!
     * Set the boolean over to true
     */
    public void end() {
        over = true;
    }

    /**
     * Getter for the boolean winner
     * Allow to know if the player has won the fight
     *
     * @return (boolean) : True if the player has won the fight, false otherwise
     */
    public boolean playerWin(){
        return winner;
    }

    /**
     * Enumerate the different states of the fight
     */
    private enum State {
        INTRODUCTION,
        COUNTER,
        CONCLUSION,
        ACTION_SELECTION,
        ACTION_EXECUTION;
    }
}
