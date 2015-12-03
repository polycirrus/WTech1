package by.bsuir.lab01.service;

/**
 * Thrown to indicate that a service could not produce a response due to an exceptional situation.
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ServiceException with the specified detail message.
     *
     * @param message the detail message
     */
    public ServiceException(String message){
		super(message);
	}

    /**
     * Constructs a new ServiceException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param ex      the cause
     */
    public ServiceException(String message, Exception ex){
		super(message, ex);
	}
}
