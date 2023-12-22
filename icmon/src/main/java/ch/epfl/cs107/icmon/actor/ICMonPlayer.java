package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.items.Fruit;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Pikachu;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.gamelogic.actions.HealPokemonsAction;
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
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ICMonPlayer extends ICMonActor implements Interactor {

    /** Duration of the animation of the sprite */
    private final int ANIMATION_DURATION = 8;

    /** Duration of the movement of the sprite */
    private final static int MOVE_DURATION = 4;

    /** Handler for the interactions with the player */
    private final ICMonPlayerInteractionHandler handler;

    /** Event manager of the game */
    private final ICMon.ICMonEventManager eventManager;

    /** Game state of the game */
    private final ICMon.ICMonGameState gameState;

    /** List of pokemons of the player */
    private final List<Pokemon> pokemonList;

    /** Animation of the player */
    private OrientedAnimation animation;

    /** Dialog of the player */
    private Dialog dialog;

    /** Number of pokeball of the player */
    private int ballNumber;

    /** Graphics for the number of pokeball */
    private TextGraphics ballNumberGraphics;

    /** List of the animations of the player */
    private final OrientedAnimation[] animations = {
            new OrientedAnimation("actors/player", ANIMATION_DURATION/2, Orientation.DOWN, this),
            new OrientedAnimation("actors/player_water", ANIMATION_DURATION/2, Orientation.DOWN, this)
    };

    /**
     * Default ICMonPlayer constructor
     *
     * @param area         (Area): Owner area. Not null
     * @param position     (DiscreteCoordinates): Initial position of the entity. Not null
     * @param spriteName   (String) : Name of the sprite of the player. Not null
     * @param gameState    (ICMon.ICMonGameState) : Game state. Not null
     * @param eventManager (ICMon.ICMonEventManager) : Event manager of the game. Not null
     */
    public ICMonPlayer(Area area, DiscreteCoordinates position, String spriteName, ICMon.ICMonGameState gameState, ICMon.ICMonEventManager eventManager) {
        super(area, Orientation.DOWN, position);
        this.eventManager = eventManager;
        this.gameState = gameState;

        animation = new OrientedAnimation(spriteName, ANIMATION_DURATION/2, Orientation.DOWN, this);
        handler = new ICMonPlayerInteractionHandler();
        pokemonList = new ArrayList<>();
        ballNumberGraphics = new TextGraphics("ICMonBall : "+ballNumber, 0.8f, Color.WHITE);
        ballNumberGraphics.setParent(this);
        ballNumberGraphics.setAnchor(new Vector(-5f, -5f));
        ballNumber = 0;
    }

    /**
     * Updates the ICMonPlayer
     * Manage the movement, dialog and animations of the player
     *
     * @param deltaTime (float): elapsed time since last update, in seconds, non-negative
     */
    public void update(float deltaTime) {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        // Manages dialogs
        if (dialog != null && !dialog.isCompleted()) {
            if (keyboard.get(Keyboard.SPACE).isPressed()) {
                dialog.update(deltaTime);
            }
        } else {
            // Manages movement
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP), keyboard.get(Keyboard.W));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN), keyboard.get(Keyboard.S));
            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT), keyboard.get(Keyboard.A));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT), keyboard.get(Keyboard.D));

            // Manages animations
            if (isDisplacementOccurs()) {
                animation.update(deltaTime);
            } else {
                animation.reset();
                resetMotion();
            }
        }
        ballNumberGraphics.setText("ICMonBall : "+ballNumber);
        super.update(deltaTime);
    }

    /**
     * Fighting other actors
     * Can fight an NPCActor or a Pokemon
     *
     * @param actor (ICMonFightableActor): the actor to fight with
     */
    public void fight(ICMonFightableActor actor) {
            PokemonSelectionEvent selectionEvent = new PokemonSelectionEvent(this, actor, gameState);
            gameState.send(new SuspendWithEventMessage(selectionEvent));
    }

    /**
     * Move the player and assures animations
     *
     * @param orientation : the orientation of the player
     * @param b : the button pressed
     * @param c : the button pressed
     */
    private void moveIfPressed(Orientation orientation, Button b, Button c) {
        if (b.isDown() || c.isDown()) {
            if (!isDisplacementOccurs()) {
                animation.orientate(orientation);
                orientate(orientation);
                move(MOVE_DURATION);
            }
        }
    }

    /**
     * Draw the player
     *
     * @param canvas : the canvas on which the player is drawn
     */
    @Override
    public void draw(Canvas canvas) {
        animation.draw(canvas);
        ballNumberGraphics.draw(canvas);
        // Cannot move while in dialog
        if (dialog != null && !dialog.isCompleted()){
            dialog.draw(canvas);
        }
    }

    /**
     * Center the camera on the player
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    /**@return (boolean) : indicates that the Player takes space on current cell*/
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    /**
     * Getter for the current cells of the entity
     *
     * @return (List<DiscreteCoordinates>) : List of the coordinates of the cells occupied by the player
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return super.getCurrentCells();
    }

    /**
     * Getter for the field of view cells of the player
     *
     * @return (List<DiscreteCoordinates>) : List of the coordinates of the cells in the field of view of the player
     */
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    /**@return (boolean): true, so the player requires cell interaction */
    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    /**@return (boolean): true so the player requires view interaction */
    @Override
    public boolean wantsViewInteraction() {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        if (dialog == null || dialog.isCompleted()) {
            return keyboard.get(Keyboard.L).isPressed();
        }
        return false;
    }

    /**
     * Delegate interactions wanted by the player to the interaction handler
     *
     * @param other : the interactable with which the player interacts
     * @param isCellInteraction : true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
    }

    /**
     * Delegate interactions to the interaction handler
     *
     * @param v (AreaInteractionVisitor) : the interactor that wants to interact with this interactable
     * @param isCellInteraction : true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    /**
     * Open a dialog
     *
     * @param dialog : the dialog to open
     */
    public void openDialog(Dialog dialog){
        this.dialog = dialog;
    }

    /**
     * Add a pokemon to the player's pokemon list
     *
     * @param pokemon : the pokemon to add
     */
    public void addPokemon(Pokemon pokemon){
        if (ballNumber> 0){
            pokemonList.add(pokemon);
            openDialog(new Dialog("collect_pokemon"));
            ballNumber--;
        } else {
            openDialog(new Dialog("collect_pokemon_fail"));
        }
    }

    /**
     * Getter for the event manager
     *
     * @return (ICMonEventManager) : the event manager
     */
    public ICMon.ICMonEventManager getEventManager() {
        return eventManager;
    }

    /**
     * Getter for the pokemon list
     *
     * @return List<Pokemon> : the pokemon list
     */
    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    /**
     * Allow to know if the dialog is completed
     *
     * @return (boolean) : true if the dialog is completed
     */
    public boolean isDialogCompleted(){
        return dialog.isCompleted();
    }

    /**
     * Getter for the current area
     *
     * @return (Area) : the current area
     */
    public Area getCurrentArea(){
        return super.getOwnerArea();
    }
    public void addBall(){
        ballNumber++;
    }

    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor {

        /**
         * Manage interactions between the player and a cell
         * Change the animation of the player based on cell type that he is standing on
         *
         * @param cell              (ICMonCell) : the cell with which the player interacts
         * @param isCellInteraction (boolean): true because the interaction comes from a cell
         */
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

        /**
         * Manage interactions between the player and a ball
         * Collect the ball
         *
         * @param ball : the ball with which the player interacts
         * @param isCellInteraction : false cause the interaction is a view interaction
         */
        @Override
        public void interactWith(ICBall ball, boolean isCellInteraction) {
            if (!isCellInteraction) {
                ballNumber++;
                ball.collect();
            }
        }

        /**
         * Manage interactions between the player and an assistant
         * Delegate the interaction to the game state<
         *
         * @param assistant : the assistant with which the player interacts
         * @param isCellInteraction : false cause the interaction is a view interaction
         */
        @Override
        public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
            if (!isCellInteraction){
                gameState.acceptInteraction(assistant, isCellInteraction);
            }
        }

        /**
         * Manage interactions between the player and a door
         * Go through the door
         *
         * @param door : the door with which the player interacts
         * @param isCellInteraction : true cause the interaction is a cell interaction
         */
        @Override
        public void interactWith(Door door, boolean isCellInteraction) {
            if(isCellInteraction) {
                PassDoorMessage message = new PassDoorMessage(door, gameState);
                gameState.send(message);
            }
        }

        /**
         * Manage interactions between the player and a pokemon
         * Fight the pokemon
         *
         * @param pokemon : the pokemon with which the player interacts
         * @param isCellInteraction : false cause the interaction is a view interaction
         */
        @Override
        public void interactWith(Pokemon pokemon, boolean isCellInteraction) {
            if (!isCellInteraction){
                fight(pokemon);
            }
        }

        /**
         * Manage interactions between the player and ProfOak
         * Delegate the interaction to the game state
         *
         * @param profOak : ProfOak with which the player interacts
         * @param isCellInteraction : false cause the interaction is a view interaction
         */
        @Override
        public void interactWith(ProfOak profOak, boolean isCellInteraction) {
            gameState.acceptInteraction(profOak, isCellInteraction);
        }

        /**
         * Manage interactions between the player and Garry
         * Delegate the interaction to the game state
         *
         * @param garry : Garry with which the player interacts
         * @param isCellInteraction : false cause the interaction is a view interaction
         */
        @Override
        public void interactWith(Garry garry, boolean isCellInteraction) {
            gameState.acceptInteraction(garry, isCellInteraction);
        }

        /**
         * Manage interactions between the player and a display
         * Player can see the message displayed on the screen
         *
         * @param display : display with message
         * @param isCellInteraction : false cause the interaction is a view interaction
         */
        @Override
        public void interactWith(Display display, boolean isCellInteraction) {
            if (!isCellInteraction) {
                openDialog(display.getDialog());
            }
        }

        /**
         * Manage interactions between the player and a desk
         * Player can see the message displayed on the desk
         *
         * @param desk : desk with message
         * @param isCellInteraction : false cause the interaction is a view interaction
         */
        @Override
        public void interactWith(Desk desk, boolean isCellInteraction) {
            if (!isCellInteraction) {
                gameState.acceptInteraction(desk.getActor(), isCellInteraction);
            }
        }

        /**
         * Manage interactions between the player and a fruit
         * Player can collect the fruit to heal his pokemons
         *
         * @param fruit : collectable fruit
         * @param isCellInteraction : false cause the interaction is a view interaction
         */
        @Override
        public void interactWith(Fruit fruit, boolean isCellInteraction) {
            if (!isCellInteraction) {
                // After picking up the fruit, the heal action is called
                HealPokemonsAction heal = new HealPokemonsAction(pokemonList);
                heal.perform();
                fruit.collect();
                openDialog(new Dialog("berry_interaction"));
            }
        }
    }
}
