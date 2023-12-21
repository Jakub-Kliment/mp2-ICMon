package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class ICBall extends ICMonItem {
    /**
     * Default ICBall constructor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinates): Initial position of the entity. Not null
     */
    public ICBall(Area area, DiscreteCoordinates position) {
        super(area, position, "items/icball");
    }

    /**@return (boolean): true, ball is able to have view interactions*/
    @Override
    public boolean isViewInteractable() {
        return true;
    }

    /**
     * Delegate interactions to the interaction handler
     *
     * @param v                 (AreaInteractionVisitor): the interactor that wants to interact with this interactable
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}
