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
    private String areaName;
    private DiscreteCoordinates coordinates;
    private List<DiscreteCoordinates> occupiedCoordinates;

    /**
     * Default AreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Door(Area area, DiscreteCoordinates position, String areaName, DiscreteCoordinates coordinates) {
        super(area, Orientation.UP, position);
        occupiedCoordinates = new ArrayList<>();
        this.areaName = areaName;
        this.coordinates = coordinates;
        occupiedCoordinates.add(position);
    }
    public Door(Area area, DiscreteCoordinates position, String areaName, DiscreteCoordinates coordinates, DiscreteCoordinates... occupiedCoordinates){
        super(area, Orientation.UP, position);
        this.occupiedCoordinates = new ArrayList<>();
        this.areaName = areaName;
        this.coordinates = coordinates;
        this.occupiedCoordinates.add(position);
        Collections.addAll(this.occupiedCoordinates, occupiedCoordinates);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return occupiedCoordinates;
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    @Override
    public void draw(Canvas canvas) {}

    public String getAreaName() {
        return areaName;
    }

    public DiscreteCoordinates getCoordinates() {
        return coordinates;
    }
}
