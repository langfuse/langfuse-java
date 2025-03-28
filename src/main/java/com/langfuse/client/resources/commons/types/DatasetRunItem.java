/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.commons.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.langfuse.client.core.ObjectMappers;
import java.lang.Object;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = DatasetRunItem.Builder.class
)
public final class DatasetRunItem {
  private final String id;

  private final String datasetRunId;

  private final String datasetRunName;

  private final String datasetItemId;

  private final String traceId;

  private final Optional<String> observationId;

  private final OffsetDateTime createdAt;

  private final OffsetDateTime updatedAt;

  private final Map<String, Object> additionalProperties;

  private DatasetRunItem(String id, String datasetRunId, String datasetRunName,
      String datasetItemId, String traceId, Optional<String> observationId,
      OffsetDateTime createdAt, OffsetDateTime updatedAt,
      Map<String, Object> additionalProperties) {
    this.id = id;
    this.datasetRunId = datasetRunId;
    this.datasetRunName = datasetRunName;
    this.datasetItemId = datasetItemId;
    this.traceId = traceId;
    this.observationId = observationId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("datasetRunId")
  public String getDatasetRunId() {
    return datasetRunId;
  }

  @JsonProperty("datasetRunName")
  public String getDatasetRunName() {
    return datasetRunName;
  }

  @JsonProperty("datasetItemId")
  public String getDatasetItemId() {
    return datasetItemId;
  }

  @JsonProperty("traceId")
  public String getTraceId() {
    return traceId;
  }

  @JsonProperty("observationId")
  public Optional<String> getObservationId() {
    return observationId;
  }

  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof DatasetRunItem && equalTo((DatasetRunItem) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(DatasetRunItem other) {
    return id.equals(other.id) && datasetRunId.equals(other.datasetRunId) && datasetRunName.equals(other.datasetRunName) && datasetItemId.equals(other.datasetItemId) && traceId.equals(other.traceId) && observationId.equals(other.observationId) && createdAt.equals(other.createdAt) && updatedAt.equals(other.updatedAt);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id, this.datasetRunId, this.datasetRunName, this.datasetItemId, this.traceId, this.observationId, this.createdAt, this.updatedAt);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    DatasetRunIdStage id(@NotNull String id);

    Builder from(DatasetRunItem other);
  }

  public interface DatasetRunIdStage {
    DatasetRunNameStage datasetRunId(@NotNull String datasetRunId);
  }

  public interface DatasetRunNameStage {
    DatasetItemIdStage datasetRunName(@NotNull String datasetRunName);
  }

  public interface DatasetItemIdStage {
    TraceIdStage datasetItemId(@NotNull String datasetItemId);
  }

  public interface TraceIdStage {
    CreatedAtStage traceId(@NotNull String traceId);
  }

  public interface CreatedAtStage {
    UpdatedAtStage createdAt(@NotNull OffsetDateTime createdAt);
  }

  public interface UpdatedAtStage {
    _FinalStage updatedAt(@NotNull OffsetDateTime updatedAt);
  }

  public interface _FinalStage {
    DatasetRunItem build();

    _FinalStage observationId(Optional<String> observationId);

    _FinalStage observationId(String observationId);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, DatasetRunIdStage, DatasetRunNameStage, DatasetItemIdStage, TraceIdStage, CreatedAtStage, UpdatedAtStage, _FinalStage {
    private String id;

    private String datasetRunId;

    private String datasetRunName;

    private String datasetItemId;

    private String traceId;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private Optional<String> observationId = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(DatasetRunItem other) {
      id(other.getId());
      datasetRunId(other.getDatasetRunId());
      datasetRunName(other.getDatasetRunName());
      datasetItemId(other.getDatasetItemId());
      traceId(other.getTraceId());
      observationId(other.getObservationId());
      createdAt(other.getCreatedAt());
      updatedAt(other.getUpdatedAt());
      return this;
    }

    @java.lang.Override
    @JsonSetter("id")
    public DatasetRunIdStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("datasetRunId")
    public DatasetRunNameStage datasetRunId(@NotNull String datasetRunId) {
      this.datasetRunId = Objects.requireNonNull(datasetRunId, "datasetRunId must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("datasetRunName")
    public DatasetItemIdStage datasetRunName(@NotNull String datasetRunName) {
      this.datasetRunName = Objects.requireNonNull(datasetRunName, "datasetRunName must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("datasetItemId")
    public TraceIdStage datasetItemId(@NotNull String datasetItemId) {
      this.datasetItemId = Objects.requireNonNull(datasetItemId, "datasetItemId must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("traceId")
    public CreatedAtStage traceId(@NotNull String traceId) {
      this.traceId = Objects.requireNonNull(traceId, "traceId must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("createdAt")
    public UpdatedAtStage createdAt(@NotNull OffsetDateTime createdAt) {
      this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("updatedAt")
    public _FinalStage updatedAt(@NotNull OffsetDateTime updatedAt) {
      this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
      return this;
    }

    @java.lang.Override
    public _FinalStage observationId(String observationId) {
      this.observationId = Optional.ofNullable(observationId);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "observationId",
        nulls = Nulls.SKIP
    )
    public _FinalStage observationId(Optional<String> observationId) {
      this.observationId = observationId;
      return this;
    }

    @java.lang.Override
    public DatasetRunItem build() {
      return new DatasetRunItem(id, datasetRunId, datasetRunName, datasetItemId, traceId, observationId, createdAt, updatedAt, additionalProperties);
    }
  }
}
