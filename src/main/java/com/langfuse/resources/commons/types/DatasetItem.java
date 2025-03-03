/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.commons.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import core.ObjectMappers;
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
    builder = DatasetItem.Builder.class
)
public final class DatasetItem {
  private final String id;

  private final DatasetStatus status;

  private final Optional<Object> input;

  private final Optional<Object> expectedOutput;

  private final Optional<Object> metadata;

  private final Optional<String> sourceTraceId;

  private final Optional<String> sourceObservationId;

  private final String datasetId;

  private final String datasetName;

  private final OffsetDateTime createdAt;

  private final OffsetDateTime updatedAt;

  private final Map<String, Object> additionalProperties;

  private DatasetItem(String id, DatasetStatus status, Optional<Object> input,
      Optional<Object> expectedOutput, Optional<Object> metadata, Optional<String> sourceTraceId,
      Optional<String> sourceObservationId, String datasetId, String datasetName,
      OffsetDateTime createdAt, OffsetDateTime updatedAt,
      Map<String, Object> additionalProperties) {
    this.id = id;
    this.status = status;
    this.input = input;
    this.expectedOutput = expectedOutput;
    this.metadata = metadata;
    this.sourceTraceId = sourceTraceId;
    this.sourceObservationId = sourceObservationId;
    this.datasetId = datasetId;
    this.datasetName = datasetName;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("status")
  public DatasetStatus getStatus() {
    return status;
  }

  @JsonProperty("input")
  public Optional<Object> getInput() {
    return input;
  }

  @JsonProperty("expectedOutput")
  public Optional<Object> getExpectedOutput() {
    return expectedOutput;
  }

  @JsonProperty("metadata")
  public Optional<Object> getMetadata() {
    return metadata;
  }

  @JsonProperty("sourceTraceId")
  public Optional<String> getSourceTraceId() {
    return sourceTraceId;
  }

  @JsonProperty("sourceObservationId")
  public Optional<String> getSourceObservationId() {
    return sourceObservationId;
  }

  @JsonProperty("datasetId")
  public String getDatasetId() {
    return datasetId;
  }

  @JsonProperty("datasetName")
  public String getDatasetName() {
    return datasetName;
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
    return other instanceof DatasetItem && equalTo((DatasetItem) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(DatasetItem other) {
    return id.equals(other.id) && status.equals(other.status) && input.equals(other.input) && expectedOutput.equals(other.expectedOutput) && metadata.equals(other.metadata) && sourceTraceId.equals(other.sourceTraceId) && sourceObservationId.equals(other.sourceObservationId) && datasetId.equals(other.datasetId) && datasetName.equals(other.datasetName) && createdAt.equals(other.createdAt) && updatedAt.equals(other.updatedAt);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id, this.status, this.input, this.expectedOutput, this.metadata, this.sourceTraceId, this.sourceObservationId, this.datasetId, this.datasetName, this.createdAt, this.updatedAt);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    StatusStage id(@NotNull String id);

    Builder from(DatasetItem other);
  }

  public interface StatusStage {
    DatasetIdStage status(@NotNull DatasetStatus status);
  }

  public interface DatasetIdStage {
    DatasetNameStage datasetId(@NotNull String datasetId);
  }

  public interface DatasetNameStage {
    CreatedAtStage datasetName(@NotNull String datasetName);
  }

  public interface CreatedAtStage {
    UpdatedAtStage createdAt(@NotNull OffsetDateTime createdAt);
  }

  public interface UpdatedAtStage {
    _FinalStage updatedAt(@NotNull OffsetDateTime updatedAt);
  }

  public interface _FinalStage {
    DatasetItem build();

    _FinalStage input(Optional<Object> input);

    _FinalStage input(Object input);

    _FinalStage expectedOutput(Optional<Object> expectedOutput);

    _FinalStage expectedOutput(Object expectedOutput);

    _FinalStage metadata(Optional<Object> metadata);

    _FinalStage metadata(Object metadata);

    _FinalStage sourceTraceId(Optional<String> sourceTraceId);

    _FinalStage sourceTraceId(String sourceTraceId);

    _FinalStage sourceObservationId(Optional<String> sourceObservationId);

    _FinalStage sourceObservationId(String sourceObservationId);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, StatusStage, DatasetIdStage, DatasetNameStage, CreatedAtStage, UpdatedAtStage, _FinalStage {
    private String id;

    private DatasetStatus status;

    private String datasetId;

    private String datasetName;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private Optional<String> sourceObservationId = Optional.empty();

    private Optional<String> sourceTraceId = Optional.empty();

    private Optional<Object> metadata = Optional.empty();

    private Optional<Object> expectedOutput = Optional.empty();

    private Optional<Object> input = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(DatasetItem other) {
      id(other.getId());
      status(other.getStatus());
      input(other.getInput());
      expectedOutput(other.getExpectedOutput());
      metadata(other.getMetadata());
      sourceTraceId(other.getSourceTraceId());
      sourceObservationId(other.getSourceObservationId());
      datasetId(other.getDatasetId());
      datasetName(other.getDatasetName());
      createdAt(other.getCreatedAt());
      updatedAt(other.getUpdatedAt());
      return this;
    }

    @java.lang.Override
    @JsonSetter("id")
    public StatusStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("status")
    public DatasetIdStage status(@NotNull DatasetStatus status) {
      this.status = Objects.requireNonNull(status, "status must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("datasetId")
    public DatasetNameStage datasetId(@NotNull String datasetId) {
      this.datasetId = Objects.requireNonNull(datasetId, "datasetId must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("datasetName")
    public CreatedAtStage datasetName(@NotNull String datasetName) {
      this.datasetName = Objects.requireNonNull(datasetName, "datasetName must not be null");
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
    public _FinalStage sourceObservationId(String sourceObservationId) {
      this.sourceObservationId = Optional.ofNullable(sourceObservationId);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "sourceObservationId",
        nulls = Nulls.SKIP
    )
    public _FinalStage sourceObservationId(Optional<String> sourceObservationId) {
      this.sourceObservationId = sourceObservationId;
      return this;
    }

    @java.lang.Override
    public _FinalStage sourceTraceId(String sourceTraceId) {
      this.sourceTraceId = Optional.ofNullable(sourceTraceId);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "sourceTraceId",
        nulls = Nulls.SKIP
    )
    public _FinalStage sourceTraceId(Optional<String> sourceTraceId) {
      this.sourceTraceId = sourceTraceId;
      return this;
    }

    @java.lang.Override
    public _FinalStage metadata(Object metadata) {
      this.metadata = Optional.ofNullable(metadata);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "metadata",
        nulls = Nulls.SKIP
    )
    public _FinalStage metadata(Optional<Object> metadata) {
      this.metadata = metadata;
      return this;
    }

    @java.lang.Override
    public _FinalStage expectedOutput(Object expectedOutput) {
      this.expectedOutput = Optional.ofNullable(expectedOutput);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "expectedOutput",
        nulls = Nulls.SKIP
    )
    public _FinalStage expectedOutput(Optional<Object> expectedOutput) {
      this.expectedOutput = expectedOutput;
      return this;
    }

    @java.lang.Override
    public _FinalStage input(Object input) {
      this.input = Optional.ofNullable(input);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "input",
        nulls = Nulls.SKIP
    )
    public _FinalStage input(Optional<Object> input) {
      this.input = input;
      return this;
    }

    @java.lang.Override
    public DatasetItem build() {
      return new DatasetItem(id, status, input, expectedOutput, metadata, sourceTraceId, sourceObservationId, datasetId, datasetName, createdAt, updatedAt, additionalProperties);
    }
  }
}
