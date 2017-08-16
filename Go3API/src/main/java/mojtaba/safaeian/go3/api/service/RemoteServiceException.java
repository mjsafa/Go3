package mojtaba.safaeian.go3.api.service;

/**
 * @author Mojtaba Safaeian
 *         Created at: 08/16/2017.
 */
public class RemoteServiceException extends RuntimeException {

    public RemoteServiceException() {
    }

    public RemoteServiceException(String message) {
        super(message);
    }

    public RemoteServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteServiceException(Throwable cause) {
        super(cause);
    }

    public RemoteServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
