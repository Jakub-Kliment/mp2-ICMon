package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Kitchen extends ICMonArea {

    @Override
    public String getTitle() {
        return "house_left_kitchen";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Door(this, new DiscreteCoordinates(11,1), "town", new DiscreteCoordinates(13, 11), new DiscreteCoordinates(10,1)));
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(4, 5);
    }
}
