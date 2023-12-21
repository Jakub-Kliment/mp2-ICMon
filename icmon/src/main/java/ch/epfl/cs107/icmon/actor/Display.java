package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

public class Display extends ICMonActor {
    private final String textDialog;
    /**
     * Default ICMonActor constructor
     *
     * @param area      (Area): Owner area. Not null
     * @param position  (DiscreteCoordinates): Initial position of the entity. Not null
     */
    public Display(Area area, DiscreteCoordinates position, String textDialog) {
        super(area, Orientation.DOWN, position);
        this.textDialog = textDialog;
    }

    /**
     * Delegate interactions with the interactor
     *
     * @param v (AreaInteractionVisitor) : the interactor that wants to interact with this interactable
     * @param isCellInteraction : true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    /**@return (boolean) : true, so the display takes space on current cell*/
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    /**@return (boolean): false, so this is not able to have cell interactions*/
    @Override
    public boolean isCellInteractable() {
        return false;
    }

    /**@return (boolean): true, so it is able to have interactions on distance*/
    @Override
    public boolean isViewInteractable() {
        return true;
    }

    /**
     * This function is empty because the display is already drawn within the map
     *
     * @param canvas (Canvas): Canvas onto which the ICMonActor is drawn. Not null
     */
    @Override
    public void draw(Canvas canvas) {}

    /** Getter for dialog with the display */
    public Dialog getDialog() {
        return new Dialog(textDialog);
    }
}
