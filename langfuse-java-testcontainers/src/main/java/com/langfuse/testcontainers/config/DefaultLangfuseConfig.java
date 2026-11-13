package com.langfuse.testcontainers.config;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

final class DefaultLangfuseConfig implements LangfuseConfig {
  private final String image;
  private final int port;
  private final Duration startupTimeout;
  private final String initOrgId;
  private final String initOrgName;
  private final String initProjectId;
  private final String initProjectName;
  private final String initProjectPublicKey;
  private final String initProjectSecretKey;
  private final String initUserEmail;
  private final String initUserName;
  private final String initUserPassword;
  private final boolean enableExperimentalFeatures;
  private final boolean batchExportEnabled;
  private final Duration ingestionQueueDelay;
  private final Duration ingestionClickhouseWriteInterval;
  private final String emailFromAddress;
  private final String smtpConnectionUrl;
  private final Map<String, String> containerEnv;

  DefaultLangfuseConfig(Builder builder) {
    this.image = builder.image;
    this.port = builder.port;
    this.startupTimeout = builder.startupTimeout;
    this.initOrgId = builder.initOrgId;
    this.initOrgName = builder.initOrgName;
    this.initProjectId = builder.initProjectId;
    this.initProjectName = builder.initProjectName;
    this.initProjectPublicKey = builder.initProjectPublicKey;
    this.initProjectSecretKey = builder.initProjectSecretKey;
    this.initUserEmail = builder.initUserEmail;
    this.initUserName = builder.initUserName;
    this.initUserPassword = builder.initUserPassword;
    this.enableExperimentalFeatures = builder.enableExperimentalFeatures;
    this.batchExportEnabled = builder.batchExportEnabled;
    this.ingestionQueueDelay = builder.ingestionQueueDelay;
    this.ingestionClickhouseWriteInterval = builder.ingestionClickhouseWriteInterval;
    this.emailFromAddress = builder.emailFromAddress;
    this.smtpConnectionUrl = builder.smtpConnectionUrl;
    this.containerEnv = Map.copyOf(builder.containerEnv);
  }

  @Override
  public String image() {
    return image;
  }

  @Override
  public int port() {
    return port;
  }

  @Override
  public Duration startupTimeout() {
    return startupTimeout;
  }

  @Override
  public String initOrgId() {
    return initOrgId;
  }

  @Override
  public String initOrgName() {
    return initOrgName;
  }

  @Override
  public String initProjectId() {
    return initProjectId;
  }

  @Override
  public String initProjectName() {
    return initProjectName;
  }

  @Override
  public String initProjectPublicKey() {
    return initProjectPublicKey;
  }

  @Override
  public String initProjectSecretKey() {
    return initProjectSecretKey;
  }

  @Override
  public String initUserEmail() {
    return initUserEmail;
  }

  @Override
  public String initUserName() {
    return initUserName;
  }

  @Override
  public String initUserPassword() {
    return initUserPassword;
  }

  @Override
  public boolean enableExperimentalFeatures() {
    return enableExperimentalFeatures;
  }

  @Override
  public boolean batchExportEnabled() {
    return batchExportEnabled;
  }

  @Override
  public Optional<Duration> ingestionQueueDelay() {
    return Optional.ofNullable(ingestionQueueDelay);
  }

  @Override
  public Optional<Duration> ingestionClickhouseWriteInterval() {
    return Optional.ofNullable(ingestionClickhouseWriteInterval);
  }

  @Override
  public String emailFromAddress() {
    return emailFromAddress;
  }

  @Override
  public String smtpConnectionUrl() {
    return smtpConnectionUrl;
  }

  @Override
  public Map<String, String> containerEnv() {
    return containerEnv;
  }
}
