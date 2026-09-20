package com.langfuse.testcontainers.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis configuration.
 *
 * @author Eric Deandrea
 */
public interface RedisConfig {
  /**
   * Default container image for Redis.
   */
  String DEFAULT_IMAGE = "redis:7";

  /**
   * @return the container image
   */
  String image();

  /**
   * @return the Redis password
   */
  String password();

  /**
   * @return whether TLS is enabled
   */
  boolean tlsEnabled();

  /**
   * @return additional container environment variables
   */
  Map<String, String> containerEnv();

  /**
   * Builder for {@link RedisConfig}.
   *
   * @author Eric Deandrea
   */
  final class Builder {
    private final LangfuseContainerConfig.Builder parent;
    String image = DEFAULT_IMAGE;
    String password = "myredissecret";
    boolean tlsEnabled = false;
    final Map<String, String> containerEnv = new HashMap<>();

    Builder(LangfuseContainerConfig.Builder parent) {
      this.parent = parent;
    }

    /**
     * @return the parent {@link LangfuseContainerConfig.Builder}
     */
    public LangfuseContainerConfig.Builder and() {
      return parent;
    }

    /** @param image the container image (default: {@value RedisConfig#DEFAULT_IMAGE})
     *  @return this builder */
    public Builder image(String image) {
      this.image = image;
      return this;
    }

    /** @param password the password (default: {@code "myredissecret"})
     *  @return this builder */
    public Builder password(String password) {
      this.password = password;
      return this;
    }

    /** @param tlsEnabled whether TLS is enabled (default: {@code false})
     *  @return this builder */
    public Builder tlsEnabled(boolean tlsEnabled) {
      this.tlsEnabled = tlsEnabled;
      return this;
    }

    /** @param key the environment variable name
     *  @param value the environment variable value
     *  @return this builder */
    public Builder containerEnv(String key, String value) {
      this.containerEnv.put(key, value);
      return this;
    }

    /** @param env a map of environment variable names to values
     *  @return this builder */
    public Builder containerEnv(Map<String, String> env) {
      this.containerEnv.putAll(env);
      return this;
    }

    RedisConfig build() {
      return new DefaultRedisConfig(this);
    }
  }
}
