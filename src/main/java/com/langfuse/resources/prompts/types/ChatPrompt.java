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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = ChatPrompt.Builder.class
)
public final class ChatPrompt implements IBasePrompt {
  private final String name;

  private final int version;

  private final Object config;

  private final List<String> labels;

  private final List<String> tags;

  private final Optional<String> commitMessage;

  private final List<ChatMessage> prompt;

  private final Map<String, Object> additionalProperties;

  private ChatPrompt(String name, int version, Object config, List<String> labels,
      List<String> tags, Optional<String> commitMessage, List<ChatMessage> prompt,
      Map<String, Object> additionalProperties) {
    this.name = name;
    this.version = version;
    this.config = config;
    this.labels = labels;
    this.tags = tags;
    this.commitMessage = commitMessage;
    this.prompt = prompt;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("name")
  @java.lang.Override
  public String getName() {
    return name;
  }

  @JsonProperty("version")
  @java.lang.Override
  public int getVersion() {
    return version;
  }

  @JsonProperty("config")
  @java.lang.Override
  public Object getConfig() {
    return config;
  }

  /**
   * @return List of deployment labels of this prompt version.
   */
  @JsonProperty("labels")
  @java.lang.Override
  public List<String> getLabels() {
    return labels;
  }

  /**
   * @return List of tags. Used to filter via UI and API. The same across versions of a prompt.
   */
  @JsonProperty("tags")
  @java.lang.Override
  public List<String> getTags() {
    return tags;
  }

  /**
   * @return Commit message for this prompt version.
   */
  @JsonProperty("commitMessage")
  @java.lang.Override
  public Optional<String> getCommitMessage() {
    return commitMessage;
  }

  @JsonProperty("prompt")
  public List<ChatMessage> getPrompt() {
    return prompt;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof ChatPrompt && equalTo((ChatPrompt) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(ChatPrompt other) {
    return name.equals(other.name) && version == other.version && config.equals(other.config) && labels.equals(other.labels) && tags.equals(other.tags) && commitMessage.equals(other.commitMessage) && prompt.equals(other.prompt);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.name, this.version, this.config, this.labels, this.tags, this.commitMessage, this.prompt);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static NameStage builder() {
    return new Builder();
  }

  public interface NameStage {
    VersionStage name(@NotNull String name);

    Builder from(ChatPrompt other);
  }

  public interface VersionStage {
    ConfigStage version(int version);
  }

  public interface ConfigStage {
    _FinalStage config(Object config);
  }

  public interface _FinalStage {
    ChatPrompt build();

    _FinalStage labels(List<String> labels);

    _FinalStage addLabels(String labels);

    _FinalStage addAllLabels(List<String> labels);

    _FinalStage tags(List<String> tags);

    _FinalStage addTags(String tags);

    _FinalStage addAllTags(List<String> tags);

    _FinalStage commitMessage(Optional<String> commitMessage);

    _FinalStage commitMessage(String commitMessage);

    _FinalStage prompt(List<ChatMessage> prompt);

    _FinalStage addPrompt(ChatMessage prompt);

    _FinalStage addAllPrompt(List<ChatMessage> prompt);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements NameStage, VersionStage, ConfigStage, _FinalStage {
    private String name;

    private int version;

    private Object config;

    private List<ChatMessage> prompt = new ArrayList<>();

    private Optional<String> commitMessage = Optional.empty();

    private List<String> tags = new ArrayList<>();

    private List<String> labels = new ArrayList<>();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(ChatPrompt other) {
      name(other.getName());
      version(other.getVersion());
      config(other.getConfig());
      labels(other.getLabels());
      tags(other.getTags());
      commitMessage(other.getCommitMessage());
      prompt(other.getPrompt());
      return this;
    }

    @java.lang.Override
    @JsonSetter("name")
    public VersionStage name(@NotNull String name) {
      this.name = Objects.requireNonNull(name, "name must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("version")
    public ConfigStage version(int version) {
      this.version = version;
      return this;
    }

    @java.lang.Override
    @JsonSetter("config")
    public _FinalStage config(Object config) {
      this.config = config;
      return this;
    }

    @java.lang.Override
    public _FinalStage addAllPrompt(List<ChatMessage> prompt) {
      this.prompt.addAll(prompt);
      return this;
    }

    @java.lang.Override
    public _FinalStage addPrompt(ChatMessage prompt) {
      this.prompt.add(prompt);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "prompt",
        nulls = Nulls.SKIP
    )
    public _FinalStage prompt(List<ChatMessage> prompt) {
      this.prompt.clear();
      this.prompt.addAll(prompt);
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
     * <p>List of tags. Used to filter via UI and API. The same across versions of a prompt.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage addAllTags(List<String> tags) {
      this.tags.addAll(tags);
      return this;
    }

    /**
     * <p>List of tags. Used to filter via UI and API. The same across versions of a prompt.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage addTags(String tags) {
      this.tags.add(tags);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "tags",
        nulls = Nulls.SKIP
    )
    public _FinalStage tags(List<String> tags) {
      this.tags.clear();
      this.tags.addAll(tags);
      return this;
    }

    /**
     * <p>List of deployment labels of this prompt version.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage addAllLabels(List<String> labels) {
      this.labels.addAll(labels);
      return this;
    }

    /**
     * <p>List of deployment labels of this prompt version.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage addLabels(String labels) {
      this.labels.add(labels);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "labels",
        nulls = Nulls.SKIP
    )
    public _FinalStage labels(List<String> labels) {
      this.labels.clear();
      this.labels.addAll(labels);
      return this;
    }

    @java.lang.Override
    public ChatPrompt build() {
      return new ChatPrompt(name, version, config, labels, tags, commitMessage, prompt, additionalProperties);
    }
  }
}
