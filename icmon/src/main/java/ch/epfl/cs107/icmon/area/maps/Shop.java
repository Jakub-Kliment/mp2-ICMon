package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Shop extends ICMonArea {
    @Override
    public String getTitle() {
        return "shop";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground((this)));
        registerActor(new Door(this, new DiscreteCoordinates(3,1), "town", new DiscreteCoordinates(25, 19), new DiscreteCoordinates(4, 1)));
        registerActor(new ICShopAssistant(this, Orientation.DOWN, new DiscreteCoordinates(4,6)));

    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return null;
    }
}
