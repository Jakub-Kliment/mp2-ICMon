package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Door extends AreaEntity {

    /** List of coordinates occupied by the door */
    private final List<DiscreteCoordinates> occupiedCoordinates;

    /** Coordinates of the area to which the door leads */
    private final DiscreteCoordinates coordinates;

    /** Name of the area to which the door leads */
    private final String areaName;

    /**
     * Default Door constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (Coordinate): Position of the door. Not null
     * @param areaName    (String) : Name of the area to which the door leads. Not null
     * @param coordinates (DiscreteCoordinates) : Coordinates in the area to which the door leads. Not null
     */
    public Door(Area area, DiscreteCoordinates position, String areaName, DiscreteCoordinates coordinates) {
        super(area, Orientation.UP, position);
        this.coordinates = coordinates;
        this.areaName = areaName;

        occupiedCoordinates = new ArrayList<>();
        occupiedCoordinates.add(position);
    }

    /**
     * Default Door constructor
     *
     * @param area                (Area): Owner area. Not null
     * @param position            (Coordinate): Position of the door. Not null
     * @param areaName            (String) : Name of the area to which the door leads. Not null
     * @param coordinates         (DiscreteCoordinates) : Coordinates in the area to which the door leads. Not null
     * @param occupiedCoordinates (DiscreteCoordinates...) : Coordinates occupied by the door. Not null
     */
    public Door(Area area, DiscreteCoordinates position, String areaName, DiscreteCoordinates coordinates, DiscreteCoordinates... occupiedCoordinates){
        this(area, position, areaName, coordinates);
        Collections.addAll(this.occupiedCoordinates, occupiedCoordinates);
    }

    /**
     * Getter for the coordinates occupied by the door
     *
     * @return (List<DiscreteCoordinates>) : List of coordinates occupied by the door
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return occupiedCoordinates;
    }

    /**@return (boolean) : false, so the door does not take space on current cell*/
    @Override
    public boolean takeCellSpace() {
        return false;
    }

    /**@return (boolean): true, so this is able to have cell interactions*/
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**@return (boolean): false, so it is not able to have interactions on distance*/
    @Override
    public boolean isViewInteractable() { return false; }

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

    /**
     * This function is empty because the door is already drawn within the map
     *
     * @param canvas (Canvas): Canvas onto which the ICMonActor is drawn. Not null
     */
    @Override
    public void draw(Canvas canvas) {}

    /**
     * Getter for the name of the area to which the door leads
     *
     * @return (String) : Name of the area to which the door leads
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * Getter for the coordinates in the area to which the door leads
     *
     * @return (DiscreteCoordinates) : Coordinates in the area to which the door leads
     */
    public DiscreteCoordinates getCoordinates() {
        return coordinates;
    }
}
