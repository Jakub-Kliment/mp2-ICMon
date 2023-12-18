package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.io.FileSystem;
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
    private String name;
    private int hp;
    private final int hpMax;
    private final int damage;
    private Sprite pokemon;
    private List<ICMonFightAction> fightActions;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Pokemon(Area area, Orientation orientation, DiscreteCoordinates position, String name, int damage, int hpMax) {
        super(area, orientation, position);
        this.name = name;
        this.damage = damage;
        this.hpMax = hpMax;
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
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return super.getCurrentCells();
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }
    @Override
    public boolean takeCellSpace() {
        return false;
    }
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
            ((ICMonInteractionVisitor)v).interactWith(this, isCellInteraction);
    }
    @Override
    public void draw(Canvas canvas) {
        pokemon.draw(canvas);
    }
    public boolean isAlive() {
        return hp > 0;
    }
    //PAS SUR !!!!!!!
    public void receiveAttack(int damage) {
        if (isAlive()) {
            hp -= damage;
            if (hp < 0) {
                hp = 0;
            }
        }
    }
    public PokemonProperties properties(){return new PokemonProperties();}
    public void addFightAction(ICMonFightAction ... actions) {
        Collections.addAll(fightActions, actions);
    }
    public List<ICMonFightAction> getFightAction() {
        return fightActions;
    }
}