package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.Fruit;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
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
import ch.epfl.cs107.play.math.Orientation;
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

    /** The list of current events of ICMon */
    private List<ICMonEvent> eventList;

    /** The list of events that are start */
    private List<ICMonEvent> startedEvent;

    /** The list of events that are completed */
    private List<ICMonEvent> completedEvent;

    /** The game state of ICMon */
    private ICMonGameState gameState;

    /** The list of the areas of ICMon */
    private List<Area> areaList;
    private boolean hasFruitStarted = false;

    public final static Random rand = new Random();
    /**
     * Create the area of the game
     * Add the different areas to ICMon
     */
    private void createAreas() {
        areaList = new ArrayList<>();

        addAreaToGame(new Town());
        addAreaToGame(new Lab());
        addAreaToGame(new Arena());
        addAreaToGame(new House());
        addAreaToGame(new Shop());
        addAreaToGame(new HouseLeftKitchen());
        addAreaToGame(new HouseLeftRoom());
        addAreaToGame(new HouseRightKitchen());
        addAreaToGame(new HouseRightRoom());
    }

    /**
     * Create the game by lunching the different elements of the game such as
     * the arena
     * the player
     * the event
     *
     * @param window (Window): the window to display
     * @param fileSystem (FileSystem): the file system to use
     * @return (boolean): true if the game is initialized, false otherwise
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
     * @param deltaTime elapsed time since last update, in seconds, non-negative
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

        // If the area town is initialized, then start added events
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

    /** End the game */
    public void end() {}

    /**
     * Create events and the whole plot of the game
     */
    private void event() {
      ICMonChainedEvent icMonChainedEvent = new ICMonChainedEvent(
                player,
                new IntroductionEvent(player),
                new FirstInteractionWithProfOakEvent(player),
                new CapturePokemon(player),
                new FirstInteractionWithGarryEvent(player),
                new EndOfTheGameEvent(player));

        icMonChainedEvent.start();
    }

    /**
     * Randomly spawns ball into the area town based on calculated probabilities
     * If the ball is spawned on a cell that is not walkable, it is automatically
     * not registered by functions implemented in game-engine
     */
    private void randomEvent() {
        int upperbound = 10000;
        int random_number = rand.nextInt(upperbound);
        // Chance to spawn a ball is one out of 250 approximately
        if (random_number <= 30) {
            // Spawns a ball to a random position on the map
            ballEvent(areaList.get(0), getTownRandomCoordinates()).start();
        }

        // Chance to spawn a fruit is one out of 500 approximately
        if (random_number > 40 && random_number <= 60) {
            // Spawns a fruit to a random position on the map
            fruitEvent();
        }

        // Chance to spawn a Bulbizarre is one out of 1000 approximately
        if (random_number > 60 && random_number <= 75) {
            // Spawns a fruit to a random position on the map
            Bulbizarre bulbizarre = new Bulbizarre(areaList.get(0), Orientation.DOWN, getTownRandomCoordinates());
            areaList.get(0).registerActor(bulbizarre);
        }

        // Chance to spawn a Nidoqueen is one out of 2000 approximately
        if (random_number > 90 && random_number <= 100) {
            // Spawns a Nidoqueen to a random position on the map
            Nidoqueen nidoqueen = new Nidoqueen(areaList.get(0), Orientation.DOWN, getTownRandomCoordinates());
            areaList.get(0).registerActor(nidoqueen);
        }

        //Chance to spawn a Latios is one out of 5000
        if (random_number > 100 && random_number <= 105) {
            // Spawns a Latios to a random position on the map
            Latios latios = new Latios(areaList.get(0), Orientation.DOWN, getTownRandomCoordinates());
            areaList.get(0).registerActor(latios);
        }
    }

    /** @return (DiscreteCoordinates): random coordinates in town */
    private DiscreteCoordinates getTownRandomCoordinates() {
        int coordinateX = rand.nextInt(31);
        int coordinateY = rand.nextInt(33);
        return new DiscreteCoordinates(coordinateX, coordinateY);
    }

    /**
     * Creates an event of collecting a ball but does not start it
     *
     * @return (CollectItemEvent): event of collecting a ball
     */
    private CollectItemEvent ballEvent(Area area, DiscreteCoordinates coords) {
        ICBall randomBall = new ICBall(area, coords);
        CollectItemEvent randomEvent = new CollectItemEvent(randomBall, player);
        randomEvent.onStart(new RegisterinAreaAction(area, randomBall));
        return randomEvent;
    }
    /**
     * Spawns a piece of fruit that can be eaten (picked up) by the player
     * This fruit allows your player to heal all their pokemons to full health
     */
    private void fruitEvent() {
        // Spawns a fruit that can be collected and creates an event
        Fruit fruit = new Fruit(areaList.get(0), getTownRandomCoordinates());
        CollectItemEvent heal = new CollectItemEvent(fruit, player);
        heal.onStart(new RegisterinAreaAction(areaList.get(0), fruit));
        heal.start();
    }
    /**
     * Add an area to the game and to the list of different areas
     *
     * @param area (Area): the area to add
     */
    private void addAreaToGame(Area area) {
        addArea(area);
        areaList.add(area);
    }

    /** Game state of ICMon */
    public class ICMonGameState {

        /** Message sent by the different part of the game to be treated */
        private GamePlayMessage message = null;

        /**
         * Default ICMonGameState constructor
         */
        private ICMonGameState() {}

        /**
         * Delegate the interaction between interactor and interactable to different current events
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
         * Allows to send a message to the game
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
            // Loads message
            ICMonEvent event = ((SuspendWithEventMessage)message).getEvent();

            if (event.isMenuPause()) {
                // Starts the events of pausing the game
                event.onStart(new SetPauseAction(this, (event).getMenu()));
                event.onComplete(new ResumeMenu(this));

                // Suspends all current events
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