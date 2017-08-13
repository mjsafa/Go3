package mojtaba.safaeian.go3.api.domain;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/14/2017.
 */
public class GameStateException extends RuntimeException {

    public GameStateException() {
    }

    public GameStateException(String message) {
        super(message);
    }

    public GameStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameStateException(Throwable cause) {
        super(cause);
    }

    public GameStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
