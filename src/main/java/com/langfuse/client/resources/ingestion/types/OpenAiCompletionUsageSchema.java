/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.ingestion.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.langfuse.client.core.ObjectMappers;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = OpenAiCompletionUsageSchema.Builder.class
)
public final class OpenAiCompletionUsageSchema {
  private final int promptTokens;

  private final int completionTokens;

  private final int totalTokens;

  private final Optional<Map<String, Optional<Integer>>> promptTokensDetails;

  private final Optional<Map<String, Optional<Integer>>> completionTokensDetails;

  private final Map<String, Object> additionalProperties;

  private OpenAiCompletionUsageSchema(int promptTokens, int completionTokens, int totalTokens,
      Optional<Map<String, Optional<Integer>>> promptTokensDetails,
      Optional<Map<String, Optional<Integer>>> completionTokensDetails,
      Map<String, Object> additionalProperties) {
    this.promptTokens = promptTokens;
    this.completionTokens = completionTokens;
    this.totalTokens = totalTokens;
    this.promptTokensDetails = promptTokensDetails;
    this.completionTokensDetails = completionTokensDetails;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("prompt_tokens")
  public int getPromptTokens() {
    return promptTokens;
  }

  @JsonProperty("completion_tokens")
  public int getCompletionTokens() {
    return completionTokens;
  }

  @JsonProperty("total_tokens")
  public int getTotalTokens() {
    return totalTokens;
  }

  @JsonProperty("prompt_tokens_details")
  public Optional<Map<String, Optional<Integer>>> getPromptTokensDetails() {
    return promptTokensDetails;
  }

  @JsonProperty("completion_tokens_details")
  public Optional<Map<String, Optional<Integer>>> getCompletionTokensDetails() {
    return completionTokensDetails;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof OpenAiCompletionUsageSchema && equalTo((OpenAiCompletionUsageSchema) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(OpenAiCompletionUsageSchema other) {
    return promptTokens == other.promptTokens && completionTokens == other.completionTokens && totalTokens == other.totalTokens && promptTokensDetails.equals(other.promptTokensDetails) && completionTokensDetails.equals(other.completionTokensDetails);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.promptTokens, this.completionTokens, this.totalTokens, this.promptTokensDetails, this.completionTokensDetails);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static PromptTokensStage builder() {
    return new Builder();
  }

  public interface PromptTokensStage {
    CompletionTokensStage promptTokens(int promptTokens);

    Builder from(OpenAiCompletionUsageSchema other);
  }

  public interface CompletionTokensStage {
    TotalTokensStage completionTokens(int completionTokens);
  }

  public interface TotalTokensStage {
    _FinalStage totalTokens(int totalTokens);
  }

  public interface _FinalStage {
    OpenAiCompletionUsageSchema build();

    _FinalStage promptTokensDetails(Optional<Map<String, Optional<Integer>>> promptTokensDetails);

    _FinalStage promptTokensDetails(Map<String, Optional<Integer>> promptTokensDetails);

    _FinalStage completionTokensDetails(
        Optional<Map<String, Optional<Integer>>> completionTokensDetails);

    _FinalStage completionTokensDetails(Map<String, Optional<Integer>> completionTokensDetails);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements PromptTokensStage, CompletionTokensStage, TotalTokensStage, _FinalStage {
    private int promptTokens;

    private int completionTokens;

    private int totalTokens;

    private Optional<Map<String, Optional<Integer>>> completionTokensDetails = Optional.empty();

    private Optional<Map<String, Optional<Integer>>> promptTokensDetails = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(OpenAiCompletionUsageSchema other) {
      promptTokens(other.getPromptTokens());
      completionTokens(other.getCompletionTokens());
      totalTokens(other.getTotalTokens());
      promptTokensDetails(other.getPromptTokensDetails());
      completionTokensDetails(other.getCompletionTokensDetails());
      return this;
    }

    @java.lang.Override
    @JsonSetter("prompt_tokens")
    public CompletionTokensStage promptTokens(int promptTokens) {
      this.promptTokens = promptTokens;
      return this;
    }

    @java.lang.Override
    @JsonSetter("completion_tokens")
    public TotalTokensStage completionTokens(int completionTokens) {
      this.completionTokens = completionTokens;
      return this;
    }

    @java.lang.Override
    @JsonSetter("total_tokens")
    public _FinalStage totalTokens(int totalTokens) {
      this.totalTokens = totalTokens;
      return this;
    }

    @java.lang.Override
    public _FinalStage completionTokensDetails(
        Map<String, Optional<Integer>> completionTokensDetails) {
      this.completionTokensDetails = Optional.ofNullable(completionTokensDetails);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "completion_tokens_details",
        nulls = Nulls.SKIP
    )
    public _FinalStage completionTokensDetails(
        Optional<Map<String, Optional<Integer>>> completionTokensDetails) {
      this.completionTokensDetails = completionTokensDetails;
      return this;
    }

    @java.lang.Override
    public _FinalStage promptTokensDetails(Map<String, Optional<Integer>> promptTokensDetails) {
      this.promptTokensDetails = Optional.ofNullable(promptTokensDetails);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "prompt_tokens_details",
        nulls = Nulls.SKIP
    )
    public _FinalStage promptTokensDetails(
        Optional<Map<String, Optional<Integer>>> promptTokensDetails) {
      this.promptTokensDetails = promptTokensDetails;
      return this;
    }

    @java.lang.Override
    public OpenAiCompletionUsageSchema build() {
      return new OpenAiCompletionUsageSchema(promptTokens, completionTokens, totalTokens, promptTokensDetails, completionTokensDetails, additionalProperties);
    }
  }
}
