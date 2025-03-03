/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.prompts.requests;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import core.ObjectMappers;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = GetPromptRequest.Builder.class
)
public final class GetPromptRequest {
  private final Optional<Integer> version;

  private final Optional<String> label;

  private final Map<String, Object> additionalProperties;

  private GetPromptRequest(Optional<Integer> version, Optional<String> label,
      Map<String, Object> additionalProperties) {
    this.version = version;
    this.label = label;
    this.additionalProperties = additionalProperties;
  }

  /**
   * @return Version of the prompt to be retrieved.
   */
  @JsonProperty("version")
  public Optional<Integer> getVersion() {
    return version;
  }

  /**
   * @return Label of the prompt to be retrieved. Defaults to &quot;production&quot; if no label or version is set.
   */
  @JsonProperty("label")
  public Optional<String> getLabel() {
    return label;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof GetPromptRequest && equalTo((GetPromptRequest) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(GetPromptRequest other) {
    return version.equals(other.version) && label.equals(other.label);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.version, this.label);
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
    private Optional<Integer> version = Optional.empty();

    private Optional<String> label = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    public Builder from(GetPromptRequest other) {
      version(other.getVersion());
      label(other.getLabel());
      return this;
    }

    @JsonSetter(
        value = "version",
        nulls = Nulls.SKIP
    )
    public Builder version(Optional<Integer> version) {
      this.version = version;
      return this;
    }

    public Builder version(Integer version) {
      this.version = Optional.ofNullable(version);
      return this;
    }

    @JsonSetter(
        value = "label",
        nulls = Nulls.SKIP
    )
    public Builder label(Optional<String> label) {
      this.label = label;
      return this;
    }

    public Builder label(String label) {
      this.label = Optional.ofNullable(label);
      return this;
    }

    public GetPromptRequest build() {
      return new GetPromptRequest(version, label, additionalProperties);
    }
  }
}
