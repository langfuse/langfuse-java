package com.langfuse.testcontainers.config;


import java.util.Map;

final class DefaultClickHouseConfig implements ClickHouseConfig {
  private final String image;
  private final String username;
  private final String password;
  private final String databaseName;
  private final Map<String, String> containerEnv;

  DefaultClickHouseConfig(Builder builder) {
    this.image = builder.image;
    this.username = builder.username;
    this.password = builder.password;
    this.databaseName = builder.databaseName;
    this.containerEnv = Map.copyOf(builder.containerEnv);
  }

  @Override
  public String image() {
    return image;
  }

  @Override
  public String username() {
    return username;
  }

  @Override
  public String password() {
    return password;
  }

  @Override
  public String databaseName() {
    return databaseName;
  }

  @Override
  public Map<String, String> containerEnv() {
    return containerEnv;
  }
}