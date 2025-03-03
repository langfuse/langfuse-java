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
    builder = NumericScore.Builder.class
)
public final class NumericScore implements INumericScore, IBaseScore {
  private final double value;

  private final String id;

  private final String traceId;

  private final String name;

  private final ScoreSource source;

  private final Optional<String> observationId;

  private final OffsetDateTime timestamp;

  private final OffsetDateTime createdAt;

  private final OffsetDateTime updatedAt;

  private final Optional<String> authorUserId;

  private final Optional<String> comment;

  private final Optional<String> configId;

  private final Optional<String> queueId;

  private final Optional<String> environment;

  private final Map<String, Object> additionalProperties;

  private NumericScore(double value, String id, String traceId, String name, ScoreSource source,
      Optional<String> observationId, OffsetDateTime timestamp, OffsetDateTime createdAt,
      OffsetDateTime updatedAt, Optional<String> authorUserId, Optional<String> comment,
      Optional<String> configId, Optional<String> queueId, Optional<String> environment,
      Map<String, Object> additionalProperties) {
    this.value = value;
    this.id = id;
    this.traceId = traceId;
    this.name = name;
    this.source = source;
    this.observationId = observationId;
    this.timestamp = timestamp;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.authorUserId = authorUserId;
    this.comment = comment;
    this.configId = configId;
    this.queueId = queueId;
    this.environment = environment;
    this.additionalProperties = additionalProperties;
  }

  /**
   * @return The numeric value of the score
   */
  @JsonProperty("value")
  @java.lang.Override
  public double getValue() {
    return value;
  }

  @JsonProperty("id")
  @java.lang.Override
  public String getId() {
    return id;
  }

  @JsonProperty("traceId")
  @java.lang.Override
  public String getTraceId() {
    return traceId;
  }

  @JsonProperty("name")
  @java.lang.Override
  public String getName() {
    return name;
  }

  @JsonProperty("source")
  @java.lang.Override
  public ScoreSource getSource() {
    return source;
  }

  @JsonProperty("observationId")
  @java.lang.Override
  public Optional<String> getObservationId() {
    return observationId;
  }

  @JsonProperty("timestamp")
  @java.lang.Override
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  @JsonProperty("createdAt")
  @java.lang.Override
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("updatedAt")
  @java.lang.Override
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  @JsonProperty("authorUserId")
  @java.lang.Override
  public Optional<String> getAuthorUserId() {
    return authorUserId;
  }

  @JsonProperty("comment")
  @java.lang.Override
  public Optional<String> getComment() {
    return comment;
  }

  /**
   * @return Reference a score config on a score. When set, config and score name must be equal and value must comply to optionally defined numerical range
   */
  @JsonProperty("configId")
  @java.lang.Override
  public Optional<String> getConfigId() {
    return configId;
  }

  /**
   * @return Reference an annotation queue on a score. Populated if the score was initially created in an annotation queue.
   */
  @JsonProperty("queueId")
  @java.lang.Override
  public Optional<String> getQueueId() {
    return queueId;
  }

