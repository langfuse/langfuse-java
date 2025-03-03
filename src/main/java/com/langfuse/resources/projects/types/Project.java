/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.projects.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import core.ObjectMappers;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = Project.Builder.class
)
public final class Project {
  private final String id;

  private final String name;

  private final Map<String, Object> additionalProperties;

  private Project(String id, String name, Map<String, Object> additionalProperties) {
    this.id = id;
    this.name = name;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof Project && equalTo((Project) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(Project other) {
    return id.equals(other.id) && name.equals(other.name);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    NameStage id(@NotNull String id);

    Builder from(Project other);
  }

  public interface NameStage {
    _FinalStage name(@NotNull String name);
  }

  public interface _FinalStage {
    Project build();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, NameStage, _FinalStage {
    private String id;

    private String name;

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(Project other) {
      id(other.getId());
      name(other.getName());
      return this;
    }

    @java.lang.Override
    @JsonSetter("id")
    public NameStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("name")
    public _FinalStage name(@NotNull String name) {
      this.name = Objects.requireNonNull(name, "name must not be null");
      return this;
    }

    @java.lang.Override
    public Project build() {
      return new Project(id, name, additionalProperties);
    }
  }
}
