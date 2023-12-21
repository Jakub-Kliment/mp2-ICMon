package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class ICShopAssistant extends NPCActor {

    /**
     * Default ICShopAssistant constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Orientation of this Actor
     * @param position    (DiscreteCoordinates): Initial position of the entity. Not null
     */
    public ICShopAssistant(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "actors/assistant");
    }

    /**
     * Delegate interactions to the interaction handler
     *
     * @param v                 (AreaInteractionVisitor) : the interactor that wants to interact with this interactable
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}
