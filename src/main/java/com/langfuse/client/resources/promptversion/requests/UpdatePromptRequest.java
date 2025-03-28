/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.promptversion.requests;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = UpdatePromptRequest.Builder.class
)
public final class UpdatePromptRequest {
  private final List<String> newLabels;

  private final Map<String, Object> additionalProperties;

  private UpdatePromptRequest(List<String> newLabels, Map<String, Object> additionalProperties) {
    this.newLabels = newLabels;
    this.additionalProperties = additionalProperties;
  }

  /**
   * @return New labels for the prompt version. Labels are unique across versions. The &quot;latest&quot; label is reserved and managed by Langfuse.
   */
  @JsonProperty("newLabels")
  public List<String> getNewLabels() {
    return newLabels;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof UpdatePromptRequest && equalTo((UpdatePromptRequest) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(UpdatePromptRequest other) {
    return newLabels.equals(other.newLabels);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.newLabels);
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
    private List<String> newLabels = new ArrayList<>();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    public Builder from(UpdatePromptRequest other) {
      newLabels(other.getNewLabels());
      return this;
    }

    @JsonSetter(
        value = "newLabels",
        nulls = Nulls.SKIP
    )
    public Builder newLabels(List<String> newLabels) {
      this.newLabels.clear();
      this.newLabels.addAll(newLabels);
      return this;
    }

    public Builder addNewLabels(String newLabels) {
      this.newLabels.add(newLabels);
      return this;
    }

    public Builder addAllNewLabels(List<String> newLabels) {
      this.newLabels.addAll(newLabels);
      return this;
    }

    public UpdatePromptRequest build() {
      return new UpdatePromptRequest(newLabels, additionalProperties);
    }
  }
}
