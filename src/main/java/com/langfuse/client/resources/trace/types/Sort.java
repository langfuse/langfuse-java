/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.trace.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.langfuse.client.core.ObjectMappers;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = Sort.Builder.class
)
public final class Sort {
  private final String id;

  private final Map<String, Object> additionalProperties;

  private Sort(String id, Map<String, Object> additionalProperties) {
    this.id = id;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof Sort && equalTo((Sort) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(Sort other) {
    return id.equals(other.id);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id);
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

    Builder from(Sort other);
  }

  public interface _FinalStage {
    Sort build();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, _FinalStage {
    private String id;

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(Sort other) {
      id(other.getId());
      return this;
    }

    @java.lang.Override
    @JsonSetter("id")
    public _FinalStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    @java.lang.Override
    public Sort build() {
      return new Sort(id, additionalProperties);
    }
  }
}
