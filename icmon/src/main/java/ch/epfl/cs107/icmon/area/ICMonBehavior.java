package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.AreaGraph;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.AreaBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class ICMonBehavior extends AreaBehavior {
    private final AreaGraph areaGraph;

    /**
     * Default ICMonBehavior constructor
     *
     * @param window (Window): Graphic context, not null
     * @param name (String): Name of the behavior image, not null
     */
    public ICMonBehavior(Window window, String name) {
        super(window, name);
        areaGraph = new AreaGraph();
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                areaGraph.addNode(new DiscreteCoordinates(x,y), x>0, y<height-1, x<width-1, y>0);
                ICMonCellType color = ICMonCellType.toType(getRGB(height - 1 - y, x));
                setCell(x, y, new ICMonCell(x, y, color));
            }
        }
    }

    /**
     * Enumerate the different walking type allowed on a cell
     */
    public enum AllowedWalkingType {
        NONE,
        SURF,
        FEET,
        ALL;
    }

    /**
     * Enumerate the different types of cells
     */
    public enum ICMonCellType {
        NULL(0, AllowedWalkingType.NONE),
        WALL(-16777216, AllowedWalkingType.NONE),
        BUILDING(-8750470, AllowedWalkingType.NONE),
        INTERACT(-256, AllowedWalkingType.ALL),
        DOOR(-195580, AllowedWalkingType.ALL),
        INDOOR_WALKABLE(-1, AllowedWalkingType.FEET),
        OUTDOOR_WALKABLE(-14112955, AllowedWalkingType.FEET),
        WATER(-16776961, AllowedWalkingType.SURF),
        GRASS(-16743680, AllowedWalkingType.FEET)
        ;
        /**
         * The walking type of the cell
         */
        private final AllowedWalkingType walkingType;

        /**
         * The type of the cell
         */
        private final int type;

        /**
         * Default ICMonCellType constructor
         *
         * @param type (int): The type of the cell
         * @param walkingType (AllowedWalkingType): The walking type of the cell
         */
        ICMonCellType(int type, AllowedWalkingType walkingType) {
            this.type = type;
            this.walkingType = walkingType;
        }

        /**
         * Associates type to cell
         *
         * @param type (int): The type of the cell
         */
        public static ICMonCellType toType(int type) {
            for (ICMonCellType ict : ICMonCellType.values()) {
                if (ict.type == type)
                    return ict;
            }
            return NULL;
        }
    }

    public AreaGraph getAreaGraph() {
        return areaGraph;
    }

    /**
     * Cell of the ICMonBehavior
     */
    public class ICMonCell extends Cell {

        /** The type of the cell */
        private final ICMonCellType type;

        /**
         * True if the cell is occupied
         */
        private boolean taken;

        /**
         * Default ICMonCell constructor
         *
         * @param x (int): The x coordinate of the cell
         * @param y (int): The y coordinate of the cell
         * @param type (ICMonCellType): The type of the cell
         */
        public ICMonCell(int x, int y, ICMonCellType type) {
            super(x, y);
            this.type = type;
        }

        /**
         * Allowed the cell to be cell interacted  with
         *
         * @return (boolean) : True if the cell can be cell interacted with
         */
        @Override
        public boolean isCellInteractable() {
            return true;
        }

/**
         * Allowed the cell to be view interacted with
         *
         * @return (boolean) : True if the cell can be view interacted with
         */
        @Override
        public boolean isViewInteractable() {
            return false;
        }

        /**
         * Delegate interactions to the interaction handler
         *
         * @param v (AreaInteractionVisitor) : the interactor that wants to interact with this interactable
         * @param isCellInteraction : true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
         */
        @Override
        public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
            ((ICMonInteractionVisitor)v).interactWith(this, isCellInteraction);
        }

        /**
         * Liberate the cell if the actor was taking it
         *
         * @param entity (Interactable) : The entity that wants to leave the cell
         * @return (boolean) : True if the cell can be left
         */
        @Override
        protected boolean canLeave(Interactable entity) {
            if (entity != null) {
                if (entity.takeCellSpace()) {
                    taken = false;
                }
                return true;
            } else {
                return false;
            }
        }

        /**
         * Looks whether the player can enter onto a cell
         *
         *
         * @param entity (Interactable) : The entity that wants to enter the cell
         * @return (boolean) : True if entity can enter the cell
         */
        @Override
        protected boolean canEnter(Interactable entity) {
            if ((type.walkingType == AllowedWalkingType.NONE) || taken || (entity == null)) {
                return false;
            } else {
                if (entity.takeCellSpace()) {
                    taken = true;
                }
                return true;
            }
        }

        /**
         * Getter for the walking type of the cell
         *
         * @return (AllowedWalkingType) : The walking type of the cell
         */
        public AllowedWalkingType getWalkingType() {
            return type.walkingType;
        }
    }
}
