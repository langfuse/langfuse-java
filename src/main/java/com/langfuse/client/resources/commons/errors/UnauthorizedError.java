/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.commons.errors;

import com.langfuse.client.core.LangfuseClientApiException;
import java.lang.Object;

public final class UnauthorizedError extends LangfuseClientApiException {
  /**
   * The body of the response that triggered the exception.
   */
  private final Object body;

  public UnauthorizedError(Object body) {
    super("UnauthorizedError", 401, body);
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
