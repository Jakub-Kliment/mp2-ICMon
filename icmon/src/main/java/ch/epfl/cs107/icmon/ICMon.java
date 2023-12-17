package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.Arena;
import ch.epfl.cs107.icmon.area.maps.Lab;
import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.EndOfTheGameEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.message.GamePlayMessage;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;

public class ICMon extends AreaGame {
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    /** ??? */
    //private final String[] areas = {"town","lab","arena"};
    /** ??? */
    private ICMonPlayer player;
    private List<ICMonEvent> eventList;
    private List<ICMonEvent> startedEvent;
    private List<ICMonEvent> completedEvent;
    private ICMonGameState gameState;
    private ICMonEventManager eventManager;
    /** ??? */
    private int areaIndex;
    private void createAreas() {
        addArea(new Town());
        addArea(new Lab());
        addArea(new Arena());
    }
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            areaIndex = 0;
            initArea("town");

            eventManager = new ICMonEventManager();

            ICBall ball = new ICBall(getCurrentArea(), new DiscreteCoordinates(6,6));
            CollectItemEvent eventBall = new CollectItemEvent(ball, player);
            eventBall.onStart(new LogAction("CollectItemEvent started !"));
            eventBall.onStart(new RegisterinAreaAction(getCurrentArea(), ball));
            eventBall.onComplete(new LogAction("CollectItemEvent completed !"));
            eventBall.onComplete(new UnregisterEventAction(eventManager, eventBall));
            eventBall.onStart(new RegisterEventAction(eventManager, eventBall));
            eventBall.start();

            EndOfTheGameEvent eventEnd = new EndOfTheGameEvent(player);
            eventEnd.onStart(new RegisterEventAction(eventManager, eventEnd));
            eventEnd.onComplete(new UnregisterEventAction(eventManager, eventEnd));
            eventBall.onComplete(new StartEventAction(eventEnd));
            return true;
        }
        return false;
    }
    private void initArea(String areaKey) {
        ICMonArea area = (ICMonArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();
        gameState = new ICMonGameState();
        player = new ICMonPlayer(area, coords, "actors/player", gameState);
        eventList = new ArrayList<>();
        startedEvent = new ArrayList<>();
        completedEvent = new ArrayList<>();
        player.enterArea(area, coords);
        player.centerCamera();
    }
    public void update(float deltaTime) {
        Keyboard keyboard = getCurrentArea().getKeyboard();
        if (keyboard.get(Keyboard.R).isPressed()){
            begin(getWindow(),getFileSystem());
        }
        if (gameState.message != null){
            gameState.message.process();
            gameState.message = null;
        }
        for(ICMonEvent event : startedEvent){
            eventList.add(event);
        }
        startedEvent.clear();
        for(ICMonEvent event : completedEvent){
            eventList.remove(event);
        }
        completedEvent.clear();
        for(ICMonEvent event : eventList){
            event.update(deltaTime);
        }
        super.update(deltaTime);
    }
    public String getTitle() {
        return "ICMon";
    }
    public void end() {
    }
    public class ICMonGameState {
        private GamePlayMessage message = null;
        private ICMonGameState(){}
        public void acceptInteraction(Interactable interactable , boolean isCellInteraction) {
            for (var event : ICMon.this.eventList)
                interactable.acceptInteraction(event, isCellInteraction);
        }
        public void send(GamePlayMessage message){
            this.message = message;
        }
        public void switchArea(String areaName, DiscreteCoordinates coordinates) {
            player.leaveArea();
            ICMonArea currentArea = (ICMonArea) setCurrentArea(areaName, false);
            player.enterArea(currentArea, coordinates);
        }
        public void addEvent(ICMonEvent event){
            startedEvent.add(event);
        }
    }

    public class ICMonEventManager{
        public List<ICMonEvent> getStartedEvent(){
            return startedEvent;
        }
        public List<ICMonEvent> getCompletedEvent(){
            return completedEvent;
        }
    }
}