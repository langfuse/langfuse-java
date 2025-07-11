/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.organizations.types;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;

public enum MembershipRole {
  OWNER("OWNER"),

  ADMIN("ADMIN"),

  MEMBER("MEMBER"),

  VIEWER("VIEWER");

  private final String value;

  MembershipRole(String value) {
    this.value = value;
  }

  @JsonValue
  @java.lang.Override
  public String toString() {
    return this.value;
  }
}
