package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;

public interface ICMonInteractionVisitor extends AreaInteractionVisitor {

    /**
     * handle the interaction between the player and the ball, overrite by the different handler
     *
     * @param ball (ICBall): the ball that interact with the player
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(ICBall ball, boolean isCellInteraction) {}

    /**
     * handle the interaction between the player and the cell, overrite by the different handler
     *
     * @param cell (ICMonBehavior.ICMonCell): the cell that interact with the player
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {}

    /**
     * handle the interaction between the player and the shop assistant, overrite by the different handler
     *
     * @param assistant (ICShopAssistant): the shop assistant that interact with the player
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {}

    /**
     * handle the interaction between the player and the door, overrite by the different handler
     *
     * @param door (Door): the door that interact with the player
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(Door door, boolean isCellInteraction) {}

    /**
     * handle the interaction between the player and the pokemon, overrite by the different handler
     *
     * @param pokemon (Pokemon): the pokemon that interact with the player
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(Pokemon pokemon, boolean isCellInteraction) {}

    /**
     * handle the interaction between the player and the prof oak, overrite by the different handler
     *
     * @param profOak (ProfOak): the prof oak that interact with the player
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(ProfOak profOak, boolean isCellInteraction) {}

    /**
     * handle the interaction between the player and the garry, overrite by the different handler
     *
     * @param garry (Garry): the garry that interact with the player
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(Garry garry, boolean isCellInteraction) {}
}