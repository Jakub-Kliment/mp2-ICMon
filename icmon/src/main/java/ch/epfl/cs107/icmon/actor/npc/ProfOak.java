package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class ProfOak extends NPCActor {

    /**
     * Default ICShopAssistant constructor`
     * The position is fixed because there is only one ProfOak in the game
     *
     * @param area (Area): Owner area. Not null
     */
    public ProfOak(Area area) {
        super(area, Orientation.DOWN, new DiscreteCoordinates(7,7), "actors/prof-oak");
    }

    /**
     * Delegate interactions to the interaction handler
     *
     * @param v (AreaInteractionVisitor) : the interactor that wants to interact with this interactable
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}
