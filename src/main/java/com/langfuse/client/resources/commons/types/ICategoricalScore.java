/**
 * This file was auto-generated by Fern from our API Definition.
 */

package com.langfuse.client.resources.commons.types;

import java.lang.Double;
import java.lang.String;
import java.util.Optional;

public interface ICategoricalScore extends IBaseScore {
  Optional<Double> getValue();

  String getStringValue();
}
