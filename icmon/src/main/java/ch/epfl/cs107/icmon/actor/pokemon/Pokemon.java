package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public abstract class Pokemon extends ICMonActor implements ICMonFightableActor {
    private final List<ICMonFightAction> fightActions;
    private final Sprite pokemon;
    private final String name;
    private final float hpMax;
    private final int damage;
    private float hp;

    /**
     * Default Pokemon constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     * @param name        (String) : Name of the pokémon. Not null
     * @param damage      (int) : Dégâts infligés par le pokémon. Not null
     * @param hpMax       (float) : Nombre de pv maximum du pokémon
     */
    public Pokemon(Area area, Orientation orientation, DiscreteCoordinates position, String name, int damage, float hpMax) {
        super(area, orientation, position);
        this.damage = damage;
        this.hpMax = hpMax;
        this.name = name;
        hp = hpMax;

        pokemon = new RPGSprite("pokemon/" + name, 1, 1, this);
        fightActions = new ArrayList<ICMonFightAction>();
    }

    /**
     * @author Hamza REMMAL (hamza.remmal@epfl.ch)
     */
    public final class PokemonProperties {

        public String name(){
            return name;
        }

        public float hp(){
            return hp;
        }

        public float maxHp(){
            return hpMax;
        }

        public int damage(){
            return damage;
        }

    }
    /**
    * Allows access to the property of the pokémon
    *
    * @return (PokemonProperties) : The property of the pokémon
    */
    public PokemonProperties properties() { return new PokemonProperties(); }

    /**
     * Getter for the cells occupied by the pokémon
     *
     * @return (List<DiscreteCoordinates>) : The list of the cells occupied by the pokémon
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return super.getCurrentCells();
    }


    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
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
     * Draws the pokemon
     *
     * @param canvas (Canvas) : The canvas on which the pokemon is drawn
     */
    @Override
    public void draw(Canvas canvas) {
        pokemon.draw(canvas);
    }

    /**
     * Return itself, allow polymorphisme for the fight methode of the ICMonPlayer
     *
     * @return (Pokemon) : The pokemon chosen
     */
    @Override
    public Pokemon choosenPokemon() {
        return this;
    }

    /**
     * Add a fight action to the pokemon
     *
     * @param actions (ICMonFightAction) : The fight action to add
     */
    public void addFightActions(ICMonFightAction ... actions) {
        Collections.addAll(fightActions, actions);
    }

    /**
     * Getter for the fight actions of the pokemon
     *
     * @return (List<ICMonFightAction>) : The list of the fight actions of the pokemon
     */
    public List<ICMonFightAction> getFightActions() {
        return fightActions;
    }

    /**
     * Getter to know if the pokemon is alive
     *
     * @return (boolean) : True if the pokemon is alive, false otherwise
     */
    public boolean isAlive() {
        return hp > 0;
    }

    /**
     * The pokemon receives an attack
     *
     * @param damage : The damage received
     */
    public void receiveAttack(int damage) {
        if (isAlive()) {
            hp -= damage;
            if (hp < 0) {
                hp = 0;
            }
        }
    }

    public void heal() {
        hp = hpMax;
    }
}