package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;

public class ICMonFight extends PauseMenu {
    private float compteur = 5;
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        compteur-=deltaTime;
    }

    @Override
    public void drawMenu(Canvas c) {}
    public boolean isRunning(){
        return compteur>0;
    }
}
