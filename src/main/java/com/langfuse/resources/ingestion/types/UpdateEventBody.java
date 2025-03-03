/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.ingestion.types;

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
import resources.commons.types.ObservationLevel;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = UpdateEventBody.Builder.class
)
public final class UpdateEventBody implements IUpdateEventBody, IOptionalObservationBody {
  private final String id;

  private final Optional<String> traceId;

  private final Optional<String> name;

  private final Optional<OffsetDateTime> startTime;

  private final Optional<Object> metadata;

  private final Optional<Object> input;

  private final Optional<Object> output;

  private final Optional<ObservationLevel> level;

  private final Optional<String> statusMessage;

  private final Optional<String> parentObservationId;

  private final Optional<String> version;

  private final Optional<String> environment;

  private final Map<String, Object> additionalProperties;

  private UpdateEventBody(String id, Optional<String> traceId, Optional<String> name,
      Optional<OffsetDateTime> startTime, Optional<Object> metadata, Optional<Object> input,
      Optional<Object> output, Optional<ObservationLevel> level, Optional<String> statusMessage,
      Optional<String> parentObservationId, Optional<String> version, Optional<String> environment,
      Map<String, Object> additionalProperties) {
    this.id = id;
    this.traceId = traceId;
    this.name = name;
    this.startTime = startTime;
    this.metadata = metadata;
    this.input = input;
    this.output = output;
    this.level = level;
    this.statusMessage = statusMessage;
    this.parentObservationId = parentObservationId;
    this.version = version;
    this.environment = environment;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("id")
  @java.lang.Override
  public String getId() {
    return id;
  }

  @JsonProperty("traceId")
  @java.lang.Override
  public Optional<String> getTraceId() {
    return traceId;
  }

  @JsonProperty("name")
  @java.lang.Override
  public Optional<String> getName() {
    return name;
  }

  @JsonProperty("startTime")
  @java.lang.Override
  public Optional<OffsetDateTime> getStartTime() {
    return startTime;
  }

  @JsonProperty("metadata")
  @java.lang.Override
  public Optional<Object> getMetadata() {
    return metadata;
  }

  @JsonProperty("input")
  @java.lang.Override
  public Optional<Object> getInput() {
    return input;
  }

  @JsonProperty("output")
  @java.lang.Override
  public Optional<Object> getOutput() {
    return output;
  }

  @JsonProperty("level")
  @java.lang.Override
  public Optional<ObservationLevel> getLevel() {
    return level;
  }

  @JsonProperty("statusMessage")
  @java.lang.Override
  public Optional<String> getStatusMessage() {
    return statusMessage;
  }

  @JsonProperty("parentObservationId")
  @java.lang.Override
  public Optional<String> getParentObservationId() {
    return parentObservationId;
  }

  @JsonProperty("version")
  @java.lang.Override
  public Optional<String> getVersion() {
    return version;
  }

  @JsonProperty("environment")
  @java.lang.Override
  public Optional<String> getEnvironment() {
    return environment;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof UpdateEventBody && equalTo((UpdateEventBody) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(UpdateEventBody other) {
    return id.equals(other.id) && traceId.equals(other.traceId) && name.equals(other.name) && startTime.equals(other.startTime) && metadata.equals(other.metadata) && input.equals(other.input) && output.equals(other.output) && level.equals(other.level) && statusMessage.equals(other.statusMessage) && parentObservationId.equals(other.parentObservationId) && version.equals(other.version) && environment.equals(other.environment);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id, this.traceId, this.name, this.startTime, this.metadata, this.input, this.output, this.level, this.statusMessage, this.parentObservationId, this.version, this.environment);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    _FinalStage id(@NotNull String id);

    Builder from(UpdateEventBody other);
  }

  public interface _FinalStage {
    UpdateEventBody build();

    _FinalStage traceId(Optional<String> traceId);

    _FinalStage traceId(String traceId);

    _FinalStage name(Optional<String> name);

    _FinalStage name(String name);

    _FinalStage startTime(Optional<OffsetDateTime> startTime);

    _FinalStage startTime(OffsetDateTime startTime);

    _FinalStage metadata(Optional<Object> metadata);

    _FinalStage metadata(Object metadata);

    _FinalStage input(Optional<Object> input);

    _FinalStage input(Object input);

    _FinalStage output(Optional<Object> output);

    _FinalStage output(Object output);

    _FinalStage level(Optional<ObservationLevel> level);

    _FinalStage level(ObservationLevel level);

    _FinalStage statusMessage(Optional<String> statusMessage);

    _FinalStage statusMessage(String statusMessage);

    _FinalStage parentObservationId(Optional<String> parentObservationId);

    _FinalStage parentObservationId(String parentObservationId);

    _FinalStage version(Optional<String> version);

    _FinalStage version(String version);

    _FinalStage environment(Optional<String> environment);

    _FinalStage environment(String environment);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, _FinalStage {
    private String id;

    private Optional<String> environment = Optional.empty();

    private Optional<String> version = Optional.empty();

    private Optional<String> parentObservationId = Optional.empty();

    private Optional<String> statusMessage = Optional.empty();

    private Optional<ObservationLevel> level = Optional.empty();

    private Optional<Object> output = Optional.empty();

    private Optional<Object> input = Optional.empty();

    private Optional<Object> metadata = Optional.empty();

    private Optional<OffsetDateTime> startTime = Optional.empty();

    private Optional<String> name = Optional.empty();

    private Optional<String> traceId = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(UpdateEventBody other) {
      id(other.getId());
      traceId(other.getTraceId());
      name(other.getName());
      startTime(other.getStartTime());
      metadata(other.getMetadata());
      input(other.getInput());
      output(other.getOutput());
      level(other.getLevel());
      statusMessage(other.getStatusMessage());
      parentObservationId(other.getParentObservationId());
      version(other.getVersion());
      environment(other.getEnvironment());
      return this;
    }

    @java.lang.Override
    @JsonSetter("id")
    public _FinalStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    @java.lang.Override
    public _FinalStage environment(String environment) {
      this.environment = Optional.ofNullable(environment);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "environment",
        nulls = Nulls.SKIP
    )
    public _FinalStage environment(Optional<String> environment) {
      this.environment = environment;
      return this;
    }

    @java.lang.Override
    public _FinalStage version(String version) {
      this.version = Optional.ofNullable(version);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "version",
        nulls = Nulls.SKIP
    )
    public _FinalStage version(Optional<String> version) {
      this.version = version;
      return this;
    }

    @java.lang.Override
    public _FinalStage parentObservationId(String parentObservationId) {
      this.parentObservationId = Optional.ofNullable(parentObservationId);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "parentObservationId",
        nulls = Nulls.SKIP
    )
    public _FinalStage parentObservationId(Optional<String> parentObservationId) {
      this.parentObservationId = parentObservationId;
      return this;
    }

    @java.lang.Override
    public _FinalStage statusMessage(String statusMessage) {
      this.statusMessage = Optional.ofNullable(statusMessage);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "statusMessage",
        nulls = Nulls.SKIP
    )
    public _FinalStage statusMessage(Optional<String> statusMessage) {
      this.statusMessage = statusMessage;
      return this;
    }

    @java.lang.Override
    public _FinalStage level(ObservationLevel level) {
      this.level = Optional.ofNullable(level);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "level",
        nulls = Nulls.SKIP
    )
    public _FinalStage level(Optional<ObservationLevel> level) {
      this.level = level;
      return this;
    }

    @java.lang.Override
    public _FinalStage output(Object output) {
      this.output = Optional.ofNullable(output);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "output",
        nulls = Nulls.SKIP
    )
    public _FinalStage output(Optional<Object> output) {
      this.output = output;
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
    public _FinalStage startTime(OffsetDateTime startTime) {
      this.startTime = Optional.ofNullable(startTime);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "startTime",
        nulls = Nulls.SKIP
    )
    public _FinalStage startTime(Optional<OffsetDateTime> startTime) {
      this.startTime = startTime;
      return this;
    }

    @java.lang.Override
    public _FinalStage name(String name) {
      this.name = Optional.ofNullable(name);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "name",
        nulls = Nulls.SKIP
    )
    public _FinalStage name(Optional<String> name) {
      this.name = name;
      return this;
    }

    @java.lang.Override
    public _FinalStage traceId(String traceId) {
      this.traceId = Optional.ofNullable(traceId);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "traceId",
        nulls = Nulls.SKIP
    )
    public _FinalStage traceId(Optional<String> traceId) {
      this.traceId = traceId;
      return this;
    }

    @java.lang.Override
    public UpdateEventBody build() {
      return new UpdateEventBody(id, traceId, name, startTime, metadata, input, output, level, statusMessage, parentObservationId, version, environment, additionalProperties);
    }
  }
}
