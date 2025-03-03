/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.ingestion.types;

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

public final class IngestionEvent {
  private final Value value;

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  private IngestionEvent(Value value) {
    this.value = value;
  }

  public <T> T visit(Visitor<T> visitor) {
    return value.visit(visitor);
  }

  public static IngestionEvent traceCreate(TraceEvent value) {
    return new IngestionEvent(new TraceCreateValue(value));
  }

  public static IngestionEvent scoreCreate(ScoreEvent value) {
    return new IngestionEvent(new ScoreCreateValue(value));
  }

  public static IngestionEvent spanCreate(CreateSpanEvent value) {
    return new IngestionEvent(new SpanCreateValue(value));
  }

  public static IngestionEvent spanUpdate(UpdateSpanEvent value) {
    return new IngestionEvent(new SpanUpdateValue(value));
  }

  public static IngestionEvent generationCreate(CreateGenerationEvent value) {
    return new IngestionEvent(new GenerationCreateValue(value));
  }

  public static IngestionEvent generationUpdate(UpdateGenerationEvent value) {
    return new IngestionEvent(new GenerationUpdateValue(value));
  }

  public static IngestionEvent eventCreate(CreateEventEvent value) {
    return new IngestionEvent(new EventCreateValue(value));
  }

  public static IngestionEvent sdkLog(SdkLogEvent value) {
    return new IngestionEvent(new SdkLogValue(value));
  }

  public static IngestionEvent observationCreate(CreateObservationEvent value) {
    return new IngestionEvent(new ObservationCreateValue(value));
  }

  public static IngestionEvent observationUpdate(UpdateObservationEvent value) {
    return new IngestionEvent(new ObservationUpdateValue(value));
  }

  public boolean isTraceCreate() {
    return value instanceof TraceCreateValue;
  }

  public boolean isScoreCreate() {
    return value instanceof ScoreCreateValue;
  }

  public boolean isSpanCreate() {
    return value instanceof SpanCreateValue;
  }

  public boolean isSpanUpdate() {
    return value instanceof SpanUpdateValue;
  }

  public boolean isGenerationCreate() {
    return value instanceof GenerationCreateValue;
  }

  public boolean isGenerationUpdate() {
    return value instanceof GenerationUpdateValue;
  }

  public boolean isEventCreate() {
    return value instanceof EventCreateValue;
  }

  public boolean isSdkLog() {
    return value instanceof SdkLogValue;
  }

  public boolean isObservationCreate() {
    return value instanceof ObservationCreateValue;
  }

  public boolean isObservationUpdate() {
    return value instanceof ObservationUpdateValue;
  }

  public boolean _isUnknown() {
    return value instanceof _UnknownValue;
  }

