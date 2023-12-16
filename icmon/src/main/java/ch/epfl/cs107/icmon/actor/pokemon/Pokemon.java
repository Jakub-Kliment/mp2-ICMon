package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public abstract class Pokemon extends ICMonActor {

    private String name;
    private int hp;
    private final int hpMax;
    private final int attackDamage;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Pokemon(Area area, Orientation orientation, DiscreteCoordinates position, String name, int attackDamage, int hpMax) {
        super(area, orientation, position);
        this.name = name;
        this.attackDamage = attackDamage;
        this.hpMax = hpMax;
        hp = hpMax;
    }

    /**
     * @author Hamza REMMAL (hamza.remmal@epfl.ch)
     */
    public final class PokemonProperties {

        public String name(){
            return null;
        }

        public float hp(){
            return 0f;
        }

        public float maxHp(){
            return 0f;
        }

        public int damage(){
            return 0;
        }

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
        if (isCellInteraction) {
            v.interactWith(this, true);
        }
    }
    @Override
    public void draw(Canvas canvas) {
        new RPGSprite("pokemon/" + name, 1, 1, this);
    }
    public boolean isAlive() {
        return hp > 0;
    }
    public void receiveAttack(int damage) {
        if (isAlive()) {
            hp -= damage;
        }
        if (hp < 0) {
            hp = 0;
        }
    }
}