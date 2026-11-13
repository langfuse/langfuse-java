package com.langfuse.testcontainers.config;

import java.util.HashMap;
import java.util.Map;

/**
 * PostgreSQL configuration.
 *
 * @author Eric Deandrea
 */
public interface PostgresConfig {
  /**
   * Default container image for PostgreSQL.
   */
  String DEFAULT_IMAGE = "postgres:17";

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
   * Builder for {@link PostgresConfig}.
   *
   * @author Eric Deandrea
   */
  final class Builder {
    private final LangfuseContainerConfig.Builder parent;
    String image = DEFAULT_IMAGE;
    String username = "postgres";
    String password = "postgres";
    String databaseName = "postgres";
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

    /** @param image the container image (default: {@value PostgresConfig#DEFAULT_IMAGE})
     *  @return this builder */
    public Builder image(String image) {
      this.image = image;
      return this;
    }

    /** @param username the username (default: {@code "postgres"})
     *  @return this builder */
    public Builder username(String username) {
      this.username = username;
      return this;
    }

    /** @param password the password (default: {@code "postgres"})
     *  @return this builder */
    public Builder password(String password) {
      this.password = password;
      return this;
    }

    /** @param databaseName the database name (default: {@code "postgres"})
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

    PostgresConfig build() {
      return new DefaultPostgresConfig(this);
    }
  }
}
