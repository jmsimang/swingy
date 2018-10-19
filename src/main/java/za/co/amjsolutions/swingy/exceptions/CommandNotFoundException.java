package za.co.amjsolutions.swingy.exceptions;

public class CommandNotFoundException extends Exception {
    /**
     * Constructs a new command not found exception with the specified detail
     * message.
     *
     * @param message the detail error message.
     */
    public CommandNotFoundException(String message) {
        super(message);
    }
}
