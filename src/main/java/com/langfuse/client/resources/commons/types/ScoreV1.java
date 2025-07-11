/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.commons.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Object;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public final class ScoreV1 {
  private final Value value;

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  private ScoreV1(Value value) {
    this.value = value;
  }

  public <T> T visit(Visitor<T> visitor) {
    return value.visit(visitor);
  }

  public static ScoreV1 numeric(NumericScoreV1 value) {
    return new ScoreV1(new NumericValue(value));
  }

  public static ScoreV1 categorical(CategoricalScoreV1 value) {
    return new ScoreV1(new CategoricalValue(value));
  }

  public static ScoreV1 boolean_(BooleanScoreV1 value) {
    return new ScoreV1(new BooleanValue(value));
  }

  public boolean isNumeric() {
    return value instanceof NumericValue;
  }

  public boolean isCategorical() {
    return value instanceof CategoricalValue;
  }

  public boolean isBoolean() {
    return value instanceof BooleanValue;
  }

  public boolean _isUnknown() {
    return value instanceof _UnknownValue;
  }

  public Optional<NumericScoreV1> getNumeric() {
    if (isNumeric()) {
      return Optional.of(((NumericValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<CategoricalScoreV1> getCategorical() {
    if (isCategorical()) {
      return Optional.of(((CategoricalValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<BooleanScoreV1> getBoolean() {
    if (isBoolean()) {
      return Optional.of(((BooleanValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<Object> _getUnknown() {
    if (_isUnknown()) {
      return Optional.of(((_UnknownValue) value).value);
    }
    return Optional.empty();
  }

  @JsonValue
  private Value getValue() {
    return this.value;
  }

  public interface Visitor<T> {
    T visitNumeric(NumericScoreV1 numeric);

    T visitCategorical(CategoricalScoreV1 categorical);

    T visitBoolean(BooleanScoreV1 boolean_);

    T _visitUnknown(Object unknownType);
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "dataType",
      visible = true,
      defaultImpl = _UnknownValue.class
  )
  @JsonSubTypes({
      @JsonSubTypes.Type(NumericValue.class),
      @JsonSubTypes.Type(CategoricalValue.class),
      @JsonSubTypes.Type(BooleanValue.class)
  })
  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  private interface Value {
    <T> T visit(Visitor<T> visitor);
  }

  @JsonTypeName("NUMERIC")
  @JsonIgnoreProperties("dataType")
  private static final class NumericValue implements Value {
    @JsonUnwrapped
    private NumericScoreV1 value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private NumericValue() {
    }

    private NumericValue(NumericScoreV1 value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitNumeric(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof NumericValue && equalTo((NumericValue) other);
    }

    private boolean equalTo(NumericValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "ScoreV1{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("CATEGORICAL")
  @JsonIgnoreProperties("dataType")
  private static final class CategoricalValue implements Value {
    @JsonUnwrapped
    private CategoricalScoreV1 value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private CategoricalValue() {
    }

    private CategoricalValue(CategoricalScoreV1 value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitCategorical(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof CategoricalValue && equalTo((CategoricalValue) other);
    }

    private boolean equalTo(CategoricalValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "ScoreV1{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("BOOLEAN")
  @JsonIgnoreProperties("dataType")
  private static final class BooleanValue implements Value {
    @JsonUnwrapped
    private BooleanScoreV1 value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private BooleanValue() {
    }

    private BooleanValue(BooleanScoreV1 value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitBoolean(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof BooleanValue && equalTo((BooleanValue) other);
    }

    private boolean equalTo(BooleanValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "ScoreV1{" + "value: " + value + "}";
    }
  }

  @JsonIgnoreProperties("dataType")
  private static final class _UnknownValue implements Value {
    private String type;

    @JsonValue
    private Object value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private _UnknownValue(@JsonProperty("value") Object value) {
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor._visitUnknown(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof _UnknownValue && equalTo((_UnknownValue) other);
    }

    private boolean equalTo(_UnknownValue other) {
      return type.equals(other.type) && value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.type, this.value);
    }

    @java.lang.Override
    public String toString() {
      return "ScoreV1{" + "type: " + type + ", value: " + value + "}";
    }
  }
}
