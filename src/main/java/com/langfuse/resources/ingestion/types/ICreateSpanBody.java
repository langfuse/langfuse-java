/**
 * This file was auto-generated by Fern from our API Definition.
 */

package resources.ingestion.types;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface ICreateSpanBody extends ICreateEventBody {
  Optional<OffsetDateTime> getEndTime();
}
