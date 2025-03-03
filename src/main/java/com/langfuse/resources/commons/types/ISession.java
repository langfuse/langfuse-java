/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.commons.types;

import java.lang.String;
import java.time.OffsetDateTime;
import java.util.Optional;

public interface ISession {
  String getId();

  OffsetDateTime getCreatedAt();

  String getProjectId();

  Optional<String> getEnvironment();
}
