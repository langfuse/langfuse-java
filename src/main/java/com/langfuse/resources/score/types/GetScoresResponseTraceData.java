/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.score.types;

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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = GetScoresResponseTraceData.Builder.class
)
public final class GetScoresResponseTraceData {
  private final Optional<String> userId;

  private final Optional<List<String>> tags;

  private final Optional<String> environment;

  private final Map<String, Object> additionalProperties;

  private GetScoresResponseTraceData(Optional<String> userId, Optional<List<String>> tags,
      Optional<String> environment, Map<String, Object> additionalProperties) {
    this.userId = userId;
    this.tags = tags;
    this.environment = environment;
    this.additionalProperties = additionalProperties;
  }

  /**
   * @return The user ID associated with the trace referenced by score
   */
  @JsonProperty("userId")
  public Optional<String> getUserId() {
    return userId;
  }

  /**
   * @return A list of tags associated with the trace referenced by score
   */
  @JsonProperty("tags")
  public Optional<List<String>> getTags() {
    return tags;
  }

  /**
   * @return The environment of the trace referenced by score
   */
  @JsonProperty("environment")
  public Optional<String> getEnvironment() {
    return environment;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof GetScoresResponseTraceData && equalTo((GetScoresResponseTraceData) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(GetScoresResponseTraceData other) {
    return userId.equals(other.userId) && tags.equals(other.tags) && environment.equals(other.environment);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.userId, this.tags, this.environment);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder {
    private Optional<String> userId = Optional.empty();

    private Optional<List<String>> tags = Optional.empty();

    private Optional<String> environment = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    public Builder from(GetScoresResponseTraceData other) {
      userId(other.getUserId());
      tags(other.getTags());
      environment(other.getEnvironment());
      return this;
    }

    @JsonSetter(
        value = "userId",
        nulls = Nulls.SKIP
    )
    public Builder userId(Optional<String> userId) {
      this.userId = userId;
      return this;
    }

    public Builder userId(String userId) {
      this.userId = Optional.ofNullable(userId);
      return this;
    }

    @JsonSetter(
        value = "tags",
        nulls = Nulls.SKIP
    )
    public Builder tags(Optional<List<String>> tags) {
      this.tags = tags;
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = Optional.ofNullable(tags);
      return this;
    }

    @JsonSetter(
        value = "environment",
        nulls = Nulls.SKIP
    )
    public Builder environment(Optional<String> environment) {
      this.environment = environment;
      return this;
    }

    public Builder environment(String environment) {
      this.environment = Optional.ofNullable(environment);
      return this;
    }

    public GetScoresResponseTraceData build() {
      return new GetScoresResponseTraceData(userId, tags, environment, additionalProperties);
    }
  }
}
