package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class ICMonPlayer extends ICMonActor implements Interactor {
    private final int ANIMATION_DURATION = 8;
    private final static int MOVE_DURATION = 8;
    private OrientedAnimation animation;
    private ICMonPlayerInteractionHandler handler;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public ICMonPlayer(Area area, DiscreteCoordinates position, String spriteName) {
        super(area, Orientation.DOWN, position);
        animation = new OrientedAnimation(spriteName, ANIMATION_DURATION/2, Orientation.DOWN, this);
        handler = new ICMonPlayerInteractionHandler();
    }
    public void update(float deltaTime) {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
        moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        if (isDisplacementOccurs()){
            animation.update(deltaTime);
        } else {
            animation.reset();
        }
        super.update(deltaTime);
    }
    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown()) {
            if (!isDisplacementOccurs()) {
                animation.orientate(orientation);
                orientate(orientation);
                move(MOVE_DURATION);
            }
        }
    }
    @Override
    public void draw(Canvas canvas) {
        animation.draw(canvas);
    }
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return super.getCurrentCells();
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        if (keyboard.get(Keyboard.L).isPressed()) {
            return true;
        }
        return false;
    }

    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor{
        @Override
        public void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {
            if(isCellInteraction){
                if (cell.getType().getWalkingType()== ICMonBehavior.AllowedWalkingType.FEET){
                    animation = new OrientedAnimation("actors/player", ANIMATION_DURATION/2, getOrientation(), ICMonPlayer.this);
                }
                if (cell.getType().getWalkingType()== ICMonBehavior.AllowedWalkingType.SURF){
                    animation = new OrientedAnimation("actors/player_water", ANIMATION_DURATION/2, getOrientation(), ICMonPlayer.this);
                }
            }
        }
    }
}
