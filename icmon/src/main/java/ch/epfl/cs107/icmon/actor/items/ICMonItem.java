package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.play.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

abstract public class ICMonItem extends CollectableAreaEntity {

    /**The sprite of the item */
    private final Sprite sprite;

    /**
     * Default ICMonItem constructor
     *
     * @param area       (Area): Owner area. Not null
     * @param position   (DiscreteCoordinates): Initial position of the entity. Not null
     * @param spriteName (String) : Name of the sprite. Not null
     */
    public ICMonItem(Area area, DiscreteCoordinates position, String spriteName) {
        super(area, Orientation.DOWN, position);
        sprite = new RPGSprite(spriteName, 1f, 1f, this);

    }

    /**
     * Getter for the current cells of the entity
     *
     * @return (List<DiscreteCoordinates>) : List of the coordinates of the cells occupied by the entity
     */
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**@return (boolean) : indicates whether the Interactable takes space on current cell*/
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    /**@return (boolean): true, so this is able to have cell interactions*/
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * Draw the item
     *
     * @param canvas (Canvas) : The canvas on which the item is drawn
     */
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
}