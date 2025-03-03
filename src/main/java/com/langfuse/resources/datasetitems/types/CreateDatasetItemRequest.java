/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.datasetitems.types;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import resources.commons.types.DatasetStatus;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = CreateDatasetItemRequest.Builder.class
)
public final class CreateDatasetItemRequest {
  private final String datasetName;

  private final Optional<Object> input;

  private final Optional<Object> expectedOutput;

  private final Optional<Object> metadata;

  private final Optional<String> sourceTraceId;

  private final Optional<String> sourceObservationId;

  private final Optional<String> id;

  private final Optional<DatasetStatus> status;

  private final Map<String, Object> additionalProperties;

  private CreateDatasetItemRequest(String datasetName, Optional<Object> input,
      Optional<Object> expectedOutput, Optional<Object> metadata, Optional<String> sourceTraceId,
      Optional<String> sourceObservationId, Optional<String> id, Optional<DatasetStatus> status,
      Map<String, Object> additionalProperties) {
    this.datasetName = datasetName;
    this.input = input;
    this.expectedOutput = expectedOutput;
    this.metadata = metadata;
    this.sourceTraceId = sourceTraceId;
    this.sourceObservationId = sourceObservationId;
    this.id = id;
    this.status = status;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("datasetName")
  public String getDatasetName() {
    return datasetName;
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

  /**
   * @return Dataset items are upserted on their id. Id needs to be unique (project-level) and cannot be reused across datasets.
   */
  @JsonProperty("id")
  public Optional<String> getId() {
    return id;
  }

  /**
   * @return Defaults to ACTIVE for newly created items
   */
  @JsonProperty("status")
  public Optional<DatasetStatus> getStatus() {
    return status;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof CreateDatasetItemRequest && equalTo((CreateDatasetItemRequest) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(CreateDatasetItemRequest other) {
    return datasetName.equals(other.datasetName) && input.equals(other.input) && expectedOutput.equals(other.expectedOutput) && metadata.equals(other.metadata) && sourceTraceId.equals(other.sourceTraceId) && sourceObservationId.equals(other.sourceObservationId) && id.equals(other.id) && status.equals(other.status);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.datasetName, this.input, this.expectedOutput, this.metadata, this.sourceTraceId, this.sourceObservationId, this.id, this.status);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static DatasetNameStage builder() {
    return new Builder();
  }

  public interface DatasetNameStage {
    _FinalStage datasetName(@NotNull String datasetName);

    Builder from(CreateDatasetItemRequest other);
  }

  public interface _FinalStage {
    CreateDatasetItemRequest build();

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

    _FinalStage id(Optional<String> id);

    _FinalStage id(String id);

    _FinalStage status(Optional<DatasetStatus> status);

    _FinalStage status(DatasetStatus status);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements DatasetNameStage, _FinalStage {
    private String datasetName;

    private Optional<DatasetStatus> status = Optional.empty();

    private Optional<String> id = Optional.empty();

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
    public Builder from(CreateDatasetItemRequest other) {
      datasetName(other.getDatasetName());
      input(other.getInput());
      expectedOutput(other.getExpectedOutput());
      metadata(other.getMetadata());
      sourceTraceId(other.getSourceTraceId());
      sourceObservationId(other.getSourceObservationId());
      id(other.getId());
      status(other.getStatus());
      return this;
    }

    @java.lang.Override
    @JsonSetter("datasetName")
    public _FinalStage datasetName(@NotNull String datasetName) {
      this.datasetName = Objects.requireNonNull(datasetName, "datasetName must not be null");
      return this;
    }

    /**
     * <p>Defaults to ACTIVE for newly created items</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage status(DatasetStatus status) {
      this.status = Optional.ofNullable(status);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "status",
        nulls = Nulls.SKIP
    )
    public _FinalStage status(Optional<DatasetStatus> status) {
      this.status = status;
      return this;
    }

    /**
     * <p>Dataset items are upserted on their id. Id needs to be unique (project-level) and cannot be reused across datasets.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage id(String id) {
      this.id = Optional.ofNullable(id);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "id",
        nulls = Nulls.SKIP
    )
    public _FinalStage id(Optional<String> id) {
      this.id = id;
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
    public CreateDatasetItemRequest build() {
      return new CreateDatasetItemRequest(datasetName, input, expectedOutput, metadata, sourceTraceId, sourceObservationId, id, status, additionalProperties);
    }
  }
}
