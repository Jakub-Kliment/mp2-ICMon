package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.areagame.AreaGraph;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Queue;

public class WalkingNPC extends ICMonActor{

    /** Duration of the animation of the sprite*/
    private final int ANIMATION_DURATION = 10;

    /** Duration of the movement of the sprite*/
    private final static int MOVE_DURATION = 16;

    /** Animation of the player */
    private final OrientedAnimation animation;

    private final DiscreteCoordinates[] coorList = {new DiscreteCoordinates(10, 12), new DiscreteCoordinates(15, 12), new DiscreteCoordinates(15, 20), new DiscreteCoordinates(15, 7), new DiscreteCoordinates(15, 12)};
    private int listIndex;
    private final AreaGraph areaGraph;
    private Queue<Orientation> actualQueue;

    /**
     * Default ICMonActor constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public WalkingNPC(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        areaGraph = ((ICMonArea)area).getAreaGraph();
        animation = new OrientedAnimation("actors/PNJ", ANIMATION_DURATION  / 2, orientation, this);
        listIndex = 0;
        actualQueue = areaGraph.shortestPath(coorList[listIndex], coorList[(listIndex+1)%5]);
    }

    @Override
    public void update(float deltaTime) {
        if (actualQueue.isEmpty()){
            listIndex = (listIndex+1)%5;
            actualQueue = areaGraph.shortestPath(coorList[listIndex], coorList[(listIndex+1)%5]);
        }
        if (!isDisplacementOccurs()) {
            Orientation nextOrientation = actualQueue.poll();
            animation.orientate(nextOrientation);
            orientate(nextOrientation);
            move(MOVE_DURATION);
        }
        if (isDisplacementOccurs()) {
            animation.update(deltaTime);
        } else {
            animation.reset();
            resetMotion();
        }
        super.update(deltaTime);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {}

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        animation.draw(canvas);
    }
}
