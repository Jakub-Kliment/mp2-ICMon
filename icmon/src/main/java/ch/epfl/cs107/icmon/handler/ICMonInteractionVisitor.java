package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.actor.*;
import ch.epfl.cs107.icmon.actor.items.Fruit;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;

public interface ICMonInteractionVisitor extends AreaInteractionVisitor {

    /**
     * Handle the interaction between an interactor and the ball, overwrite by the different handlers
     *
     * @param ball (ICBall): the ball that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(ICBall ball, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and the cell, overwrite by the different handlers
     *
     * @param cell (ICMonBehavior.ICMonCell): the cell that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and the shop assistant, overwrite by the different handler
     *
     * @param assistant (ICShopAssistant): the shop assistant that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and the door, overwrite by the different handlers
     *
     * @param door (Door): the door that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(Door door, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and the pokemon, overwrite by the different handlers
     *
     * @param pokemon (Pokemon): the pokemon that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(Pokemon pokemon, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and Prof Oak, overwrite by the different handlers
     *
     * @param profOak (ProfOak): Prof Oak that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(ProfOak profOak, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and Garry, overwrite by the different handlers
     *
     * @param garry (Garry): Garry that interact with the player
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(Garry garry, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and the player, overwrite by the different handlers
     *
     * @param player (ICMonPlayer): the player that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(ICMonPlayer player, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and the display, overwrite by the different handlers
     *
     * @param display (Display): the display that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(Display display, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and the desk, overwrite by the different handlers
     *
     * @param desk (Desk): the desk that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(Desk desk, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and the fruit, overwrite by the different handlers
     *
     * @param fruit (Fruit): the fruit that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(Fruit fruit, boolean isCellInteraction) {}

    /**
     * Handle the interaction between an interactor and the WalkingNPC, overwrite by the different handlers
     *
     * @param npc (Interactable): the WalkingNPC that interacts with the interactor
     * @param isCellInteraction (boolean): true if the interaction is a cellInteraction, false if the interaction is a viewInteraction
     */
    default void interactWith(WalkingNPC npc, boolean isCellInteraction) {}
}