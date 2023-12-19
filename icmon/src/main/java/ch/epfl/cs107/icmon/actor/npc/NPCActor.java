package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

public class NPCActor extends ICMonActor {
    private final Sprite sprite;

    /**
     * Default NPCActor constructor
     * Create the sprite associated to the NPCActor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     */
    public NPCActor(Area area, Orientation orientation, DiscreteCoordinates position, String sprite_name) {
        super(area, orientation, position);
        sprite = new RPGSprite(sprite_name, 1, 1.3125f, this, new RegionOfInterest(0, 0, 16, 21));
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    /**
     * Getter for the current cells of the entity
     *
     * @return (List<DiscreteCoordinates>) : List of the coordinates of the cells occupied by the entity
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return super.getCurrentCells();
    }

    /**
     * Draw the NPCActor
     *
     * @param canvas (Canvas) : The canvas on which the NPCActor is drawn
     */
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
}
