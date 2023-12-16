package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Arena extends ICMonArea {

    @Override
    public String getTitle() {
        return "arena";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground((this)));
        registerActor(new Bulbizarre(this, Orientation.DOWN, new DiscreteCoordinates(6, 6)));
        registerActor(new Door(this, new DiscreteCoordinates(4,1), "town", new DiscreteCoordinates(20, 15), new DiscreteCoordinates(5, 1)));
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return null;
    }
}
