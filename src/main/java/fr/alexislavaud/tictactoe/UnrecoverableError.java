package fr.alexislavaud.tictactoe;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class UnrecoverableError extends RuntimeException {
    /**
     * Default constructor
     */
    public UnrecoverableError() {
        super();
    }

    /**
     * Build the exception with an error messages
     * @param message The error message
     */
    public UnrecoverableError(String message) {
        super(message);
    }
}
