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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = SessionWithTraces.Builder.class
)
public final class SessionWithTraces implements ISession {
  private final String id;

  private final OffsetDateTime createdAt;

  private final String projectId;

  private final Optional<String> environment;

  private final List<Trace> traces;

  private final Map<String, Object> additionalProperties;

  private SessionWithTraces(String id, OffsetDateTime createdAt, String projectId,
      Optional<String> environment, List<Trace> traces, Map<String, Object> additionalProperties) {
    this.id = id;
    this.createdAt = createdAt;
    this.projectId = projectId;
    this.environment = environment;
    this.traces = traces;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("id")
  @java.lang.Override
  public String getId() {
    return id;
  }

  @JsonProperty("createdAt")
  @java.lang.Override
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("projectId")
  @java.lang.Override
  public String getProjectId() {
    return projectId;
  }

  /**
   * @return The environment from which this session originated.
   */
  @JsonProperty("environment")
  @java.lang.Override
  public Optional<String> getEnvironment() {
    return environment;
  }

  @JsonProperty("traces")
  public List<Trace> getTraces() {
    return traces;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof SessionWithTraces && equalTo((SessionWithTraces) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(SessionWithTraces other) {
    return id.equals(other.id) && createdAt.equals(other.createdAt) && projectId.equals(other.projectId) && environment.equals(other.environment) && traces.equals(other.traces);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id, this.createdAt, this.projectId, this.environment, this.traces);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    CreatedAtStage id(@NotNull String id);

    Builder from(SessionWithTraces other);
  }

  public interface CreatedAtStage {
    ProjectIdStage createdAt(@NotNull OffsetDateTime createdAt);
  }

  public interface ProjectIdStage {
    _FinalStage projectId(@NotNull String projectId);
  }

  public interface _FinalStage {
    SessionWithTraces build();

    _FinalStage environment(Optional<String> environment);

    _FinalStage environment(String environment);

    _FinalStage traces(List<Trace> traces);

    _FinalStage addTraces(Trace traces);

    _FinalStage addAllTraces(List<Trace> traces);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, CreatedAtStage, ProjectIdStage, _FinalStage {
    private String id;

    private OffsetDateTime createdAt;

    private String projectId;

    private List<Trace> traces = new ArrayList<>();

    private Optional<String> environment = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(SessionWithTraces other) {
      id(other.getId());
      createdAt(other.getCreatedAt());
      projectId(other.getProjectId());
      environment(other.getEnvironment());
      traces(other.getTraces());
      return this;
    }

    @java.lang.Override
    @JsonSetter("id")
    public CreatedAtStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("createdAt")
    public ProjectIdStage createdAt(@NotNull OffsetDateTime createdAt) {
      this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("projectId")
    public _FinalStage projectId(@NotNull String projectId) {
      this.projectId = Objects.requireNonNull(projectId, "projectId must not be null");
      return this;
    }

    @java.lang.Override
    public _FinalStage addAllTraces(List<Trace> traces) {
      this.traces.addAll(traces);
      return this;
    }

    @java.lang.Override
    public _FinalStage addTraces(Trace traces) {
      this.traces.add(traces);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "traces",
        nulls = Nulls.SKIP
    )
    public _FinalStage traces(List<Trace> traces) {
      this.traces.clear();
      this.traces.addAll(traces);
      return this;
    }

    /**
     * <p>The environment from which this session originated.</p>
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

    @java.lang.Override
    public SessionWithTraces build() {
      return new SessionWithTraces(id, createdAt, projectId, environment, traces, additionalProperties);
    }
  }
}
