/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.trace;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.langfuse.client.core.ClientOptions;
import com.langfuse.client.core.LangfuseClientApiException;
import com.langfuse.client.core.LangfuseClientException;
import com.langfuse.client.core.MediaTypes;
import com.langfuse.client.core.ObjectMappers;
import com.langfuse.client.core.QueryStringMapper;
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
import com.langfuse.client.resources.commons.types.TraceWithFullDetails;
import com.langfuse.client.resources.trace.requests.DeleteTracesRequest;
import com.langfuse.client.resources.trace.requests.GetTracesRequest;
import com.langfuse.client.resources.trace.types.DeleteTraceResponse;
import com.langfuse.client.resources.trace.types.Traces;

public class TraceClient {
  protected final ClientOptions clientOptions;

  public TraceClient(ClientOptions clientOptions) {
    this.clientOptions = clientOptions;
  }

  /**
   * Get a specific trace
   */
  public TraceWithFullDetails get(String traceId) {
    return get(traceId,null);
  }

  /**
   * Get a specific trace
   */
  public TraceWithFullDetails get(String traceId, RequestOptions requestOptions) {
    HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
      .addPathSegments("api/public")
      .addPathSegments("traces")
      .addPathSegment(traceId)
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
        return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), TraceWithFullDetails.class);
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

  /**
   * Delete a specific trace
   */
  public DeleteTraceResponse delete(String traceId) {
    return delete(traceId,null);
  }

  /**
   * Delete a specific trace
   */
  public DeleteTraceResponse delete(String traceId, RequestOptions requestOptions) {
    HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
      .addPathSegments("api/public")
      .addPathSegments("traces")
      .addPathSegment(traceId)
      .build();
    Request okhttpRequest = new Request.Builder()
      .url(httpUrl)
      .method("DELETE", null)
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
        return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), DeleteTraceResponse.class);
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

  /**
   * Get list of traces
   */
  public Traces list() {
    return list(GetTracesRequest.builder().build());
  }

  /**
   * Get list of traces
   */
  public Traces list(GetTracesRequest request) {
    return list(request,null);
  }

  /**
   * Get list of traces
   */
  public Traces list(GetTracesRequest request, RequestOptions requestOptions) {
    HttpUrl.Builder httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
      .addPathSegments("api/public")
      .addPathSegments("traces");if (request.getPage().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "page", request.getPage().get().toString(), false);
      }
      if (request.getLimit().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "limit", request.getLimit().get().toString(), false);
      }
      if (request.getUserId().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "userId", request.getUserId().get(), false);
      }
      if (request.getName().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "name", request.getName().get(), false);
      }
      if (request.getSessionId().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "sessionId", request.getSessionId().get(), false);
      }
      if (request.getFromTimestamp().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "fromTimestamp", request.getFromTimestamp().get().toString(), false);
      }
      if (request.getToTimestamp().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "toTimestamp", request.getToTimestamp().get().toString(), false);
      }
      if (request.getOrderBy().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "orderBy", request.getOrderBy().get(), false);
      }
      if (request.getTags().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "tags", request.getTags().get(), false);
      }
      if (request.getVersion().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "version", request.getVersion().get(), false);
      }
      if (request.getRelease().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "release", request.getRelease().get(), false);
      }
      if (request.getEnvironment().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "environment", request.getEnvironment().get(), false);
      }
      if (request.getFields().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "fields", request.getFields().get(), false);
      }
      Request.Builder _requestBuilder = new Request.Builder()
        .url(httpUrl.build())
        .method("GET", null)
        .headers(Headers.of(clientOptions.headers(requestOptions)))
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json");
      Request okhttpRequest = _requestBuilder.build();
      OkHttpClient client = clientOptions.httpClient();
      if (requestOptions != null && requestOptions.getTimeout().isPresent()) {
        client = clientOptions.httpClientWithTimeout(requestOptions);
      }
      try (Response response = client.newCall(okhttpRequest).execute()) {
        ResponseBody responseBody = response.body();
        if (response.isSuccessful()) {
          return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), Traces.class);
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

    /**
     * Delete multiple traces
     */
    public DeleteTraceResponse deleteMultiple(DeleteTracesRequest request) {
      return deleteMultiple(request,null);
    }

    /**
     * Delete multiple traces
     */
    public DeleteTraceResponse deleteMultiple(DeleteTracesRequest request,
        RequestOptions requestOptions) {
      HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
        .addPathSegments("api/public")
        .addPathSegments("traces")
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
        .method("DELETE", body)
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
          return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), DeleteTraceResponse.class);
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
