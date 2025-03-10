/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.ingestion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.langfuse.client.core.ClientOptions;
import com.langfuse.client.core.LangfuseClientApiException;
import com.langfuse.client.core.LangfuseClientException;
import com.langfuse.client.core.MediaTypes;
import com.langfuse.client.core.ObjectMappers;
import com.langfuse.client.core.RequestOptions;
import java.io.IOException;
import java.lang.Object;
import java.lang.String;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import com.langfuse.client.resources.commons.errors.AccessDeniedError;
import com.langfuse.client.resources.commons.errors.Error;
import com.langfuse.client.resources.commons.errors.MethodNotAllowedError;
import com.langfuse.client.resources.commons.errors.NotFoundError;
import com.langfuse.client.resources.commons.errors.UnauthorizedError;
import com.langfuse.client.resources.ingestion.requests.IngestionRequest;
import com.langfuse.client.resources.ingestion.types.IngestionResponse;

public class IngestionClient {
  protected final ClientOptions clientOptions;

  public IngestionClient(ClientOptions clientOptions) {
    this.clientOptions = clientOptions;
  }

  /**
   * Batched ingestion for Langfuse Tracing.
   * If you want to use tracing via the API, such as to build your own Langfuse client implementation, this is the only API route you need to implement.
   * <p>Within each batch, there can be multiple events.
   * Each event has a type, an id, a timestamp, metadata and a body.
   * Internally, we refer to this as the &quot;event envelope&quot; as it tells us something about the event but not the trace.
   * We use the event id within this envelope to deduplicate messages to avoid processing the same event twice, i.e. the event id should be unique per request.
   * The event.body.id is the ID of the actual trace and will be used for updates and will be visible within the Langfuse App.
   * I.e. if you want to update a trace, you'd use the same body id, but separate event IDs.</p>
   * <p>Notes:</p>
   * <ul>
   * <li>Introduction to data model: https://langfuse.com/docs/tracing-data-model</li>
   * <li>Batch sizes are limited to 3.5 MB in total. You need to adjust the number of events per batch accordingly.</li>
   * <li>The API does not return a 4xx status code for input errors. Instead, it responds with a 207 status code, which includes a list of the encountered errors.</li>
   * </ul>
   */
  public IngestionResponse batch(IngestionRequest request) {
    return batch(request,null);
  }

  /**
   * Batched ingestion for Langfuse Tracing.
   * If you want to use tracing via the API, such as to build your own Langfuse client implementation, this is the only API route you need to implement.
   * <p>Within each batch, there can be multiple events.
   * Each event has a type, an id, a timestamp, metadata and a body.
   * Internally, we refer to this as the &quot;event envelope&quot; as it tells us something about the event but not the trace.
   * We use the event id within this envelope to deduplicate messages to avoid processing the same event twice, i.e. the event id should be unique per request.
   * The event.body.id is the ID of the actual trace and will be used for updates and will be visible within the Langfuse App.
   * I.e. if you want to update a trace, you'd use the same body id, but separate event IDs.</p>
   * <p>Notes:</p>
   * <ul>
   * <li>Introduction to data model: https://langfuse.com/docs/tracing-data-model</li>
   * <li>Batch sizes are limited to 3.5 MB in total. You need to adjust the number of events per batch accordingly.</li>
   * <li>The API does not return a 4xx status code for input errors. Instead, it responds with a 207 status code, which includes a list of the encountered errors.</li>
   * </ul>
   */
  public IngestionResponse batch(IngestionRequest request, RequestOptions requestOptions) {
    HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
      .addPathSegments("api/public")
      .addPathSegments("ingestion")
      .build();
    RequestBody body;
    try {
      body = RequestBody.create(ObjectMappers.JSON_MAPPER.writeValueAsBytes(request), MediaTypes.APPLICATION_JSON);
    }
    catch(JsonProcessingException e) {
      throw new LangfuseClientException("Failed to serialize request", e);
    }
    Request okhttpRequest = new Request.Builder()
      .url(httpUrl)
      .method("POST", body)
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
        return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), IngestionResponse.class);
      }
      String responseBodyString = responseBody != null ? responseBody.string() : "{}";
      try {
        switch (response.code()) {
          case 400:throw new Error(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
          case 401:throw new UnauthorizedError(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
          case 403:throw new AccessDeniedError(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
          case 404:throw new NotFoundError(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
          case 405:throw new MethodNotAllowedError(ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
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
