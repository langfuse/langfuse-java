package com.langfuse.api;

/**
 * Runtime exception thrown when a Langfuse API call fails.
 *
 * <p>This is an unchecked exception so that the {@link LangfuseApi} interface methods
 * do not force callers to handle checked exceptions. Implementations convert
 * transport-specific checked exceptions into this type.
 *
 * @author Eric Deandrea
 */
public class LangfuseApiException extends RuntimeException {

    private final int statusCode;

    /**
     * @param message the error message
     */
    public LangfuseApiException(String message) {
        super(message);
        this.statusCode = 0;
    }

    /**
     * @param message the error message
     * @param cause the underlying cause
     */
    public LangfuseApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 0;
    }

    /**
     * @param message the error message
     * @param statusCode the HTTP status code returned by the server
     */
    public LangfuseApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * @param message the error message
     * @param statusCode the HTTP status code returned by the server
     * @param cause the underlying cause
     */
    public LangfuseApiException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    /**
     * @param cause the underlying cause
     */
    public LangfuseApiException(Throwable cause) {
        super(cause);
        this.statusCode = 0;
    }

    /**
     * Returns the HTTP status code from the failed API call, or {@code 0} if not applicable.
     *
     * @return the HTTP status code
     */
    public int getStatusCode() {
        return statusCode;
    }
}
