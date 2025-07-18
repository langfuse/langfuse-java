/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.annotationqueues.types;

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
import org.jetbrains.annotations.NotNull;
import com.langfuse.client.resources.utils.pagination.types.MetaResponse;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = PaginatedAnnotationQueueItems.Builder.class
)
public final class PaginatedAnnotationQueueItems {
  private final List<AnnotationQueueItem> data;

  private final MetaResponse meta;

  private final Map<String, Object> additionalProperties;

  private PaginatedAnnotationQueueItems(List<AnnotationQueueItem> data, MetaResponse meta,
      Map<String, Object> additionalProperties) {
    this.data = data;
    this.meta = meta;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("data")
  public List<AnnotationQueueItem> getData() {
    return data;
  }

  @JsonProperty("meta")
  public MetaResponse getMeta() {
    return meta;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof PaginatedAnnotationQueueItems && equalTo((PaginatedAnnotationQueueItems) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(PaginatedAnnotationQueueItems other) {
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

    Builder from(PaginatedAnnotationQueueItems other);
  }

  public interface _FinalStage {
    PaginatedAnnotationQueueItems build();

    _FinalStage data(List<AnnotationQueueItem> data);

    _FinalStage addData(AnnotationQueueItem data);

    _FinalStage addAllData(List<AnnotationQueueItem> data);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements MetaStage, _FinalStage {
    private MetaResponse meta;

    private List<AnnotationQueueItem> data = new ArrayList<>();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(PaginatedAnnotationQueueItems other) {
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
    public _FinalStage addAllData(List<AnnotationQueueItem> data) {
      this.data.addAll(data);
      return this;
    }

    @java.lang.Override
    public _FinalStage addData(AnnotationQueueItem data) {
      this.data.add(data);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "data",
        nulls = Nulls.SKIP
    )
    public _FinalStage data(List<AnnotationQueueItem> data) {
      this.data.clear();
      this.data.addAll(data);
      return this;
    }

    @java.lang.Override
    public PaginatedAnnotationQueueItems build() {
      return new PaginatedAnnotationQueueItems(data, meta, additionalProperties);
    }
  }
}
