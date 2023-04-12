package io.lilybukkit.lilybukkit3.exception;

/**
 * Called when a call to CB-b1.7.2 API is made, that cannot be fulfilled due to those features not existing in a1.1.0/a1.0.16.05_13
 *
 * @author VladTheMountain
 */
public class UnbackportableAPIException extends Exception {

    public UnbackportableAPIException() {
        super();
    }

    public UnbackportableAPIException(String message) {
        super(message);
    }

    public UnbackportableAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnbackportableAPIException(Throwable cause) {
        super(cause);
    }

    public UnbackportableAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
