package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
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
    private final String[] areas = {"town"};
    /** ??? */
    private ICMonPlayer player;
    private List<ICMonEvent> eventList;
    private ICMonGameState gameState;
    /** ??? */
    private int areaIndex;
    private void createAreas() {
        addArea(new Town());

    }
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            areaIndex = 0;
            initArea(areas[areaIndex]);

            ICBall ball = new ICBall(getCurrentArea(), new DiscreteCoordinates(6,6));
            CollectItemEvent event = new CollectItemEvent(ball, player);
            event.onStart(new LogAction("CollectItemEvent started !"));
            event.onStart(new RegisterinAreaAction(getCurrentArea(), ball));
            event.onComplete(new LogAction("CollectItemEvent completed !"));
            eventList.add(event);
            event.start();

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
        player.enterArea(area, coords);
        player.centerCamera();
    }
    public void update(float deltaTime) {
        Keyboard keyboard = getCurrentArea().getKeyboard();
        if (keyboard.get(Keyboard.R).isPressed()){
            begin(getWindow(),getFileSystem());
        }
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
        private ICMonGameState(){}
        void acceptInteraction(Interactable interactable , boolean isCellInteraction) {
            for (var event : ICMon.this.eventList)
                interactable.acceptInteraction(event, isCellInteraction);
        }
    }
}