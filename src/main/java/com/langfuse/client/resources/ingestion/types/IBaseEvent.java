/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.ingestion.types;

import java.lang.Object;
import java.lang.String;
import java.util.Optional;

public interface IBaseEvent {
  String getId();

  String getTimestamp();

  Optional<Object> getMetadata();
}
