package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;

public class ICMonFight extends PauseMenu {
    private Pokemon player;
    private Pokemon opponent;
    private ICMonFightArenaGraphics arena;
    public ICMonFight(Pokemon player, Pokemon opponent){
        this.player = player;
        this.opponent = opponent;
        arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR, player.properties(), opponent.properties());
    }
    private float compteur = 5;
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        compteur-=deltaTime;
    }

    @Override
    public void drawMenu(Canvas c) {
        arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR , "hello world"));
    }
    public boolean isRunning(){
        return compteur>0;
    }
}
