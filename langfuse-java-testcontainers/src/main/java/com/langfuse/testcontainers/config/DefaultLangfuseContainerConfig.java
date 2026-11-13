package com.langfuse.testcontainers.config;


/**
 * Default implementation of {@link LangfuseContainerConfig}.
 *
 * @author Eric Deandrea
 */
final class DefaultLangfuseContainerConfig implements LangfuseContainerConfig {

  private final LangfuseConfig langfuse;
  private final PostgresConfig postgres;
  private final ClickHouseConfig clickhouse;
  private final RedisConfig redis;
  private final MinIOConfig minio;
  private final WorkerConfig worker;

  DefaultLangfuseContainerConfig(Builder builder) {
    this.langfuse = builder.langfuse.build();
    this.postgres = builder.postgres.build();
    this.clickhouse = builder.clickhouse.build();
    this.redis = builder.redis.build();
    this.minio = builder.minio.build();
    this.worker = builder.worker.build();
  }

  @Override
  public LangfuseConfig langfuse() {
    return langfuse;
  }

  @Override
  public PostgresConfig postgres() {
    return postgres;
  }

  @Override
  public ClickHouseConfig clickhouse() {
    return clickhouse;
  }

  @Override
  public RedisConfig redis() {
    return redis;
  }

  @Override
  public MinIOConfig minio() {
    return minio;
  }

  @Override
  public WorkerConfig worker() {
    return worker;
  }
}