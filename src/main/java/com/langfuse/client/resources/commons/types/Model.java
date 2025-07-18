/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.commons.types;

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
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = Model.Builder.class
)
public final class Model {
  private final String id;

  private final String modelName;

  private final String matchPattern;

  private final Optional<OffsetDateTime> startDate;

  private final Optional<ModelUsageUnit> unit;

  private final Optional<Double> inputPrice;

  private final Optional<Double> outputPrice;

  private final Optional<Double> totalPrice;

  private final Optional<String> tokenizerId;

  private final Optional<Object> tokenizerConfig;

  private final boolean isLangfuseManaged;

  private final Map<String, ModelPrice> prices;

  private final Map<String, Object> additionalProperties;

  private Model(String id, String modelName, String matchPattern,
      Optional<OffsetDateTime> startDate, Optional<ModelUsageUnit> unit,
      Optional<Double> inputPrice, Optional<Double> outputPrice, Optional<Double> totalPrice,
      Optional<String> tokenizerId, Optional<Object> tokenizerConfig, boolean isLangfuseManaged,
      Map<String, ModelPrice> prices, Map<String, Object> additionalProperties) {
    this.id = id;
    this.modelName = modelName;
    this.matchPattern = matchPattern;
    this.startDate = startDate;
    this.unit = unit;
    this.inputPrice = inputPrice;
    this.outputPrice = outputPrice;
    this.totalPrice = totalPrice;
    this.tokenizerId = tokenizerId;
    this.tokenizerConfig = tokenizerConfig;
    this.isLangfuseManaged = isLangfuseManaged;
    this.prices = prices;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  /**
   * @return Name of the model definition. If multiple with the same name exist, they are applied in the following order: (1) custom over built-in, (2) newest according to startTime where model.startTime&lt;observation.startTime
   */
  @JsonProperty("modelName")
  public String getModelName() {
    return modelName;
  }

  /**
   * @return Regex pattern which matches this model definition to generation.model. Useful in case of fine-tuned models. If you want to exact match, use <code>(?i)^modelname$</code>
   */
  @JsonProperty("matchPattern")
  public String getMatchPattern() {
    return matchPattern;
  }

  /**
   * @return Apply only to generations which are newer than this ISO date.
   */
  @JsonProperty("startDate")
  public Optional<OffsetDateTime> getStartDate() {
    return startDate;
  }

  /**
   * @return Unit used by this model.
   */
  @JsonProperty("unit")
  public Optional<ModelUsageUnit> getUnit() {
    return unit;
  }

  /**
   * @return Deprecated. See 'prices' instead. Price (USD) per input unit
   */
  @JsonProperty("inputPrice")
  public Optional<Double> getInputPrice() {
    return inputPrice;
  }

  /**
   * @return Deprecated. See 'prices' instead. Price (USD) per output unit
   */
  @JsonProperty("outputPrice")
  public Optional<Double> getOutputPrice() {
    return outputPrice;
  }

  /**
   * @return Deprecated. See 'prices' instead. Price (USD) per total unit. Cannot be set if input or output price is set.
   */
  @JsonProperty("totalPrice")
  public Optional<Double> getTotalPrice() {
    return totalPrice;
  }

  /**
   * @return Optional. Tokenizer to be applied to observations which match to this model. See docs for more details.
   */
  @JsonProperty("tokenizerId")
  public Optional<String> getTokenizerId() {
    return tokenizerId;
  }

  /**
   * @return Optional. Configuration for the selected tokenizer. Needs to be JSON. See docs for more details.
   */
  @JsonProperty("tokenizerConfig")
  public Optional<Object> getTokenizerConfig() {
    return tokenizerConfig;
  }

  @JsonProperty("isLangfuseManaged")
  public boolean getIsLangfuseManaged() {
    return isLangfuseManaged;
  }

  /**
   * @return Price (USD) by usage type
   */
  @JsonProperty("prices")
  public Map<String, ModelPrice> getPrices() {
    return prices;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof Model && equalTo((Model) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(Model other) {
    return id.equals(other.id) && modelName.equals(other.modelName) && matchPattern.equals(other.matchPattern) && startDate.equals(other.startDate) && unit.equals(other.unit) && inputPrice.equals(other.inputPrice) && outputPrice.equals(other.outputPrice) && totalPrice.equals(other.totalPrice) && tokenizerId.equals(other.tokenizerId) && tokenizerConfig.equals(other.tokenizerConfig) && isLangfuseManaged == other.isLangfuseManaged && prices.equals(other.prices);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id, this.modelName, this.matchPattern, this.startDate, this.unit, this.inputPrice, this.outputPrice, this.totalPrice, this.tokenizerId, this.tokenizerConfig, this.isLangfuseManaged, this.prices);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    ModelNameStage id(@NotNull String id);

    Builder from(Model other);
  }

  public interface ModelNameStage {
    MatchPatternStage modelName(@NotNull String modelName);
  }

  public interface MatchPatternStage {
    IsLangfuseManagedStage matchPattern(@NotNull String matchPattern);
  }

  public interface IsLangfuseManagedStage {
    _FinalStage isLangfuseManaged(boolean isLangfuseManaged);
  }

  public interface _FinalStage {
    Model build();

    _FinalStage startDate(Optional<OffsetDateTime> startDate);

    _FinalStage startDate(OffsetDateTime startDate);

    _FinalStage unit(Optional<ModelUsageUnit> unit);

    _FinalStage unit(ModelUsageUnit unit);

    _FinalStage inputPrice(Optional<Double> inputPrice);

    _FinalStage inputPrice(Double inputPrice);

    _FinalStage outputPrice(Optional<Double> outputPrice);

    _FinalStage outputPrice(Double outputPrice);

    _FinalStage totalPrice(Optional<Double> totalPrice);

    _FinalStage totalPrice(Double totalPrice);

    _FinalStage tokenizerId(Optional<String> tokenizerId);

    _FinalStage tokenizerId(String tokenizerId);

    _FinalStage tokenizerConfig(Optional<Object> tokenizerConfig);

    _FinalStage tokenizerConfig(Object tokenizerConfig);

    _FinalStage prices(Map<String, ModelPrice> prices);

    _FinalStage putAllPrices(Map<String, ModelPrice> prices);

    _FinalStage prices(String key, ModelPrice value);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, ModelNameStage, MatchPatternStage, IsLangfuseManagedStage, _FinalStage {
    private String id;

    private String modelName;

    private String matchPattern;

    private boolean isLangfuseManaged;

    private Map<String, ModelPrice> prices = new LinkedHashMap<>();

    private Optional<Object> tokenizerConfig = Optional.empty();

    private Optional<String> tokenizerId = Optional.empty();

    private Optional<Double> totalPrice = Optional.empty();

    private Optional<Double> outputPrice = Optional.empty();

    private Optional<Double> inputPrice = Optional.empty();

    private Optional<ModelUsageUnit> unit = Optional.empty();

    private Optional<OffsetDateTime> startDate = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(Model other) {
      id(other.getId());
      modelName(other.getModelName());
      matchPattern(other.getMatchPattern());
      startDate(other.getStartDate());
      unit(other.getUnit());
      inputPrice(other.getInputPrice());
      outputPrice(other.getOutputPrice());
      totalPrice(other.getTotalPrice());
      tokenizerId(other.getTokenizerId());
      tokenizerConfig(other.getTokenizerConfig());
      isLangfuseManaged(other.getIsLangfuseManaged());
      prices(other.getPrices());
      return this;
    }

    @java.lang.Override
    @JsonSetter("id")
    public ModelNameStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    /**
     * <p>Name of the model definition. If multiple with the same name exist, they are applied in the following order: (1) custom over built-in, (2) newest according to startTime where model.startTime&lt;observation.startTime</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    @JsonSetter("modelName")
    public MatchPatternStage modelName(@NotNull String modelName) {
      this.modelName = Objects.requireNonNull(modelName, "modelName must not be null");
      return this;
    }

    /**
     * <p>Regex pattern which matches this model definition to generation.model. Useful in case of fine-tuned models. If you want to exact match, use <code>(?i)^modelname$</code></p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    @JsonSetter("matchPattern")
    public IsLangfuseManagedStage matchPattern(@NotNull String matchPattern) {
      this.matchPattern = Objects.requireNonNull(matchPattern, "matchPattern must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("isLangfuseManaged")
    public _FinalStage isLangfuseManaged(boolean isLangfuseManaged) {
      this.isLangfuseManaged = isLangfuseManaged;
      return this;
    }

    /**
     * <p>Price (USD) by usage type</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage prices(String key, ModelPrice value) {
      this.prices.put(key, value);
      return this;
    }

    /**
     * <p>Price (USD) by usage type</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage putAllPrices(Map<String, ModelPrice> prices) {
      this.prices.putAll(prices);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "prices",
        nulls = Nulls.SKIP
    )
    public _FinalStage prices(Map<String, ModelPrice> prices) {
      this.prices.clear();
      this.prices.putAll(prices);
      return this;
    }

    /**
     * <p>Optional. Configuration for the selected tokenizer. Needs to be JSON. See docs for more details.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage tokenizerConfig(Object tokenizerConfig) {
      this.tokenizerConfig = Optional.ofNullable(tokenizerConfig);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "tokenizerConfig",
        nulls = Nulls.SKIP
    )
    public _FinalStage tokenizerConfig(Optional<Object> tokenizerConfig) {
      this.tokenizerConfig = tokenizerConfig;
      return this;
    }

    /**
     * <p>Optional. Tokenizer to be applied to observations which match to this model. See docs for more details.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage tokenizerId(String tokenizerId) {
      this.tokenizerId = Optional.ofNullable(tokenizerId);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "tokenizerId",
        nulls = Nulls.SKIP
    )
    public _FinalStage tokenizerId(Optional<String> tokenizerId) {
      this.tokenizerId = tokenizerId;
      return this;
    }

    /**
     * <p>Deprecated. See 'prices' instead. Price (USD) per total unit. Cannot be set if input or output price is set.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage totalPrice(Double totalPrice) {
      this.totalPrice = Optional.ofNullable(totalPrice);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "totalPrice",
        nulls = Nulls.SKIP
    )
    public _FinalStage totalPrice(Optional<Double> totalPrice) {
      this.totalPrice = totalPrice;
      return this;
    }

    /**
     * <p>Deprecated. See 'prices' instead. Price (USD) per output unit</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage outputPrice(Double outputPrice) {
      this.outputPrice = Optional.ofNullable(outputPrice);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "outputPrice",
        nulls = Nulls.SKIP
    )
    public _FinalStage outputPrice(Optional<Double> outputPrice) {
      this.outputPrice = outputPrice;
      return this;
    }

    /**
     * <p>Deprecated. See 'prices' instead. Price (USD) per input unit</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage inputPrice(Double inputPrice) {
      this.inputPrice = Optional.ofNullable(inputPrice);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "inputPrice",
        nulls = Nulls.SKIP
    )
    public _FinalStage inputPrice(Optional<Double> inputPrice) {
      this.inputPrice = inputPrice;
      return this;
    }

    /**
     * <p>Unit used by this model.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage unit(ModelUsageUnit unit) {
      this.unit = Optional.ofNullable(unit);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "unit",
        nulls = Nulls.SKIP
    )
    public _FinalStage unit(Optional<ModelUsageUnit> unit) {
      this.unit = unit;
      return this;
    }

    /**
     * <p>Apply only to generations which are newer than this ISO date.</p>
     * @return Reference to {@code this} so that method calls can be chained together.
     */
    @java.lang.Override
    public _FinalStage startDate(OffsetDateTime startDate) {
      this.startDate = Optional.ofNullable(startDate);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "startDate",
        nulls = Nulls.SKIP
    )
    public _FinalStage startDate(Optional<OffsetDateTime> startDate) {
      this.startDate = startDate;
      return this;
    }

    @java.lang.Override
    public Model build() {
      return new Model(id, modelName, matchPattern, startDate, unit, inputPrice, outputPrice, totalPrice, tokenizerId, tokenizerConfig, isLangfuseManaged, prices, additionalProperties);
    }
  }
}
