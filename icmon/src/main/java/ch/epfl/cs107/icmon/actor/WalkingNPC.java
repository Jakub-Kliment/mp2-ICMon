package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.areagame.AreaGraph;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class WalkingNPC extends ICMonActor{

    /** Duration of the animation of the sprite*/
    private final int ANIMATION_DURATION = 10;

    /** Duration of the movement of the sprite*/
    private final static int MOVE_DURATION = 16;

    /** Animation of the player */
    private final OrientedAnimation animation;

    /** List of coordinates follow by the npc */
    private final DiscreteCoordinates[] coordinatesList;

    /** Stage of the npc deplacement */
    private int listIndex;

    /** Graph of the area */
    private final AreaGraph areaGraph;

    /** Queue of the orientation that the npc will take to follow the current path */
    private Queue<Orientation> actualQueue;

    /** Countdown before the next npc path */
    private double countdown;

    /** Boolean to know if the last move occurred */
    private boolean moveMade;

    /**
     * Default ICMonActor constructor
     *
     * @param area             (Area): Owner area. Not null
     * @param orientation      (Orientation): Initial orientation of the entity. Not null
     * @param coordinatesList  (DiscreteCoordinates[]): List of coordinates of the path to follow
     */
    public WalkingNPC(Area area, String nom, Orientation orientation, DiscreteCoordinates[] coordinatesList) {
        super(area, orientation, coordinatesList[0]);
        areaGraph = ((ICMonArea)area).getAreaGraph();
        animation = new OrientedAnimation(nom, ANIMATION_DURATION  / 2, orientation, this);
        this.coordinatesList = coordinatesList;
        listIndex = 0;
        countdown = 0;
        moveMade = true;
        actualQueue = areaGraph.shortestPath(coordinatesList[listIndex], coordinatesList[(listIndex + 1) % coordinatesList.length]);
    }

    @Override
    public void update(float deltaTime) {
        if (actualQueue.isEmpty()) {
            listIndex = (listIndex + 1) % coordinatesList.length;
            actualQueue = areaGraph.shortestPath(coordinatesList[listIndex], coordinatesList[(listIndex + 1) % coordinatesList.length]);
            countdown = 2;
        }

        if (!isDisplacementOccurs() && countdown < 0) {
            if (moveMade){
                Orientation nextOrientation = actualQueue.poll();
                animation.orientate(nextOrientation);
                orientate(nextOrientation);
            }
            moveMade = move(MOVE_DURATION);
        }
        if (isDisplacementOccurs()) {
            animation.update(deltaTime);
        } else {
            animation.reset();
            resetMotion();
        }
        countdown -= deltaTime;
        super.update(deltaTime);
    }

    /**
     * AcceptInteraction is void because the WalkingNPC does not accept interactions
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {}


    /**@return (boolean) : true, so the Interactable takes space on current cell */
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    /**@return (boolean): false, so it is not able to have cell interactions*/
    @Override
    public boolean isCellInteractable() {
        return false;
    }

    /**
     * Draws the WalkingNPC to the map
     *
     * @param canvas (Canvas): Canvas onto which the WalkingNPC is drawn. Not null
     */
    @Override
    public void draw(Canvas canvas) {
        animation.draw(canvas);
    }
}
