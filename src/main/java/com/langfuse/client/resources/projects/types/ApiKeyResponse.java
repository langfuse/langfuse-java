/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.projects.types;

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
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = ApiKeyResponse.Builder.class
)
public final class ApiKeyResponse {
  private final String id;

  private final OffsetDateTime createdAt;

  private final String publicKey;

  private final String secretKey;

  private final String displaySecretKey;

  private final Optional<String> note;

  private final Map<String, Object> additionalProperties;

  private ApiKeyResponse(String id, OffsetDateTime createdAt, String publicKey, String secretKey,
      String displaySecretKey, Optional<String> note, Map<String, Object> additionalProperties) {
    this.id = id;
    this.createdAt = createdAt;
    this.publicKey = publicKey;
    this.secretKey = secretKey;
    this.displaySecretKey = displaySecretKey;
    this.note = note;
    this.additionalProperties = additionalProperties;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("publicKey")
  public String getPublicKey() {
    return publicKey;
  }

  @JsonProperty("secretKey")
  public String getSecretKey() {
    return secretKey;
  }

  @JsonProperty("displaySecretKey")
  public String getDisplaySecretKey() {
    return displaySecretKey;
  }

  @JsonProperty("note")
  public Optional<String> getNote() {
    return note;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof ApiKeyResponse && equalTo((ApiKeyResponse) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(ApiKeyResponse other) {
    return id.equals(other.id) && createdAt.equals(other.createdAt) && publicKey.equals(other.publicKey) && secretKey.equals(other.secretKey) && displaySecretKey.equals(other.displaySecretKey) && note.equals(other.note);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.id, this.createdAt, this.publicKey, this.secretKey, this.displaySecretKey, this.note);
  }

  @java.lang.Override
  public String toString() {
    return ObjectMappers.stringify(this);
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    CreatedAtStage id(@NotNull String id);

    Builder from(ApiKeyResponse other);
  }

  public interface CreatedAtStage {
    PublicKeyStage createdAt(@NotNull OffsetDateTime createdAt);
  }

  public interface PublicKeyStage {
    SecretKeyStage publicKey(@NotNull String publicKey);
  }

  public interface SecretKeyStage {
    DisplaySecretKeyStage secretKey(@NotNull String secretKey);
  }

  public interface DisplaySecretKeyStage {
    _FinalStage displaySecretKey(@NotNull String displaySecretKey);
  }

  public interface _FinalStage {
    ApiKeyResponse build();

    _FinalStage note(Optional<String> note);

    _FinalStage note(String note);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, CreatedAtStage, PublicKeyStage, SecretKeyStage, DisplaySecretKeyStage, _FinalStage {
    private String id;

    private OffsetDateTime createdAt;

    private String publicKey;

    private String secretKey;

    private String displaySecretKey;

    private Optional<String> note = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    @java.lang.Override
    public Builder from(ApiKeyResponse other) {
      id(other.getId());
      createdAt(other.getCreatedAt());
      publicKey(other.getPublicKey());
      secretKey(other.getSecretKey());
      displaySecretKey(other.getDisplaySecretKey());
      note(other.getNote());
      return this;
    }

    @java.lang.Override
    @JsonSetter("id")
    public CreatedAtStage id(@NotNull String id) {
      this.id = Objects.requireNonNull(id, "id must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("createdAt")
    public PublicKeyStage createdAt(@NotNull OffsetDateTime createdAt) {
      this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("publicKey")
    public SecretKeyStage publicKey(@NotNull String publicKey) {
      this.publicKey = Objects.requireNonNull(publicKey, "publicKey must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("secretKey")
    public DisplaySecretKeyStage secretKey(@NotNull String secretKey) {
      this.secretKey = Objects.requireNonNull(secretKey, "secretKey must not be null");
      return this;
    }

    @java.lang.Override
    @JsonSetter("displaySecretKey")
    public _FinalStage displaySecretKey(@NotNull String displaySecretKey) {
      this.displaySecretKey = Objects.requireNonNull(displaySecretKey, "displaySecretKey must not be null");
      return this;
    }

    @java.lang.Override
    public _FinalStage note(String note) {
      this.note = Optional.ofNullable(note);
      return this;
    }

    @java.lang.Override
    @JsonSetter(
        value = "note",
        nulls = Nulls.SKIP
    )
    public _FinalStage note(Optional<String> note) {
      this.note = note;
      return this;
    }

    @java.lang.Override
    public ApiKeyResponse build() {
      return new ApiKeyResponse(id, createdAt, publicKey, secretKey, displaySecretKey, note, additionalProperties);
    }
  }
}
