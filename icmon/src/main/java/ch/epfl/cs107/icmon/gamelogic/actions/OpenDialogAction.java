package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.play.engine.actor.Dialog;
// A revoir !!!!!!!!!!!!!!!!
public class OpenDialogAction implements Action{
    private final ICMonPlayer player;
    private final String dialogString;
    public OpenDialogAction(ICMonPlayer player, String dialogString){
        this.player = player;
        this.dialogString = dialogString;
    }
    @Override
    public void perform() {
        player.openDialog(new Dialog(dialogString));
    }
}
