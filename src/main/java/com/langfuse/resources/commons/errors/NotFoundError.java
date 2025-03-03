/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.commons.errors;

import core.LangfuseLangfuseApiException;
import java.lang.Object;

public final class NotFoundError extends LangfuseLangfuseApiException {
  /**
   * The body of the response that triggered the exception.
   */
  private final Object body;

  public NotFoundError(Object body) {
    super("NotFoundError", 404, body);
    this.body = body;
  }

  /**
   * @return the body
   */
  @java.lang.Override
  public Object body() {
    return this.body;
  }
}
