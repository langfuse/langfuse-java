/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.comments.requests;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.langfuse.client.core.ObjectMappers;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(
    builder = GetCommentsRequest.Builder.class
)
public final class GetCommentsRequest {
  private final Optional<Integer> page;

  private final Optional<Integer> limit;

  private final Optional<String> objectType;

  private final Optional<String> objectId;

  private final Optional<String> authorUserId;

  private final Map<String, Object> additionalProperties;

  private GetCommentsRequest(Optional<Integer> page, Optional<Integer> limit,
      Optional<String> objectType, Optional<String> objectId, Optional<String> authorUserId,
      Map<String, Object> additionalProperties) {
    this.page = page;
    this.limit = limit;
    this.objectType = objectType;
    this.objectId = objectId;
    this.authorUserId = authorUserId;
    this.additionalProperties = additionalProperties;
  }

  /**
   * @return Page number, starts at 1.
   */
  @JsonProperty("page")
  public Optional<Integer> getPage() {
    return page;
  }

  /**
   * @return Limit of items per page. If you encounter api issues due to too large page sizes, try to reduce the limit
   */
  @JsonProperty("limit")
  public Optional<Integer> getLimit() {
    return limit;
  }

  /**
   * @return Filter comments by object type (trace, observation, session, prompt).
   */
  @JsonProperty("objectType")
  public Optional<String> getObjectType() {
    return objectType;
  }

  /**
   * @return Filter comments by object id. If objectType is not provided, an error will be thrown.
   */
  @JsonProperty("objectId")
  public Optional<String> getObjectId() {
    return objectId;
  }

  /**
   * @return Filter comments by author user id.
   */
  @JsonProperty("authorUserId")
  public Optional<String> getAuthorUserId() {
    return authorUserId;
  }

  @java.lang.Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof GetCommentsRequest && equalTo((GetCommentsRequest) other);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  private boolean equalTo(GetCommentsRequest other) {
    return page.equals(other.page) && limit.equals(other.limit) && objectType.equals(other.objectType) && objectId.equals(other.objectId) && authorUserId.equals(other.authorUserId);
  }

  @java.lang.Override
  public int hashCode() {
    return Objects.hash(this.page, this.limit, this.objectType, this.objectId, this.authorUserId);
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
    private Optional<Integer> page = Optional.empty();

    private Optional<Integer> limit = Optional.empty();

    private Optional<String> objectType = Optional.empty();

    private Optional<String> objectId = Optional.empty();

    private Optional<String> authorUserId = Optional.empty();

    @JsonAnySetter
    private Map<String, Object> additionalProperties = new HashMap<>();

    private Builder() {
    }

    public Builder from(GetCommentsRequest other) {
      page(other.getPage());
      limit(other.getLimit());
      objectType(other.getObjectType());
      objectId(other.getObjectId());
      authorUserId(other.getAuthorUserId());
      return this;
    }

    @JsonSetter(
        value = "page",
        nulls = Nulls.SKIP
    )
    public Builder page(Optional<Integer> page) {
      this.page = page;
      return this;
    }

    public Builder page(Integer page) {
      this.page = Optional.ofNullable(page);
      return this;
    }

    @JsonSetter(
        value = "limit",
        nulls = Nulls.SKIP
    )
    public Builder limit(Optional<Integer> limit) {
      this.limit = limit;
      return this;
    }

    public Builder limit(Integer limit) {
      this.limit = Optional.ofNullable(limit);
      return this;
    }

    @JsonSetter(
        value = "objectType",
        nulls = Nulls.SKIP
    )
    public Builder objectType(Optional<String> objectType) {
      this.objectType = objectType;
      return this;
    }

    public Builder objectType(String objectType) {
      this.objectType = Optional.ofNullable(objectType);
      return this;
    }

    @JsonSetter(
        value = "objectId",
        nulls = Nulls.SKIP
    )
    public Builder objectId(Optional<String> objectId) {
      this.objectId = objectId;
      return this;
    }

    public Builder objectId(String objectId) {
      this.objectId = Optional.ofNullable(objectId);
      return this;
    }

    @JsonSetter(
        value = "authorUserId",
        nulls = Nulls.SKIP
    )
    public Builder authorUserId(Optional<String> authorUserId) {
      this.authorUserId = authorUserId;
      return this;
    }

    public Builder authorUserId(String authorUserId) {
      this.authorUserId = Optional.ofNullable(authorUserId);
      return this;
    }

    public GetCommentsRequest build() {
      return new GetCommentsRequest(page, limit, objectType, objectId, authorUserId, additionalProperties);
    }
  }
}
