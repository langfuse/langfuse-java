/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.scorev2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.langfuse.client.core.ClientOptions;
import com.langfuse.client.core.LangfuseClientApiException;
import com.langfuse.client.core.LangfuseClientException;
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
import okhttp3.Response;
import okhttp3.ResponseBody;
import com.langfuse.client.resources.commons.errors.AccessDeniedError;
import com.langfuse.client.resources.commons.errors.Error;
import com.langfuse.client.resources.commons.errors.MethodNotAllowedError;
import com.langfuse.client.resources.commons.errors.NotFoundError;
import com.langfuse.client.resources.commons.errors.UnauthorizedError;
import com.langfuse.client.resources.commons.types.Score;
import com.langfuse.client.resources.scorev2.requests.GetScoresRequest;
import com.langfuse.client.resources.scorev2.types.GetScoresResponse;

public class ScoreV2Client {
  protected final ClientOptions clientOptions;

  public ScoreV2Client(ClientOptions clientOptions) {
    this.clientOptions = clientOptions;
  }

  /**
   * Get a list of scores (supports both trace and session scores)
   */
  public GetScoresResponse get() {
    return get(GetScoresRequest.builder().build());
  }

  /**
   * Get a list of scores (supports both trace and session scores)
   */
  public GetScoresResponse get(GetScoresRequest request) {
    return get(request,null);
  }

  /**
   * Get a list of scores (supports both trace and session scores)
   */
  public GetScoresResponse get(GetScoresRequest request, RequestOptions requestOptions) {
    HttpUrl.Builder httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
      .addPathSegments("api/public/v2")
      .addPathSegments("scores");if (request.getPage().isPresent()) {
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
      if (request.getFromTimestamp().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "fromTimestamp", request.getFromTimestamp().get().toString(), false);
      }
      if (request.getToTimestamp().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "toTimestamp", request.getToTimestamp().get().toString(), false);
      }
      if (request.getEnvironment().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "environment", request.getEnvironment().get(), false);
      }
      if (request.getSource().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "source", request.getSource().get().toString(), false);
      }
      if (request.getOperator().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "operator", request.getOperator().get(), false);
      }
      if (request.getValue().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "value", request.getValue().get().toString(), false);
      }
      if (request.getScoreIds().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "scoreIds", request.getScoreIds().get(), false);
      }
      if (request.getConfigId().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "configId", request.getConfigId().get(), false);
      }
      if (request.getQueueId().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "queueId", request.getQueueId().get(), false);
      }
      if (request.getDataType().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "dataType", request.getDataType().get().toString(), false);
      }
      if (request.getTraceTags().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "traceTags", request.getTraceTags().get(), false);
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
          return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), GetScoresResponse.class);
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
     * Get a score (supports both trace and session scores)
     */
    public Score getById(String scoreId) {
      return getById(scoreId,null);
    }

    /**
     * Get a score (supports both trace and session scores)
     */
    public Score getById(String scoreId, RequestOptions requestOptions) {
      HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
        .addPathSegments("api/public/v2")
        .addPathSegments("scores")
        .addPathSegment(scoreId)
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
          return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), Score.class);
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
