package ch.epfl.cs107.icmon.gamelogic.actions;

public class LogAction implements Action {

    /** The message to log */
    private final String message;

    /**
     * Default LogAction constructor
     *
     * @param message (String): the message to log
     */
    public LogAction(String message){
        this.message = message;
    }

    /**
     * Perform the action, log the message in the console
     */
    @Override
    public void perform() {
        System.out.println(message);
    }
}