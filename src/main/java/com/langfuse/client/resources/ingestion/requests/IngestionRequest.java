/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.ingestion.requests;

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
import java.util.Optional;
import com.langfuse.client.resources.ingestion.types.IngestionEvent;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = IngestionRequest.Builder.class
)
public final class IngestionRequest {
  private final List<IngestionEvent> batch;

  private final Optional<Object> metadata;

  private final Map<String, Object> additionalProperties;

  private IngestionRequest(List<IngestionEvent> batch, Optional<Object> metadata,
      Map<String, Object> additionalProperties) {
    this.batch = batch;
    this.metadata = metadata;
    this.additionalProperties = additionalProperties;
  }

  /**
   * @return Batch of tracing events to be ingested. Discriminated by attribute <code>type</code>.
   */
  @JsonProperty("batch")
  public List<IngestionEvent> getBatch() {
    return batch;
  }

  /**
   * @return Optional. Metadata field used by the Langfuse SDKs for debugging.
   */
  @JsonProperty("metadata")
  public Optional<Object> getMetadata() {
    return metadata;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof IngestionRequest && equalTo((IngestionRequest) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(IngestionRequest other) {
    return batch.equals(other.batch) && metadata.equals(other.metadata);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.batch, this.metadata);
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
    private List<IngestionEvent> batch = new ArrayList<>();

    private Optional<Object> metadata = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    public Builder from(IngestionRequest other) {
      batch(other.getBatch());
      metadata(other.getMetadata());
      return this;
    }

    @JsonSetter(
        value = "batch",
        nulls = Nulls.SKIP
    )
    public Builder batch(List<IngestionEvent> batch) {
      this.batch.clear();
      this.batch.addAll(batch);
      return this;
    }

    public Builder addBatch(IngestionEvent batch) {
      this.batch.add(batch);
      return this;
    }

    public Builder addAllBatch(List<IngestionEvent> batch) {
      this.batch.addAll(batch);
      return this;
    }

    @JsonSetter(
        value = "metadata",
        nulls = Nulls.SKIP
    )
    public Builder metadata(Optional<Object> metadata) {
      this.metadata = metadata;
      return this;
    }

    public Builder metadata(Object metadata) {
      this.metadata = Optional.ofNullable(metadata);
      return this;
    }

    public IngestionRequest build() {
      return new IngestionRequest(batch, metadata, additionalProperties);
    }
  }
}
