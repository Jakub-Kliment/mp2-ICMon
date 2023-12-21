package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.Fruit;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.*;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.icmon.gamelogic.events.*;
import ch.epfl.cs107.icmon.message.GamePlayMessage;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ICMon extends AreaGame {

    /** The scale factor of the camera */
    public final static float CAMERA_SCALE_FACTOR = 13.f;

    /** The player of the game */
    private ICMonPlayer player;

    /** The list of the current event of ICMon */
    private List<ICMonEvent> eventList;

    /** The list of the event that are start */
    private List<ICMonEvent> startedEvent;

    /** The list of the event that are completed */
    private List<ICMonEvent> completedEvent;

    /** The game state of ICMon*/
    private ICMonGameState gameState;

    /** The list of the area of ICMon */
    private List<Area> areaList;
    private boolean hasFruitStarted = false;

    /**
     * Create the area of the game
     * Add the different area to ICMon
     */
    private void createAreas() {
        areaList = new ArrayList<>();

        addAreaToGame(new Town());
        addAreaToGame(new Lab());
        addAreaToGame(new Arena());
        addAreaToGame(new House());
        addAreaToGame(new Shop());
        addAreaToGame(new Kitchen());
    }

    /**
     * Create the game by lunching the different element of the game such as
     * the arena
     * the player
     * the event
     *
     * @param window (Window): the window to display
     * @param fileSystem (FileSystem): the file system to use
     * @return (boolean): true if the game is initialize, false otherwise
     */
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            initArea("house");
            event();
            return true;
        }
        return false;
    }

    /**
     * Initialize the area of the game and create the player
     *
     * @param areaKey (String): the name of the area to initialize
     */
    private void initArea(String areaKey) {
        ICMonArea area = (ICMonArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();

        gameState = new ICMonGameState();
        player = new ICMonPlayer(area, coords, "actors/player", gameState, new ICMonEventManager());

        eventList = new ArrayList<>();
        startedEvent = new ArrayList<>();
        completedEvent = new ArrayList<>();

        player.enterArea(area, coords);
        player.centerCamera();
    }

    /**
     * Update the game
     * Check the message and update the event list
     *
     * @param deltaTime elapsed time since last update, in seconds, non-negative!!!!!!!!!!!!!!!
     */
    public void update(float deltaTime) {
        super.update(deltaTime);

        Keyboard keyboard = getCurrentArea().getKeyboard();

        if (keyboard.get(Keyboard.R).isPressed()) {
            hasFruitStarted = false;
            begin(getWindow(), getFileSystem());
        }

        if (gameState.message != null) {
            gameState.message.process();
            gameState.message = null;
        }

        eventList.addAll(startedEvent);
        startedEvent.clear();

        for (ICMonEvent event : completedEvent) {
            eventList.remove(event);
        }
        completedEvent.clear();

        for (ICMonEvent event : eventList) {
            event.update(deltaTime);
        }

        if (areaList.get(0).isStarted()) {
            randomEvent();
            if (!hasFruitStarted) {
                fruitEvent();
                hasFruitStarted = true;
            }
        }
    }
    /**
     * Getter for the title of the game
     *
     * @return (String): the title of the game
     */
    public String getTitle() {
        return "ICMon";
    }

    /**
     * End the game
     */
    public void end() {}

    /**
     * Create events and the whole plot of the game
     */
    private void event() {
        ICBall ball = new ICBall(areaList.get(0), new DiscreteCoordinates(6,6));
        CollectItemEvent collectBall = new CollectItemEvent(ball, player);
        collectBall.onStart(new RegisterinAreaAction(areaList.get(0), ball));

        FirstInteractionWithProfOakEvent oakEvent = new FirstInteractionWithProfOakEvent(player);

        ICMonChainedEvent icMonChainedEvent = new ICMonChainedEvent(
                player,
                new IntroductionEvent(player),
                oakEvent,
                collectBall,
                new FirstInteractionWithGarryEvent(player),
                new EndOfTheGameEvent(player));

        icMonChainedEvent.start();
    }
    // A enlever (bug quand les coordonees sont not canEnter)!!!!!!!!!!!
    private void randomEvent() {
        Random rand = new Random();
        int upperbound = 500;
        int random_number = rand.nextInt(upperbound);

        if (random_number == 0) {
            System.out.println(random_number);
            int randomCoordinateX = rand.nextInt(31);
            int randomCoordinateY = rand.nextInt(33);

            ICBall randomBall = new ICBall(areaList.get(0), new DiscreteCoordinates(randomCoordinateX, randomCoordinateY));
            CollectItemEvent randomEvent = new CollectItemEvent(randomBall, player);
            randomEvent.onStart(new RegisterinAreaAction(areaList.get(0), randomBall));
            randomEvent.start();
        }

    }
    private void fruitEvent() {
        if (areaList.get(0).isStarted()) {
            Fruit fruit = new Fruit(areaList.get(0), new DiscreteCoordinates(7, 23));
            CollectItemEvent heal = new CollectItemEvent(fruit, player);
            heal.onStart(new RegisterinAreaAction(areaList.get(0), fruit));
            heal.start();
        }
    }
    /**
     * Add an area to the game and the list of the different area
     *
     * @param area (Area): the area to add
     */
    private void addAreaToGame(Area area) {
        addArea(area);
        areaList.add(area);
    }

    /**
     * Game state of ICMon
     */
    public class ICMonGameState {
        /** Message send by the different part of the game to treat */
        private GamePlayMessage message = null;

        /**
         * Default ICMonGameState constructor
         */
        private ICMonGameState() {}

        /**
         * Delegate the interaction between interactor and interactable to the different current event
         *
         * @param interactable (Interactable): the interactable who interact
         * @param isCellInteraction (boolean): true if the interaction is a cell interaction, false otherwise
         */
        public void acceptInteraction(Interactable interactable , boolean isCellInteraction) {
            for (var event : ICMon.this.eventList) {
                interactable.acceptInteraction(event, isCellInteraction);
            }
        }

        /**
         * Allowed to send a message to the game
         *
         * @param message (GamePlayMessage): the message to send
         */
        public void send(GamePlayMessage message) {
            this.message = message;
            if (message instanceof SuspendWithEventMessage) {
                treatMessage(message);
            }
        }

        /**
         * Treat the message received
         *
         * @param message (GamePlayMessage): the message to treat
         */
        public void treatMessage(GamePlayMessage message) {
            ICMonEvent event = ((SuspendWithEventMessage)message).getEvent();
            if (event.isMenuPause()) {
                event.onStart(new SetPauseAction(this, (event).getMenu()));
                event.onComplete(new ResumeMenu(this));

                for(ICMonEvent anEvent : eventList) {
                    event.onStart(new SuspendedEventAction(anEvent));
                    event.onComplete(new ResumeEventAction(anEvent));
                }
                event.start();
            }
        }

        /**
         * Switch the current area of the game
         *
         * @param areaName (String): the name of the area to switch
         * @param coordinates (DiscreteCoordinates): the coordinates of the player in the new area
         */
        public void switchArea(String areaName, DiscreteCoordinates coordinates) {
            player.leaveArea();
            ICMonArea currentArea = (ICMonArea) setCurrentArea(areaName, false);
            player.enterArea(currentArea, coordinates);
        }

        /**
         * Set the pause menu of the game and start the pause
         *
         * @param menu (PauseMenu): the pause menu to set
         */
        public void setMenuPause(PauseMenu menu) {
            setPauseMenu(menu);
            requestPause();
        }

        /**
         * Resume the game
         */
        public void stopPauseMenu() {
            requestResume();
        }
    }

    /**
     * Event manager of ICMon
     */
    public class ICMonEventManager{

        /**
         * Add an event to the list of the event that are start
         *
         * @param event (ICMonEvent): the event to add
         */
        public void addStartedEvent(ICMonEvent event){
            startedEvent.add(event);
        }

        /**
         * Add an event to the list of the event that are completed
         *
         * @param event (ICMonEvent): the event to add
         */
        public void addCompletedEvent(ICMonEvent event){
            completedEvent.add(event);
        }
    }
}