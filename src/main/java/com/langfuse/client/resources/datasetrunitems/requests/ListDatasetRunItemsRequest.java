/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.datasetrunitems.requests;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.langfuse.client.core.ObjectMappers;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import com.langfuse.client.resources.datasetrunitems.types.PaginatedDatasetRunItems;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = ListDatasetRunItemsRequest.Builder.class
)
public final class ListDatasetRunItemsRequest {
  private final String datasetId;

  private final String runName;

  private final Optional<Integer> page;

  private final Optional<Integer> limit;

  private final PaginatedDatasetRunItems response;

  private final Map<String, Object> additionalProperties;

  private ListDatasetRunItemsRequest(String datasetId, String runName, Optional<Integer> page,
      Optional<Integer> limit, PaginatedDatasetRunItems response,
      Map<String, Object> additionalProperties) {
    this.datasetId = datasetId;
    this.runName = runName;
    this.page = page;
    this.limit = limit;
    this.response = response;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("datasetId")
  public String getDatasetId() {
    return datasetId;
  }

  @JsonProperty("runName")
  public String getRunName() {
    return runName;
  }

  /**
   * @return page number, starts at 1
   */
  @JsonProperty("page")
  public Optional<Integer> getPage() {
    return page;
  }

  /**
   * @return limit of items per page
   */
  @JsonProperty("limit")
  public Optional<Integer> getLimit() {
    return limit;
  }

  @JsonProperty("response")
  public PaginatedDatasetRunItems getResponse() {
    return response;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof ListDatasetRunItemsRequest && equalTo((ListDatasetRunItemsRequest) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(ListDatasetRunItemsRequest other) {
    return datasetId.equals(other.datasetId) && runName.equals(other.runName) && page.equals(other.page) && limit.equals(other.limit) && response.equals(other.response);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.datasetId, this.runName, this.page, this.limit, this.response);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static DatasetIdStage builder() {
    return new Builder();
  }

  public interface DatasetIdStage {
    RunNameStage datasetId(@NotNull String datasetId);

    Builder from(ListDatasetRunItemsRequest other);
  }

  public interface RunNameStage {
    ResponseStage runName(@NotNull String runName);
  }

  public interface ResponseStage {
    _FinalStage response(@NotNull PaginatedDatasetRunItems response);
  }

  public interface _FinalStage {
    ListDatasetRunItemsRequest build();

    _FinalStage page(Optional<Integer> page);

    _FinalStage page(Integer page);

    _FinalStage limit(Optional<Integer> limit);

    _FinalStage limit(Integer limit);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements DatasetIdStage, RunNameStage, ResponseStage, _FinalStage {
    private String datasetId;

    private String runName;

    private PaginatedDatasetRunItems response;

    private Optional<Integer> limit = Optional.empty();

    private Optional<Integer> page = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(ListDatasetRunItemsRequest other) {
      datasetId(other.getDatasetId());
      runName(other.getRunName());
      page(other.getPage());
      limit(other.getLimit());
      response(other.getResponse());
      return this;
    }

    @java.lang.Override
    @JsonSetter("datasetId")
    public RunNameStage datasetId(@NotNull String datasetId) {
      this.datasetId = Objects.requireNonNull(datasetId, "datasetId must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("runName")
    public ResponseStage runName(@NotNull String runName) {
      this.runName = Objects.requireNonNull(runName, "runName must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("response")
    public _FinalStage response(@NotNull PaginatedDatasetRunItems response) {
      this.response = Objects.requireNonNull(response, "response must not be null");
      return this;
    }

    /**
     * <p>limit of items per page</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage limit(Integer limit) {
      this.limit = Optional.ofNullable(limit);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "limit",
        nulls = Nulls.SKIP
    )
    public _FinalStage limit(Optional<Integer> limit) {
      this.limit = limit;
      return this;
    }

    /**
     * <p>page number, starts at 1</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage page(Integer page) {
      this.page = Optional.ofNullable(page);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "page",
        nulls = Nulls.SKIP
    )
    public _FinalStage page(Optional<Integer> page) {
      this.page = page;
      return this;
    }

    @java.lang.Override
    public ListDatasetRunItemsRequest build() {
      return new ListDatasetRunItemsRequest(datasetId, runName, page, limit, response, additionalProperties);
    }
  }
}
