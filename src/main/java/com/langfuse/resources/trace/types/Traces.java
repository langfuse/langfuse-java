/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.trace.types;

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
import org.jetbrains.annotations.NotNull;
import resources.commons.types.TraceWithDetails;
import resources.utils.pagination.types.MetaResponse;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = Traces.Builder.class
)
public final class Traces {
  private final List<TraceWithDetails> data;

  private final MetaResponse meta;

  private final Map<String, Object> additionalProperties;

  private Traces(List<TraceWithDetails> data, MetaResponse meta,
      Map<String, Object> additionalProperties) {
    this.data = data;
    this.meta = meta;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("data")
  public List<TraceWithDetails> getData() {
    return data;
  }

  @JsonProperty("meta")
  public MetaResponse getMeta() {
    return meta;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof Traces && equalTo((Traces) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(Traces other) {
    return data.equals(other.data) && meta.equals(other.meta);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.data, this.meta);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static MetaStage builder() {
    return new Builder();
  }

  public interface MetaStage {
    _FinalStage meta(@NotNull MetaResponse meta);

    Builder from(Traces other);
  }

  public interface _FinalStage {
    Traces build();

    _FinalStage data(List<TraceWithDetails> data);

    _FinalStage addData(TraceWithDetails data);

    _FinalStage addAllData(List<TraceWithDetails> data);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements MetaStage, _FinalStage {
    private MetaResponse meta;

    private List<TraceWithDetails> data = new ArrayList<>();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(Traces other) {
      data(other.getData());
      meta(other.getMeta());
      return this;
    }

    @java.lang.Override
    @JsonSetter("meta")
    public _FinalStage meta(@NotNull MetaResponse meta) {
      this.meta = Objects.requireNonNull(meta, "meta must not be null");
      return this;
    }

    @java.lang.Override
    public _FinalStage addAllData(List<TraceWithDetails> data) {
      this.data.addAll(data);
      return this;
    }

    @java.lang.Override
    public _FinalStage addData(TraceWithDetails data) {
      this.data.add(data);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "data",
        nulls = Nulls.SKIP
    )
    public _FinalStage data(List<TraceWithDetails> data) {
      this.data.clear();
      this.data.addAll(data);
      return this;
    }

    @java.lang.Override
    public Traces build() {
      return new Traces(data, meta, additionalProperties);
    }
  }
}
