package ch.epfl.cs107.icmon.gamelogic.actions;
// A enlever !!!!!!!!!!!!!!!!!
public class LogAction implements Action{
    private final String message;
    public LogAction(String message){
        this.message = message;
    }

    @Override
    public void perform() {
        System.out.println(message);
    }
}