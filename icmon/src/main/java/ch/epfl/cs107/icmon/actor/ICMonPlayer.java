package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonSelectionEvent;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.message.PassDoorMessage;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ICMonPlayer extends ICMonActor implements Interactor {
    private final int ANIMATION_DURATION = 8;
    private final static int MOVE_DURATION = 2;
    private final ICMonPlayerInteractionHandler handler;
    private final ICMon.ICMonEventManager eventManager;
    private final ICMon.ICMonGameState gameState;
    private final List<Pokemon> pokemonList;
    private OrientedAnimation animation;
    private Dialog dialog;
    private final OrientedAnimation[] animations = {
            new OrientedAnimation("actors/player", ANIMATION_DURATION/2, Orientation.DOWN, this),
            new OrientedAnimation("actors/player_water", ANIMATION_DURATION/2, Orientation.DOWN, this)
    };
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area            (Area): Owner area. Not null
     * @param position        (Coordinate): Initial position of the entity. Not null
     */
    public ICMonPlayer(Area area, DiscreteCoordinates position, String spriteName, ICMon.ICMonGameState gameState, ICMon.ICMonEventManager eventManager) {
        super(area, Orientation.DOWN, position);
        this.eventManager = eventManager;
        this.gameState = gameState;

        animation = new OrientedAnimation(spriteName, ANIMATION_DURATION/2, Orientation.DOWN, this);
        handler = new ICMonPlayerInteractionHandler();
        pokemonList = new ArrayList<>();
    }

    public void update(float deltaTime) {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        if (dialog != null && !dialog.isCompleted()) {
            if (keyboard.get(Keyboard.SPACE).isPressed()) {
                dialog.update(deltaTime);
            }
        } else {
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));

            if (isDisplacementOccurs()) {
                animation.update(deltaTime);
            } else {
                animation.reset();
            }
        }
        super.update(deltaTime);
    }

    public void fight(ICMonFightableActor actor){
            PokemonSelectionEvent selectionEvent = new PokemonSelectionEvent(this, actor, gameState);
            gameState.send(new SuspendWithEventMessage(selectionEvent));
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
        if (dialog != null && !dialog.isCompleted()){
            dialog.draw(canvas);
        }
    }
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return super.getCurrentCells();
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        if (dialog == null || dialog.isCompleted()) {
            return keyboard.get(Keyboard.L).isPressed();
        }
        return false;
    }

    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    public void openDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public void addPokemon(Pokemon pokemon){
        pokemonList.add(pokemon);
    }

    public ICMon.ICMonEventManager getEventManager() {
        return eventManager;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }
    public boolean isDialogCompleted(){
        return dialog.isCompleted();
    }
    public Area getCurrentArea(){
        return super.getOwnerArea();
    }

    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor {

        @Override
        public void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {
            if (isCellInteraction) {
                if (cell.getWalkingType() == ICMonBehavior.AllowedWalkingType.FEET) {
                    animations[0].orientate(getOrientation());
                    animation = animations[0];
                }
                if (cell.getWalkingType() == ICMonBehavior.AllowedWalkingType.SURF) {
                    animations[1].orientate(getOrientation());
                    animation = animations[1];
                }
            }
        }

        @Override
        public void interactWith(ICBall ball, boolean isCellInteraction) {
            if (!isCellInteraction) {
                ball.collect();
            }
        }

        @Override
        public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
            gameState.acceptInteraction(assistant, isCellInteraction);
        }

        @Override
        public void interactWith(Door door, boolean isCellInteraction) {
            if(isCellInteraction) {
                PassDoorMessage message = new PassDoorMessage(door, gameState);
                gameState.send(message);
            }
        }

        @Override
        public void interactWith(Pokemon pokemon, boolean isCellInteraction) {
            if (!isCellInteraction){
                fight(pokemon);
            }
        }

        @Override
        public void interactWith(ProfOak profOak, boolean isCellInteraction) {
            gameState.acceptInteraction(profOak, isCellInteraction);
        }

        @Override
        public void interactWith(Garry garry, boolean isCellInteraction) {
            gameState.acceptInteraction(garry, isCellInteraction);
        }
    }
}
