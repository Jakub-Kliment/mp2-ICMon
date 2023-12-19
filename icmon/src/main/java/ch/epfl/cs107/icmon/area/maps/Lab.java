package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Lab extends ICMonArea {

    @Override
    public String getTitle() {
        return "lab";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground((this)));
        registerActor(new ProfOak(this));
        registerActor(new Door(this, new DiscreteCoordinates(7,1), "town", new DiscreteCoordinates(15, 23), new DiscreteCoordinates(6, 1)));
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return null;
    }
}