  public Optional<TraceEvent> getTraceCreate() {
    if (isTraceCreate()) {
      return Optional.of(((TraceCreateValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<ScoreEvent> getScoreCreate() {
    if (isScoreCreate()) {
      return Optional.of(((ScoreCreateValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<CreateSpanEvent> getSpanCreate() {
    if (isSpanCreate()) {
      return Optional.of(((SpanCreateValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<UpdateSpanEvent> getSpanUpdate() {
    if (isSpanUpdate()) {
      return Optional.of(((SpanUpdateValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<CreateGenerationEvent> getGenerationCreate() {
    if (isGenerationCreate()) {
      return Optional.of(((GenerationCreateValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<UpdateGenerationEvent> getGenerationUpdate() {
    if (isGenerationUpdate()) {
      return Optional.of(((GenerationUpdateValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<CreateEventEvent> getEventCreate() {
    if (isEventCreate()) {
      return Optional.of(((EventCreateValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<SdkLogEvent> getSdkLog() {
    if (isSdkLog()) {
      return Optional.of(((SdkLogValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<CreateObservationEvent> getObservationCreate() {
    if (isObservationCreate()) {
      return Optional.of(((ObservationCreateValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<UpdateObservationEvent> getObservationUpdate() {
    if (isObservationUpdate()) {
      return Optional.of(((ObservationUpdateValue) value).value);
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
    T visitTraceCreate(TraceEvent traceCreate);

    T visitScoreCreate(ScoreEvent scoreCreate);

    T visitSpanCreate(CreateSpanEvent spanCreate);

    T visitSpanUpdate(UpdateSpanEvent spanUpdate);

    T visitGenerationCreate(CreateGenerationEvent generationCreate);

    T visitGenerationUpdate(UpdateGenerationEvent generationUpdate);

    T visitEventCreate(CreateEventEvent eventCreate);

    T visitSdkLog(SdkLogEvent sdkLog);

    T visitObservationCreate(CreateObservationEvent observationCreate);

    T visitObservationUpdate(UpdateObservationEvent observationUpdate);

    T _visitUnknown(Object unknownType);
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "type",
      visible = true,
      defaultImpl = _UnknownValue.class
  )
  @JsonSubTypes({
      @JsonSubTypes.Type(TraceCreateValue.class),
      @JsonSubTypes.Type(ScoreCreateValue.class),
      @JsonSubTypes.Type(SpanCreateValue.class),
      @JsonSubTypes.Type(SpanUpdateValue.class),
      @JsonSubTypes.Type(GenerationCreateValue.class),
      @JsonSubTypes.Type(GenerationUpdateValue.class),
      @JsonSubTypes.Type(EventCreateValue.class),
      @JsonSubTypes.Type(SdkLogValue.class),
      @JsonSubTypes.Type(ObservationCreateValue.class),
      @JsonSubTypes.Type(ObservationUpdateValue.class)
  })
  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  private interface Value {
    <T> T visit(Visitor<T> visitor);
  }

  @JsonTypeName("trace-create")
  @JsonIgnoreProperties("type")
  private static final class TraceCreateValue implements Value {
    @JsonUnwrapped
    private TraceEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private TraceCreateValue() {
    }

    private TraceCreateValue(TraceEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitTraceCreate(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof TraceCreateValue && equalTo((TraceCreateValue) other);
    }

    private boolean equalTo(TraceCreateValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("score-create")
  @JsonIgnoreProperties("type")
  private static final class ScoreCreateValue implements Value {
    @JsonUnwrapped
    private ScoreEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private ScoreCreateValue() {
    }

    private ScoreCreateValue(ScoreEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitScoreCreate(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof ScoreCreateValue && equalTo((ScoreCreateValue) other);
    }

    private boolean equalTo(ScoreCreateValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("span-create")
  @JsonIgnoreProperties("type")
  private static final class SpanCreateValue implements Value {
    @JsonUnwrapped
    private CreateSpanEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private SpanCreateValue() {
    }

    private SpanCreateValue(CreateSpanEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitSpanCreate(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof SpanCreateValue && equalTo((SpanCreateValue) other);
    }

    private boolean equalTo(SpanCreateValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("span-update")
  @JsonIgnoreProperties("type")
  private static final class SpanUpdateValue implements Value {
    @JsonUnwrapped
    private UpdateSpanEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private SpanUpdateValue() {
    }

    private SpanUpdateValue(UpdateSpanEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitSpanUpdate(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof SpanUpdateValue && equalTo((SpanUpdateValue) other);
    }

    private boolean equalTo(SpanUpdateValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("generation-create")
  @JsonIgnoreProperties("type")
  private static final class GenerationCreateValue implements Value {
    @JsonUnwrapped
    private CreateGenerationEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private GenerationCreateValue() {
    }

    private GenerationCreateValue(CreateGenerationEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitGenerationCreate(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof GenerationCreateValue && equalTo((GenerationCreateValue) other);
    }

    private boolean equalTo(GenerationCreateValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("generation-update")
  @JsonIgnoreProperties("type")
  private static final class GenerationUpdateValue implements Value {
    @JsonUnwrapped
    private UpdateGenerationEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private GenerationUpdateValue() {
    }

    private GenerationUpdateValue(UpdateGenerationEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitGenerationUpdate(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof GenerationUpdateValue && equalTo((GenerationUpdateValue) other);
    }

    private boolean equalTo(GenerationUpdateValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("event-create")
  @JsonIgnoreProperties("type")
  private static final class EventCreateValue implements Value {
    @JsonUnwrapped
    private CreateEventEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private EventCreateValue() {
    }

    private EventCreateValue(CreateEventEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitEventCreate(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof EventCreateValue && equalTo((EventCreateValue) other);
    }

    private boolean equalTo(EventCreateValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("sdk-log")
  @JsonIgnoreProperties("type")
  private static final class SdkLogValue implements Value {
    @JsonUnwrapped
    private SdkLogEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private SdkLogValue() {
    }

    private SdkLogValue(SdkLogEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitSdkLog(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof SdkLogValue && equalTo((SdkLogValue) other);
    }

    private boolean equalTo(SdkLogValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("observation-create")
  @JsonIgnoreProperties("type")
  private static final class ObservationCreateValue implements Value {
    @JsonUnwrapped
    private CreateObservationEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private ObservationCreateValue() {
    }

    private ObservationCreateValue(CreateObservationEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitObservationCreate(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof ObservationCreateValue && equalTo((ObservationCreateValue) other);
    }

    private boolean equalTo(ObservationCreateValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("observation-update")
  @JsonIgnoreProperties("type")
  private static final class ObservationUpdateValue implements Value {
    @JsonUnwrapped
    private UpdateObservationEvent value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private ObservationUpdateValue() {
    }

    private ObservationUpdateValue(UpdateObservationEvent value) {
      this.value = value;
    }

    @java.lang.Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitObservationUpdate(value);
    }

    @java.lang.Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof ObservationUpdateValue && equalTo((ObservationUpdateValue) other);
    }

    private boolean equalTo(ObservationUpdateValue other) {
      return value.equals(other.value);
    }

    @java.lang.Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @java.lang.Override
    public String toString() {
      return "IngestionEvent{" + "value: " + value + "}";
    }
  }

  @JsonIgnoreProperties("type")
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
      return "IngestionEvent{" + "type: " + type + ", value: " + value + "}";
    }
  }
}
