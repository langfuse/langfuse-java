/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.scoreconfigs.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.langfuse.client.core.ObjectMappers;
import java.lang.Double;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import com.langfuse.client.resources.commons.types.ConfigCategory;
import com.langfuse.client.resources.commons.types.ScoreDataType;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = CreateScoreConfigRequest.Builder.class
)
public final class CreateScoreConfigRequest {
  private final String name;

  private final ScoreDataType dataType;

  private final Optional<List<ConfigCategory>> categories;

  private final Optional<Double> minValue;

  private final Optional<Double> maxValue;

  private final Optional<String> description;

  private final Map<String, Object> additionalProperties;

  private CreateScoreConfigRequest(String name, ScoreDataType dataType,
      Optional<List<ConfigCategory>> categories, Optional<Double> minValue,
      Optional<Double> maxValue, Optional<String> description,
      Map<String, Object> additionalProperties) {
    this.name = name;
    this.dataType = dataType;
    this.categories = categories;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.description = description;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("dataType")
  public ScoreDataType getDataType() {
    return dataType;
  }

  /**
   * @return Configure custom categories for categorical scores. Pass a list of objects with <code>label</code> and <code>value</code> properties. Categories are autogenerated for boolean configs and cannot be passed
   */
  @JsonProperty("categories")
  public Optional<List<ConfigCategory>> getCategories() {
    return categories;
  }

  /**
   * @return Configure a minimum value for numerical scores. If not set, the minimum value defaults to -∞
   */
  @JsonProperty("minValue")
  public Optional<Double> getMinValue() {
    return minValue;
  }

  /**
   * @return Configure a maximum value for numerical scores. If not set, the maximum value defaults to +∞
   */
  @JsonProperty("maxValue")
  public Optional<Double> getMaxValue() {
    return maxValue;
  }

  /**
   * @return Description is shown across the Langfuse UI and can be used to e.g. explain the config categories in detail, why a numeric range was set, or provide additional context on config name or usage
   */
  @JsonProperty("description")
  public Optional<String> getDescription() {
    return description;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof CreateScoreConfigRequest && equalTo((CreateScoreConfigRequest) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(CreateScoreConfigRequest other) {
    return name.equals(other.name) && dataType.equals(other.dataType) && categories.equals(other.categories) && minValue.equals(other.minValue) && maxValue.equals(other.maxValue) && description.equals(other.description);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.name, this.dataType, this.categories, this.minValue, this.maxValue, this.description);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static NameStage builder() {
    return new Builder();
  }

  public interface NameStage {
    DataTypeStage name(@NotNull String name);

    Builder from(CreateScoreConfigRequest other);
  }

  public interface DataTypeStage {
    _FinalStage dataType(@NotNull ScoreDataType dataType);
  }

  public interface _FinalStage {
    CreateScoreConfigRequest build();

    _FinalStage categories(Optional<List<ConfigCategory>> categories);

    _FinalStage categories(List<ConfigCategory> categories);

    _FinalStage minValue(Optional<Double> minValue);

    _FinalStage minValue(Double minValue);

    _FinalStage maxValue(Optional<Double> maxValue);

    _FinalStage maxValue(Double maxValue);

    _FinalStage description(Optional<String> description);

    _FinalStage description(String description);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements NameStage, DataTypeStage, _FinalStage {
    private String name;

    private ScoreDataType dataType;

    private Optional<String> description = Optional.empty();

    private Optional<Double> maxValue = Optional.empty();

    private Optional<Double> minValue = Optional.empty();

    private Optional<List<ConfigCategory>> categories = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(CreateScoreConfigRequest other) {
      name(other.getName());
      dataType(other.getDataType());
      categories(other.getCategories());
      minValue(other.getMinValue());
      maxValue(other.getMaxValue());
      description(other.getDescription());
      return this;
    }

    @java.lang.Override
    @JsonSetter("name")
    public DataTypeStage name(@NotNull String name) {
      this.name = Objects.requireNonNull(name, "name must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("dataType")
    public _FinalStage dataType(@NotNull ScoreDataType dataType) {
      this.dataType = Objects.requireNonNull(dataType, "dataType must not be null");
      return this;
    }

    /**
     * <p>Description is shown across the Langfuse UI and can be used to e.g. explain the config categories in detail, why a numeric range was set, or provide additional context on config name or usage</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage description(String description) {
      this.description = Optional.ofNullable(description);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "description",
        nulls = Nulls.SKIP
    )
    public _FinalStage description(Optional<String> description) {
      this.description = description;
      return this;
    }

    /**
     * <p>Configure a maximum value for numerical scores. If not set, the maximum value defaults to +∞</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage maxValue(Double maxValue) {
      this.maxValue = Optional.ofNullable(maxValue);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "maxValue",
        nulls = Nulls.SKIP
    )
    public _FinalStage maxValue(Optional<Double> maxValue) {
      this.maxValue = maxValue;
      return this;
    }

    /**
     * <p>Configure a minimum value for numerical scores. If not set, the minimum value defaults to -∞</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage minValue(Double minValue) {
      this.minValue = Optional.ofNullable(minValue);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "minValue",
        nulls = Nulls.SKIP
    )
    public _FinalStage minValue(Optional<Double> minValue) {
      this.minValue = minValue;
      return this;
    }

    /**
     * <p>Configure custom categories for categorical scores. Pass a list of objects with <code>label</code> and <code>value</code> properties. Categories are autogenerated for boolean configs and cannot be passed</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage categories(List<ConfigCategory> categories) {
      this.categories = Optional.ofNullable(categories);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "categories",
        nulls = Nulls.SKIP
    )
    public _FinalStage categories(Optional<List<ConfigCategory>> categories) {
      this.categories = categories;
      return this;
    }

    @java.lang.Override
    public CreateScoreConfigRequest build() {
      return new CreateScoreConfigRequest(name, dataType, categories, minValue, maxValue, description, additionalProperties);
    }
  }
}
