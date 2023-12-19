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
    private final Pokemon player;
    private final Pokemon opponent;
    private final ICMonFightArenaGraphics arena;
    private ICMonFightActionSelectionGraphics selectionGraphics;
    private ICMonFightAction fightAction;
    private String message;
    private boolean winner;
    private State state;
    private boolean over;
    public ICMonFight(Pokemon player, Pokemon opponent){
        this.player = player;
        this.opponent = opponent;

        arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR, player.properties(), opponent.properties());
        state = State.INTRODUCTION;
        over = false;
        winner = true;
    }

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

    private void setGraphic(String message) {
        arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, message));
    }

    @Override
    public void drawMenu(Canvas c) {
        arena.draw(c);
    }

    public boolean isOver(){
        return over;
    }

    public void end() {
        over = true;
    }

    public boolean playerWin(){
        return winner;
    }

    private enum State {
        INTRODUCTION,
        COUNTER,
        CONCLUSION,
        ACTION_SELECTION,
        ACTION_EXECUTION
        ;
    }
}
