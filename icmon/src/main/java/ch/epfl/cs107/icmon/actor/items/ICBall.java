package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.Collections;
import java.util.List;

public class ICBall extends ICMonItem{
    public ICBall(Area area, DiscreteCoordinates position) {
        super(area, position, "items/icball");
    }
    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {

    }
}
