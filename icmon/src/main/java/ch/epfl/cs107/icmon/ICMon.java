package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
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

public class ICMon extends AreaGame {
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    private ICMonPlayer player;
    private List<ICMonEvent> eventList;
    private List<ICMonEvent> startedEvent;
    private List<ICMonEvent> completedEvent;
    private ICMonGameState gameState;
    private ICMonEventManager eventManager;
    private List<Area> areaList;
    private void createAreas() {
        areaList = new ArrayList<>();
        addAreaToGame(new Town());
        addAreaToGame(new Lab());
        addAreaToGame(new Arena());
        addAreaToGame(new House());
        addAreaToGame(new Shop());
    }
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            initArea("house");
            event();
            return true;
        }
        return false;
    }
    private void initArea(String areaKey) {
        ICMonArea area = (ICMonArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();
        gameState = new ICMonGameState();
        eventManager = new ICMonEventManager();
        player = new ICMonPlayer(area, coords, "actors/player", gameState, eventManager);
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
        eventList.addAll(startedEvent);
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
    public void end() {}
    private void event(){
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

    private void addAreaToGame(Area area){
        addArea(area);
        areaList.add(area);
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
            if (message instanceof SuspendWithEventMessage) {
                treatMessage(message);
            }
        }
        public void treatMessage(GamePlayMessage message) {
            ICMonEvent event = ((SuspendWithEventMessage)message).getEvent();
            if (event.isMenuPause()) {
                event.onStart(new setPauseAction(this, (event).getMenu()));
                event.onComplete(new ResumeMenu(this));

                for(ICMonEvent anEvent : eventList) {
                    event.onStart(new SuspendedEventAction(anEvent));
                    event.onComplete(new ResumeEventAction(anEvent));
                }
                event.start();
            }
        }
        public void switchArea(String areaName, DiscreteCoordinates coordinates) {
            player.leaveArea();
            ICMonArea currentArea = (ICMonArea) setCurrentArea(areaName, false);
            player.enterArea(currentArea, coordinates);
        }
        public void setMenuPause(PauseMenu menu){
            setPauseMenu(menu);
            requestPause();
        }
        public void stopMenuPause(){
            requestResume();
        }
    }

    public class ICMonEventManager{
        public void addStartedEvent(ICMonEvent event){
            startedEvent.add(event);
        }
        public void addCompletedEvent(ICMonEvent event){
            completedEvent.remove(event);
        }
    }
}