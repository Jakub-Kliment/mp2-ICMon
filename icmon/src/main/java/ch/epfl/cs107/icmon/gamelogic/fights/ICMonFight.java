package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class ICMonFight extends PauseMenu {
    private Pokemon player;
    private Pokemon opponent;
    private State state;
    private boolean over;
    private ICMonFightArenaGraphics arena;
    public ICMonFight(Pokemon player, Pokemon opponent){
        this.player = player;
        this.opponent = opponent;
        arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR, player.properties(), opponent.properties());
        state = State.INTRODUCTION;
        over = false;
    }
    private float compteur = 5;
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getKeyboard();
        switch (state) {
            case INTRODUCTION -> {
                arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR , "Welcome to the arena"));
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    state = State.COUNTER;
                }
            }
            case COUNTER -> {
                arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR , "ma bite"));
                compteur -= deltaTime;
                System.out.println(compteur);
                if (compteur<0) {
                    state = State.CONCLUSION;
                }
            }
            case CONCLUSION -> {
                arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR , "Good fight"));
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    end();
                }
            }

        }
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
    private enum State {
        INTRODUCTION,
        COUNTER,
        CONCLUSION
        ;
    }

}
