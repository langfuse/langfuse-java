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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = BaseEvent.Builder.class
)
public final class BaseEvent implements IBaseEvent {
  private final String id;

  private final String timestamp;

  private final Optional<Object> metadata;

  private final Map<String, Object> additionalProperties;

  private BaseEvent(String id, String timestamp, Optional<Object> metadata,
      Map<String, Object> additionalProperties) {
    this.id = id;
    this.timestamp = timestamp;
    this.metadata = metadata;
    this.additionalProperties = additionalProperties;
  }

  /**
   * @return UUID v4 that identifies the event
   */
  @JsonProperty("id")
  @java.lang.Override
  public String getId() {
    return id;
  }

  /**
   * @return Datetime (ISO 8601) of event creation in client. Should be as close to actual event creation in client as possible, this timestamp will be used for ordering of events in future release. Resolution: milliseconds (required), microseconds (optimal).
   */
  @JsonProperty("timestamp")
  @java.lang.Override
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * @return Optional. Metadata field used by the Langfuse SDKs for debugging.
   */
  @JsonProperty("metadata")
  @java.lang.Override
  public Optional<Object> getMetadata() {
    return metadata;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof BaseEvent && equalTo((BaseEvent) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(BaseEvent other) {
    return id.equals(other.id) && timestamp.equals(other.timestamp) && metadata.equals(other.metadata);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id, this.timestamp, this.metadata);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    TimestampStage id(@NotNull String id);

    Builder from(BaseEvent other);
  }

  public interface TimestampStage {
    _FinalStage timestamp(@NotNull String timestamp);
  }

  public interface _FinalStage {
    BaseEvent build();

    _FinalStage metadata(Optional<Object> metadata);

    _FinalStage metadata(Object metadata);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, TimestampStage, _FinalStage {
    private String id;

    private String timestamp;

    private Optional<Object> metadata = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(BaseEvent other) {
      id(other.getId());
      timestamp(other.getTimestamp());
      metadata(other.getMetadata());
      return this;
    }

    /**
     * <p>UUID v4 that identifies the event</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    @JsonSetter("id")
    public TimestampStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    /**
     * <p>Datetime (ISO 8601) of event creation in client. Should be as close to actual event creation in client as possible, this timestamp will be used for ordering of events in future release. Resolution: milliseconds (required), microseconds (optimal).</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    @JsonSetter("timestamp")
    public _FinalStage timestamp(@NotNull String timestamp) {
      this.timestamp = Objects.requireNonNull(timestamp, "timestamp must not be null");
      return this;
    }

    /**
     * <p>Optional. Metadata field used by the Langfuse SDKs for debugging.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
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
    public BaseEvent build() {
      return new BaseEvent(id, timestamp, metadata, additionalProperties);
    }
  }
}
