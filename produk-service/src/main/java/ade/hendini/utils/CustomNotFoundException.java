/*
 * Author : Ade Hendini
 * Email : adehendini@gmail.com
 * Date : 10/24/22, 5:18 AM
 */

package ade.hendini.utils;

public class CustomNotFoundException extends RuntimeException{
    public CustomNotFoundException() {
        super();
    }

    public CustomNotFoundException(String message) {
        super(message);
    }

    public CustomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CustomNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
