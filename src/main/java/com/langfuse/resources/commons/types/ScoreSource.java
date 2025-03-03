/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.commons.types;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;

public enum ScoreSource {
  ANNOTATION("ANNOTATION"),

  API("API"),

  EVAL("EVAL");

  private final String value;

  ScoreSource(String value) {
    this.value = value;
  }

  @JsonValue
  @java.lang.Override
  public String toString() {
    return this.value;
  }
}
