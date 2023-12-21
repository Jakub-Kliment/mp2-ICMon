package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.actor.npc.NPCActor;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

public class Desk extends ICMonActor {

    /** NPCActor that interacts with this desk */
    private final NPCActor actor;

    /**
     * Default ICMonActor constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (DiscreteCoordinates): Initial position of the entity. Not null
     */
    public Desk(Area area, Orientation orientation, DiscreteCoordinates position, NPCActor actor) {
        super(area, orientation, position);
        this.actor = actor;
    }

    /**
     * Accepts interactions with another interactor
     *
     * @param v (AreaInteractionVisitor) : the interactor that wants to interact with this interactable
     * @param isCellInteraction : true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    /**@return (boolean): true, so this is able to have interactions on distance*/
    @Override
    public boolean isViewInteractable() {
        return true;
    }

    /**@return (boolean): false, so this is not able to have cell interactions*/
    @Override
    public boolean isCellInteractable() {
        return false;
    }

    /**
     * This function is empty because the desk is already drawn within the map
     *
     * @param canvas (Canvas): Canvas onto which the ICMonActor is drawn. Not null
     */
    @Override
    public void draw(Canvas canvas) {}

    /**
     * Getter for NPCActor
     *
     * @return NPCActor
     */
    public NPCActor getActor() {
        return actor;
    }
}