  /**
   * @return The environment from which this score originated. Can be any lowercase alphanumeric string with hyphens and underscores that does not start with 'langfuse'.
   */
  @JsonProperty("environment")
  @java.lang.Override
  public Optional<String> getEnvironment() {
    return environment;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof NumericScore && equalTo((NumericScore) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(NumericScore other) {
    return value == other.value && id.equals(other.id) && traceId.equals(other.traceId) && name.equals(other.name) && source.equals(other.source) && observationId.equals(other.observationId) && timestamp.equals(other.timestamp) && createdAt.equals(other.createdAt) && updatedAt.equals(other.updatedAt) && authorUserId.equals(other.authorUserId) && comment.equals(other.comment) && configId.equals(other.configId) && queueId.equals(other.queueId) && environment.equals(other.environment);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.value, this.id, this.traceId, this.name, this.source, this.observationId, this.timestamp, this.createdAt, this.updatedAt, this.authorUserId, this.comment, this.configId, this.queueId, this.environment);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static ValueStage builder() {
    return new Builder();
  }

  public interface ValueStage {
    IdStage value(double value);

    Builder from(NumericScore other);
  }

  public interface IdStage {
    TraceIdStage id(@NotNull String id);
  }

  public interface TraceIdStage {
    NameStage traceId(@NotNull String traceId);
  }

  public interface NameStage {
    SourceStage name(@NotNull String name);
  }

  public interface SourceStage {
    TimestampStage source(@NotNull ScoreSource source);
  }

  public interface TimestampStage {
    CreatedAtStage timestamp(@NotNull OffsetDateTime timestamp);
  }

  public interface CreatedAtStage {
    UpdatedAtStage createdAt(@NotNull OffsetDateTime createdAt);
  }

  public interface UpdatedAtStage {
    _FinalStage updatedAt(@NotNull OffsetDateTime updatedAt);
  }

  public interface _FinalStage {
    NumericScore build();

    _FinalStage observationId(Optional<String> observationId);

    _FinalStage observationId(String observationId);

    _FinalStage authorUserId(Optional<String> authorUserId);

    _FinalStage authorUserId(String authorUserId);

    _FinalStage comment(Optional<String> comment);

    _FinalStage comment(String comment);

    _FinalStage configId(Optional<String> configId);

    _FinalStage configId(String configId);

    _FinalStage queueId(Optional<String> queueId);

    _FinalStage queueId(String queueId);

    _FinalStage environment(Optional<String> environment);

    _FinalStage environment(String environment);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements ValueStage, IdStage, TraceIdStage, NameStage, SourceStage, TimestampStage, CreatedAtStage, UpdatedAtStage, _FinalStage {
    private double value;

    private String id;

    private String traceId;

    private String name;

    private ScoreSource source;

    private OffsetDateTime timestamp;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private Optional<String> environment = Optional.empty();

    private Optional<String> queueId = Optional.empty();

    private Optional<String> configId = Optional.empty();

    private Optional<String> comment = Optional.empty();

    private Optional<String> authorUserId = Optional.empty();

    private Optional<String> observationId = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(NumericScore other) {
      value(other.getValue());
      id(other.getId());
      traceId(other.getTraceId());
      name(other.getName());
      source(other.getSource());
      observationId(other.getObservationId());
      timestamp(other.getTimestamp());
      createdAt(other.getCreatedAt());
      updatedAt(other.getUpdatedAt());
      authorUserId(other.getAuthorUserId());
      comment(other.getComment());
      configId(other.getConfigId());
      queueId(other.getQueueId());
      environment(other.getEnvironment());
      return this;
    }

    /**
     * <p>The numeric value of the score</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    @JsonSetter("value")
    public IdStage value(double value) {
      this.value = value;
      return this;
    }

    @java.lang.Override
    @JsonSetter("id")
    public TraceIdStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("traceId")
    public NameStage traceId(@NotNull String traceId) {
      this.traceId = Objects.requireNonNull(traceId, "traceId must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("name")
    public SourceStage name(@NotNull String name) {
      this.name = Objects.requireNonNull(name, "name must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("source")
    public TimestampStage source(@NotNull ScoreSource source) {
      this.source = Objects.requireNonNull(source, "source must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("timestamp")
    public CreatedAtStage timestamp(@NotNull OffsetDateTime timestamp) {
      this.timestamp = Objects.requireNonNull(timestamp, "timestamp must not be null");
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

    /**
     * <p>The environment from which this score originated. Can be any lowercase alphanumeric string with hyphens and underscores that does not start with 'langfuse'.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
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

    /**
     * <p>Reference an annotation queue on a score. Populated if the score was initially created in an annotation queue.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage queueId(String queueId) {
      this.queueId = Optional.ofNullable(queueId);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "queueId",
        nulls = Nulls.SKIP
    )
    public _FinalStage queueId(Optional<String> queueId) {
      this.queueId = queueId;
      return this;
    }

    /**
     * <p>Reference a score config on a score. When set, config and score name must be equal and value must comply to optionally defined numerical range</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage configId(String configId) {
      this.configId = Optional.ofNullable(configId);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "configId",
        nulls = Nulls.SKIP
    )
    public _FinalStage configId(Optional<String> configId) {
      this.configId = configId;
      return this;
    }

    @java.lang.Override
    public _FinalStage comment(String comment) {
      this.comment = Optional.ofNullable(comment);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "comment",
        nulls = Nulls.SKIP
    )
    public _FinalStage comment(Optional<String> comment) {
      this.comment = comment;
      return this;
    }

    @java.lang.Override
    public _FinalStage authorUserId(String authorUserId) {
      this.authorUserId = Optional.ofNullable(authorUserId);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "authorUserId",
        nulls = Nulls.SKIP
    )
    public _FinalStage authorUserId(Optional<String> authorUserId) {
      this.authorUserId = authorUserId;
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
    public NumericScore build() {
      return new NumericScore(value, id, traceId, name, source, observationId, timestamp, createdAt, updatedAt, authorUserId, comment, configId, queueId, environment, additionalProperties);
    }
  }
}
