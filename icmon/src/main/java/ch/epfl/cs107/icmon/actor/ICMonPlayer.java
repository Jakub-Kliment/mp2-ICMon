package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class ICMonPlayer extends ICMonActor{
    private final int ANIMATION_DURATION = 8;
    private final static int MOVE_DURATION = 8;
    private OrientedAnimation animation;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public ICMonPlayer(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position);
        animation = new OrientedAnimation(spriteName, ANIMATION_DURATION/2, orientation, this);
    }
    public void update(float deltaTime) {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        //if(keyboard.get(Keyboard.DOWN).isDown()||keyboard.get(Keyboard.UP).isDown()||keyboard.get(Keyboard.LEFT).isDown()||keyboard.get(Keyboard.RIGHT).isDown()){}
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
}
