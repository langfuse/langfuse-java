/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.datasetitems;

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
import com.langfuse.client.resources.commons.types.DatasetItem;
import com.langfuse.client.resources.datasetitems.requests.GetDatasetItemsRequest;
import com.langfuse.client.resources.datasetitems.types.CreateDatasetItemRequest;
import com.langfuse.client.resources.datasetitems.types.DeleteDatasetItemResponse;
import com.langfuse.client.resources.datasetitems.types.PaginatedDatasetItems;

public class DatasetItemsClient {
  protected final ClientOptions clientOptions;

  public DatasetItemsClient(ClientOptions clientOptions) {
    this.clientOptions = clientOptions;
  }

  /**
   * Create a dataset item
   */
  public DatasetItem create(CreateDatasetItemRequest request) {
    return create(request,null);
  }

  /**
   * Create a dataset item
   */
  public DatasetItem create(CreateDatasetItemRequest request, RequestOptions requestOptions) {
    HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
      .addPathSegments("api/public")
      .addPathSegments("dataset-items")
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
        return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), DatasetItem.class);
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
   * Get a dataset item
   */
  public DatasetItem get(String id) {
    return get(id,null);
  }

  /**
   * Get a dataset item
   */
  public DatasetItem get(String id, RequestOptions requestOptions) {
    HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
      .addPathSegments("api/public")
      .addPathSegments("dataset-items")
      .addPathSegment(id)
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
        return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), DatasetItem.class);
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
   * Get dataset items
   */
  public PaginatedDatasetItems list() {
    return list(GetDatasetItemsRequest.builder().build());
  }

  /**
   * Get dataset items
   */
  public PaginatedDatasetItems list(GetDatasetItemsRequest request) {
    return list(request,null);
  }

  /**
   * Get dataset items
   */
  public PaginatedDatasetItems list(GetDatasetItemsRequest request, RequestOptions requestOptions) {
    HttpUrl.Builder httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
      .addPathSegments("api/public")
      .addPathSegments("dataset-items");if (request.getDatasetName().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "datasetName", request.getDatasetName().get(), false);
      }
      if (request.getSourceTraceId().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "sourceTraceId", request.getSourceTraceId().get(), false);
      }
      if (request.getSourceObservationId().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "sourceObservationId", request.getSourceObservationId().get(), false);
      }
      if (request.getPage().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "page", request.getPage().get().toString(), false);
      }
      if (request.getLimit().isPresent()) {
        QueryStringMapper.addQueryParameter(httpUrl, "limit", request.getLimit().get().toString(), false);
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
          return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), PaginatedDatasetItems.class);
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
     * Delete a dataset item and all its run items. This action is irreversible.
     */
    public DeleteDatasetItemResponse delete(String id) {
      return delete(id,null);
    }

    /**
     * Delete a dataset item and all its run items. This action is irreversible.
     */
    public DeleteDatasetItemResponse delete(String id, RequestOptions requestOptions) {
      HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl()).newBuilder()
        .addPathSegments("api/public")
        .addPathSegments("dataset-items")
        .addPathSegment(id)
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
          return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), DeleteDatasetItemResponse.class);
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
