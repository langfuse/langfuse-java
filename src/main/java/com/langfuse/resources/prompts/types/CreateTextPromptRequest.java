/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.prompts.types;

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
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = CreateTextPromptRequest.Builder.class
)
public final class CreateTextPromptRequest {
  private final String name;

  private final String prompt;

  private final Optional<Object> config;

  private final Optional<List<String>> labels;

  private final Optional<List<String>> tags;

  private final Optional<String> commitMessage;

  private final Map<String, Object> additionalProperties;

  private CreateTextPromptRequest(String name, String prompt, Optional<Object> config,
      Optional<List<String>> labels, Optional<List<String>> tags, Optional<String> commitMessage,
      Map<String, Object> additionalProperties) {
    this.name = name;
    this.prompt = prompt;
    this.config = config;
    this.labels = labels;
    this.tags = tags;
    this.commitMessage = commitMessage;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("prompt")
  public String getPrompt() {
    return prompt;
  }

  @JsonProperty("config")
  public Optional<Object> getConfig() {
    return config;
  }

  /**
   * @return List of deployment labels of this prompt version.
   */
  @JsonProperty("labels")
  public Optional<List<String>> getLabels() {
    return labels;
  }

  /**
   * @return List of tags to apply to all versions of this prompt.
   */
  @JsonProperty("tags")
  public Optional<List<String>> getTags() {
    return tags;
  }

  /**
   * @return Commit message for this prompt version.
   */
  @JsonProperty("commitMessage")
  public Optional<String> getCommitMessage() {
    return commitMessage;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof CreateTextPromptRequest && equalTo((CreateTextPromptRequest) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(CreateTextPromptRequest other) {
    return name.equals(other.name) && prompt.equals(other.prompt) && config.equals(other.config) && labels.equals(other.labels) && tags.equals(other.tags) && commitMessage.equals(other.commitMessage);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.name, this.prompt, this.config, this.labels, this.tags, this.commitMessage);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static NameStage builder() {
    return new Builder();
  }

  public interface NameStage {
    PromptStage name(@NotNull String name);

    Builder from(CreateTextPromptRequest other);
  }

  public interface PromptStage {
    _FinalStage prompt(@NotNull String prompt);
  }

  public interface _FinalStage {
    CreateTextPromptRequest build();

    _FinalStage config(Optional<Object> config);

    _FinalStage config(Object config);

    _FinalStage labels(Optional<List<String>> labels);

    _FinalStage labels(List<String> labels);

    _FinalStage tags(Optional<List<String>> tags);

    _FinalStage tags(List<String> tags);

    _FinalStage commitMessage(Optional<String> commitMessage);

    _FinalStage commitMessage(String commitMessage);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements NameStage, PromptStage, _FinalStage {
    private String name;

    private String prompt;

    private Optional<String> commitMessage = Optional.empty();

    private Optional<List<String>> tags = Optional.empty();

    private Optional<List<String>> labels = Optional.empty();

    private Optional<Object> config = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(CreateTextPromptRequest other) {
      name(other.getName());
      prompt(other.getPrompt());
      config(other.getConfig());
      labels(other.getLabels());
      tags(other.getTags());
      commitMessage(other.getCommitMessage());
      return this;
    }

    @java.lang.Override
    @JsonSetter("name")
    public PromptStage name(@NotNull String name) {
      this.name = Objects.requireNonNull(name, "name must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("prompt")
    public _FinalStage prompt(@NotNull String prompt) {
      this.prompt = Objects.requireNonNull(prompt, "prompt must not be null");
      return this;
    }

    /**
     * <p>Commit message for this prompt version.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage commitMessage(String commitMessage) {
      this.commitMessage = Optional.ofNullable(commitMessage);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "commitMessage",
        nulls = Nulls.SKIP
    )
    public _FinalStage commitMessage(Optional<String> commitMessage) {
      this.commitMessage = commitMessage;
      return this;
    }

    /**
     * <p>List of tags to apply to all versions of this prompt.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage tags(List<String> tags) {
      this.tags = Optional.ofNullable(tags);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "tags",
        nulls = Nulls.SKIP
    )
    public _FinalStage tags(Optional<List<String>> tags) {
      this.tags = tags;
      return this;
    }

    /**
     * <p>List of deployment labels of this prompt version.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage labels(List<String> labels) {
      this.labels = Optional.ofNullable(labels);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "labels",
        nulls = Nulls.SKIP
    )
    public _FinalStage labels(Optional<List<String>> labels) {
      this.labels = labels;
      return this;
    }

    @java.lang.Override
    public _FinalStage config(Object config) {
      this.config = Optional.ofNullable(config);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "config",
        nulls = Nulls.SKIP
    )
    public _FinalStage config(Optional<Object> config) {
      this.config = config;
      return this;
    }

    @java.lang.Override
    public CreateTextPromptRequest build() {
      return new CreateTextPromptRequest(name, prompt, config, labels, tags, commitMessage, additionalProperties);
    }
  }
}
