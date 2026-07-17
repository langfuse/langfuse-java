package com.langfuse.testcontainers.config;

import java.util.Map;

final class DefaultWorkerConfig implements WorkerConfig {
  private final String image;
  private final Map<String, String> containerEnv;

  DefaultWorkerConfig(Builder builder) {
    this.image = builder.image;
    this.containerEnv = Map.copyOf(builder.containerEnv);
  }

  @Override
  public String image() {
    return image;
  }

  @Override
  public Map<String, String> containerEnv() {
    return containerEnv;
  }
}