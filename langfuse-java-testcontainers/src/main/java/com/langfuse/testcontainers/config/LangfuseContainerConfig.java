package com.langfuse.testcontainers.config;

/**
 * Configuration for the Langfuse testcontainers environment.
 * Contains nested configurations for each infrastructure service.
 *
 * <pre>{@code
 * var config = LangfuseContainerConfig.builder()
 *     .langfuse()
 *         .initProjectPublicKey("my-pk")
 *         .initProjectSecretKey("my-sk")
 *         .and()
 *     .postgres()
 *         .image("postgres:16")
 *         .and()
 *     .build();
 * }</pre>
 *
 * @author Eric Deandrea
 */
public interface LangfuseContainerConfig {

  /**
   * @return the Langfuse web server configuration
   */
  LangfuseConfig langfuse();

  /**
   * @return the PostgreSQL configuration
   */
  PostgresConfig postgres();

  /**
   * @return the ClickHouse configuration
   */
  ClickHouseConfig clickhouse();

  /**
   * @return the Redis configuration
   */
  RedisConfig redis();

  /**
   * @return the MinIO configuration
   */
  MinIOConfig minio();

  /**
   * @return the Langfuse worker configuration
   */
  WorkerConfig worker();

  /**
   * Creates a new builder with default values.
   *
   * @return a new {@link Builder}
   */
  static Builder builder() {
    return new Builder();
  }

  /**
   * Top-level builder for {@link LangfuseContainerConfig}.
   *
   * @author Eric Deandrea
   */
  class Builder {
    final LangfuseConfig.Builder langfuse = new LangfuseConfig.Builder(this);
    final PostgresConfig.Builder postgres = new PostgresConfig.Builder(this);
    final ClickHouseConfig.Builder clickhouse = new ClickHouseConfig.Builder(this);
    final RedisConfig.Builder redis = new RedisConfig.Builder(this);
    final MinIOConfig.Builder minio = new MinIOConfig.Builder(this);
    final WorkerConfig.Builder worker = new WorkerConfig.Builder(this);

    /**
     * Returns the Langfuse web server config builder for customization.
     * Call {@code .and()} to return to this builder.
     *
     * @return the Langfuse config builder
     */
    public LangfuseConfig.Builder langfuse() {
      return langfuse;
    }

    /**
     * Returns the PostgreSQL config builder for customization.
     * Call {@code .and()} to return to this builder.
     *
     * @return the PostgreSQL config builder
     */
    public PostgresConfig.Builder postgres() {
      return postgres;
    }

    /**
     * Returns the ClickHouse config builder for customization.
     * Call {@code .and()} to return to this builder.
     *
     * @return the ClickHouse config builder
     */
    public ClickHouseConfig.Builder clickhouse() {
      return clickhouse;
    }

    /**
     * Returns the Redis config builder for customization.
     * Call {@code .and()} to return to this builder.
     *
     * @return the Redis config builder
     */
    public RedisConfig.Builder redis() {
      return redis;
    }

    /**
     * Returns the MinIO config builder for customization.
     * Call {@code .and()} to return to this builder.
     *
     * @return the MinIO config builder
     */
    public MinIOConfig.Builder minio() {
      return minio;
    }

    /**
     * Returns the Langfuse worker config builder for customization.
     * Call {@code .and()} to return to this builder.
     *
     * @return the worker config builder
     */
    public WorkerConfig.Builder worker() {
      return worker;
    }

    /**
     * Builds the configuration.
     *
     * @return a new {@link LangfuseContainerConfig}
     */
    public LangfuseContainerConfig build() {
      return new DefaultLangfuseContainerConfig(this);
    }
  }
}