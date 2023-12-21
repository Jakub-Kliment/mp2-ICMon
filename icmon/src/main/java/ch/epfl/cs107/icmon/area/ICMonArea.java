package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.areagame.AreaGraph;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

abstract public class ICMonArea extends Area {

    /**
     * Creates the area
     */
    protected abstract void createArea();

    /**
     * Getter for the spawn position of the player
     * @return (DiscreteCoordinates): the spawn position of the player
     */
    public abstract DiscreteCoordinates getPlayerSpawnPosition();

    private AreaGraph areaGraph;

    /**
     * Link the area to the window and the file system
     * Create the behavior linked to the area
     */
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            ICMonBehavior behavior = new ICMonBehavior(window, getTitle());
            areaGraph = behavior.getAreaGraph();
            setBehavior(behavior);
            createArea();
            return true;
        }
        return false;
    }

    /**
     * Getter for the camera scale factor
     *
     * @return (float): the camera scale factor
     */
    @Override
    public final float getCameraScaleFactor() {
        return ICMon.CAMERA_SCALE_FACTOR;
    }

    public AreaGraph getAreaGraph(){
        return areaGraph;
    }
}
