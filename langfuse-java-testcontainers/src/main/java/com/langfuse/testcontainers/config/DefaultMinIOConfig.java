package com.langfuse.testcontainers.config;


import java.util.Map;

final class DefaultMinIOConfig implements MinIOConfig {
  private final String image;
  private final String rootUser;
  private final String rootPassword;
  private final String bucketName;
  private final Map<String, String> containerEnv;

  DefaultMinIOConfig(Builder builder) {
    this.image = builder.image;
    this.rootUser = builder.rootUser;
    this.rootPassword = builder.rootPassword;
    this.bucketName = builder.bucketName;
    this.containerEnv = Map.copyOf(builder.containerEnv);
  }

  @Override
  public String image() {
    return image;
  }

  @Override
  public String rootUser() {
    return rootUser;
  }

  @Override
  public String rootPassword() {
    return rootPassword;
  }

  @Override
  public String bucketName() {
    return bucketName;
  }

  @Override
  public Map<String, String> containerEnv() {
    return containerEnv;
  }
}