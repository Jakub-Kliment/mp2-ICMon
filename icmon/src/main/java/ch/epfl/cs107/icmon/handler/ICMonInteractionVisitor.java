package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;

public interface ICMonInteractionVisitor extends AreaInteractionVisitor {
    default void interactWith(Interactable other, boolean isCellInteraction) {
    }
}
