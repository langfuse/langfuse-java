/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.health;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.langfuse.client.core.ClientOptions;
import com.langfuse.client.core.LangfuseClientApiException;
import com.langfuse.client.core.LangfuseClientException;
import com.langfuse.client.core.ObjectMappers;
import com.langfuse.client.core.RequestOptions;
import java.io.IOException;
import java.lang.Object;
import java.lang.String;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import com.langfuse.client.resources.commons.errors.AccessDeniedError;
import com.langfuse.client.resources.commons.errors.Error;
import com.langfuse.client.resources.commons.errors.MethodNotAllowedError;
import com.langfuse.client.resources.commons.errors.NotFoundError;
import com.langfuse.client.resources.commons.errors.UnauthorizedError;
import com.langfuse.client.resources.health.errors.ServiceUnavailableError;
import com.langfuse.client.resources.health.types.HealthResponse;

public class HealthClient {
  protected final ClientOptions clientOptions;

  public HealthClient(ClientOptions clientOptions) {
    this.clientOptions = clientOptions;
  }

  /**
   * Check health of API and database
   */
  public HealthResponse health() {
    return health(null);
  }

  /**
   * Check health of API and database
   */
  public HealthResponse health(RequestOptions requestOptions) {
    HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
      .addPathSegments("api/public")
      .addPathSegments("health")
      .build();
    Request okhttpRequest = new Request.Builder()
      .url(httpUrl)
      .method("GET", null)
      .headers(Headers.of(clientOptions.headers(requestOptions)))
      .addHeader("Content-Type", "application/json")
      .addHeader("Accept", "application/json")
      .build();
    OkHttpClient client = clientOptions.httpClient();
    if (requestOptions != null && requestOptions.getTimeout().isPresent()) {
      client = clientOptions.httpClientWithTimeout(requestOptions);
    }
    try (Response response = client.newCall(okhttpRequest).execute()) {
      ResponseBody responseBody = response.body();
      if (response.isSuccessful()) {
        return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), HealthResponse.class);
      }
      String responseBodyString = responseBody != null ? responseBody.string() : "{}";
      try {
        switch (response.code()) {
          case 400:throw new Error(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
          case 401:throw new UnauthorizedError(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
          case 403:throw new AccessDeniedError(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
          case 404:throw new NotFoundError(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
          case 405:throw new MethodNotAllowedError(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
          case 503:throw new ServiceUnavailableError(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
        }
      }
      catch (JsonProcessingException ignored) {
        // unable to map error response, throwing generic error
      }
      throw new LangfuseClientApiException("Error with status code " + response.code(), response.code(), ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
    }
    catch (IOException e) {
      throw new LangfuseClientException("Network error executing HTTP request", e);
    }
  }
}
