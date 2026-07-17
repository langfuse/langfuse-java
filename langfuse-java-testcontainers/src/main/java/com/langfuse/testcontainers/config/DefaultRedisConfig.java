package com.langfuse.testcontainers.config;

import java.util.Map;

final class DefaultRedisConfig implements RedisConfig {
  private final String image;
  private final String password;
  private final boolean tlsEnabled;
  private final Map<String, String> containerEnv;

  DefaultRedisConfig(Builder builder) {
    this.image = builder.image;
    this.password = builder.password;
    this.tlsEnabled = builder.tlsEnabled;
    this.containerEnv = Map.copyOf(builder.containerEnv);
  }

  @Override
  public String image() {
    return image;
  }

  @Override
  public String password() {
    return password;
  }

  @Override
  public boolean tlsEnabled() {
    return tlsEnabled;
  }

  @Override
  public Map<String, String> containerEnv() {
    return containerEnv;
  }
}
