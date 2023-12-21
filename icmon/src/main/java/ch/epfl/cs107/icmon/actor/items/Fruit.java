package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Fruit extends ICMonItem {

    /**
     * Default ICMonItem constructor
     *
     * @param area       (Area): Owner area. Not null
     * @param position   (Coordinate): Initial position of the entity. Not null
     */
    public Fruit(Area area, DiscreteCoordinates position) {
        super(area, position, "items/fruit");
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}
