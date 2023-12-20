package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class OpenDialogAction implements Action {

    /** The player to open the dialog to */
    private final ICMonPlayer player;

    /** The dialog to open */
    private final String dialogString;

    /**
     * Default OpenDialogAction constructor
     *
     * @param player (ICMonPlayer): the player to open the dialog to
     * @param dialogString (String): the dialog to open
     */
    public OpenDialogAction(ICMonPlayer player, String dialogString){
        this.player = player;
        this.dialogString = dialogString;
    }

    /**
     * Perform the action, open the dialog to the player
     */
    @Override
    public void perform() {
        player.openDialog(new Dialog(dialogString));
    }
}
