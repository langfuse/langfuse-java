/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.scim.types;

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

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = SchemaResource.Builder.class
)
public final class SchemaResource {
  private final String id;

  private final String name;

  private final String description;

  private final List<Object> attributes;

  private final ResourceMeta meta;

  private final Map<String, Object> additionalProperties;

  private SchemaResource(String id, String name, String description, List<Object> attributes,
      ResourceMeta meta, Map<String, Object> additionalProperties) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.attributes = attributes;
    this.meta = meta;
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

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  @JsonProperty("attributes")
  public List<Object> getAttributes() {
    return attributes;
  }

  @JsonProperty("meta")
  public ResourceMeta getMeta() {
    return meta;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof SchemaResource && equalTo((SchemaResource) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(SchemaResource other) {
    return id.equals(other.id) && name.equals(other.name) && description.equals(other.description) && attributes.equals(other.attributes) && meta.equals(other.meta);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.description, this.attributes, this.meta);
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

    Builder from(SchemaResource other);
  }

  public interface NameStage {
    DescriptionStage name(@NotNull String name);
  }

  public interface DescriptionStage {
    MetaStage description(@NotNull String description);
  }

  public interface MetaStage {
    _FinalStage meta(@NotNull ResourceMeta meta);
  }

  public interface _FinalStage {
    SchemaResource build();

    _FinalStage attributes(List<Object> attributes);

    _FinalStage addAttributes(Object attributes);

    _FinalStage addAllAttributes(List<Object> attributes);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, NameStage, DescriptionStage, MetaStage, _FinalStage {
    private String id;

    private String name;

    private String description;

    private ResourceMeta meta;

    private List<Object> attributes = new ArrayList<>();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(SchemaResource other) {
      id(other.getId());
      name(other.getName());
      description(other.getDescription());
      attributes(other.getAttributes());
      meta(other.getMeta());
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
    public DescriptionStage name(@NotNull String name) {
      this.name = Objects.requireNonNull(name, "name must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("description")
    public MetaStage description(@NotNull String description) {
      this.description = Objects.requireNonNull(description, "description must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("meta")
    public _FinalStage meta(@NotNull ResourceMeta meta) {
      this.meta = Objects.requireNonNull(meta, "meta must not be null");
      return this;
    }

    @java.lang.Override
    public _FinalStage addAllAttributes(List<Object> attributes) {
      this.attributes.addAll(attributes);
      return this;
    }

    @java.lang.Override
    public _FinalStage addAttributes(Object attributes) {
      this.attributes.add(attributes);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "attributes",
        nulls = Nulls.SKIP
    )
    public _FinalStage attributes(List<Object> attributes) {
      this.attributes.clear();
      this.attributes.addAll(attributes);
      return this;
    }

    @java.lang.Override
    public SchemaResource build() {
      return new SchemaResource(id, name, description, attributes, meta, additionalProperties);
    }
  }
}
