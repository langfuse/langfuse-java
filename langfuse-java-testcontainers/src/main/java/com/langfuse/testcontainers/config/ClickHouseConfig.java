package com.langfuse.testcontainers.config;

import java.util.HashMap;
import java.util.Map;

/**
 * ClickHouse configuration.
 *
 * @author Eric Deandrea
 */
public interface ClickHouseConfig {
  /**
   * Default container image for ClickHouse.
   */
  String DEFAULT_IMAGE = "clickhouse/clickhouse-server";

  /**
   * @return the container image
   */
  String image();

  /**
   * @return the database username
   */
  String username();

  /**
   * @return the database password
   */
  String password();

  /**
   * @return the database name
   */
  String databaseName();

  /**
   * @return additional container environment variables
   */
  Map<String, String> containerEnv();

  /**
   * Builder for {@link ClickHouseConfig}.
   *
   * @author Eric Deandrea
   */
  class Builder {
    private final LangfuseContainerConfig.Builder parent;
    String image = DEFAULT_IMAGE;
    String username = "clickhouse";
    String password = "clickhouse";
    String databaseName = "default";
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

    /** @param image the container image (default: {@value ClickHouseConfig#DEFAULT_IMAGE})
     *  @return this builder */
    public Builder image(String image) {
      this.image = image;
      return this;
    }

    /** @param username the username (default: {@code "clickhouse"})
     *  @return this builder */
    public Builder username(String username) {
      this.username = username;
      return this;
    }

    /** @param password the password (default: {@code "clickhouse"})
     *  @return this builder */
    public Builder password(String password) {
      this.password = password;
      return this;
    }

    /** @param databaseName the database name (default: {@code "default"})
     *  @return this builder */
    public Builder databaseName(String databaseName) {
      this.databaseName = databaseName;
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

    ClickHouseConfig build() {
      return new DefaultClickHouseConfig(this);
    }
  }
}
