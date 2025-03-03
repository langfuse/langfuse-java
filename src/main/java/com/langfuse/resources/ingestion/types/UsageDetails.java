/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.ingestion.types;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import core.ObjectMappers;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import java.util.Objects;

@JsonDeserialize(
    using = UsageDetails.Deserializer.class
)
public final class UsageDetails {
  private final Object value;

  private final int type;

  private UsageDetails(Object value, int type) {
    this.value = value;
    this.type = type;
  }

  @JsonValue
  public Object get() {
    return this.value;
  }

  public <T> T visit(Visitor<T> visitor) {
    if(this.type == 0) {
      return visitor.visit((Map<String, Integer>) this.value);
    } else if(this.type == 1) {
      return visitor.visit((OpenAiUsageSchema) this.value);
    }
    throw new IllegalStateException("Failed to visit value. This should never happen.");
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof UsageDetails && equalTo((UsageDetails) other);
  }

  private boolean equalTo(UsageDetails other) {
    return value.equals(other.value);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.value);
  }

  @java.lang.Override
  public String toString() {
    return this.value.toString();
  }

  public static UsageDetails of(Map<String, Integer> value) {
    return new UsageDetails(value, 0);
  }

  public static UsageDetails of(OpenAiUsageSchema value) {
    return new UsageDetails(value, 1);
  }

  public interface Visitor<T> {
    T visit(Map<String, Integer> value);

    T visit(OpenAiUsageSchema value);
  }

  static final class Deserializer extends StdDeserializer<UsageDetails> {
    Deserializer() {
      super(UsageDetails.class);
    }

    @java.lang.Override
    public UsageDetails deserialize(JsonParser p, DeserializationContext context) throws
        IOException {
      Object value = p.readValueAs(Object.class);
      try {
        return of(ObjectMappers.JSON_MAPPER.convertValue(value, new TypeReference<Map<String, Integer>>() {}));
      } catch(IllegalArgumentException e) {
      }
      try {
        return of(ObjectMappers.JSON_MAPPER.convertValue(value, OpenAiUsageSchema.class));
      } catch(IllegalArgumentException e) {
      }
      throw new JsonParseException(p, "Failed to deserialize");
    }
  }
}
