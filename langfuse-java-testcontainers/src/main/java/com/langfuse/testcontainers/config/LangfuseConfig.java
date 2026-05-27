package com.langfuse.testcontainers.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Langfuse web server configuration.
 *
 * @author Eric Deandrea
 */
public interface LangfuseConfig {
  /**
   * Default container image for Langfuse web server.
   */
  String DEFAULT_IMAGE = "docker.io/langfuse/langfuse:3";

  /**
   * Default port for the Langfuse web server.
   */
  int DEFAULT_PORT = 3000;

  /**
   * Default startup timeout for the Langfuse web server container.
   */
  Duration DEFAULT_STARTUP_TIMEOUT = Duration.ofMinutes(3);

  /**
   * @return the container image
   */
  String image();

  /**
   * @return the port the Langfuse web server listens on
   */
  int port();

  /**
   * @return the startup timeout
   */
  Duration startupTimeout();

  /**
   * @return the init org ID
   */
  String initOrgId();

  /**
   * @return the init org name
   */
  String initOrgName();

  /**
   * @return the init project ID
   */
  String initProjectId();

  /**
   * @return the init project name
   */
  String initProjectName();

  /**
   * @return the init project public API key
   */
  String initProjectPublicKey();

  /**
   * @return the init project secret API key
   */
  String initProjectSecretKey();

  /**
   * @return the init user email
   */
  String initUserEmail();

  /**
   * @return the init user name
   */
  String initUserName();

  /**
   * @return the init user password
   */
  String initUserPassword();

  /**
   * @return whether experimental features are enabled
   */
  boolean enableExperimentalFeatures();

  /**
   * @return whether batch export is enabled
   */
  boolean batchExportEnabled();

  /**
   * @return the ingestion queue delay, or empty if using the server default
   */
  Optional<Duration> ingestionQueueDelay();

  /**
   * @return the ingestion ClickHouse write interval, or empty if using the server default
   */
  Optional<Duration> ingestionClickhouseWriteInterval();

  /**
   * @return the email from address (empty string if unset)
   */
  String emailFromAddress();

  /**
   * @return the SMTP connection URL (empty string if unset)
   */
  String smtpConnectionUrl();

  /**
   * @return additional container environment variables
   */
  Map<String, String> containerEnv();

  /**
   * Builder for {@link LangfuseConfig}.
   *
   * @author Eric Deandrea
   */
  final class Builder {
    private final LangfuseContainerConfig.Builder parent;
    String image = DEFAULT_IMAGE;
    int port = DEFAULT_PORT;
    Duration startupTimeout = DEFAULT_STARTUP_TIMEOUT;
    String initOrgId = "langfuse-dev-org";
    String initOrgName = "Langfuse Dev Org";
    String initProjectId = "langfuse-dev-project";
    String initProjectName = "langfuse-dev";
    String initProjectPublicKey = "pk-lf-dev";
    String initProjectSecretKey = "sk-lf-dev";
    String initUserEmail = "dev@langfuse.com";
    String initUserName = "Dev User";
    String initUserPassword = "password";
    boolean enableExperimentalFeatures = false;
    boolean batchExportEnabled = false;
    Duration ingestionQueueDelay;
    Duration ingestionClickhouseWriteInterval;
    String emailFromAddress = "";
    String smtpConnectionUrl = "";
    final Map<String, String> containerEnv = new HashMap<>();

    Builder(LangfuseContainerConfig.Builder parent) {
      this.parent = parent;
    }

    /**
     * Returns the parent builder.
     *
     * @return the parent {@link LangfuseContainerConfig.Builder}
     */
    public LangfuseContainerConfig.Builder and() {
      return parent;
    }

    /** @param image the container image (default: {@value LangfuseConfig#DEFAULT_IMAGE})
     *  @return this builder */
    public Builder image(String image) {
      this.image = image;
      return this;
    }

    /**
     * Sets the port the Langfuse web server listens on.
     *
     * @param port the port (default: {@value LangfuseConfig#DEFAULT_PORT})
     * @return this builder
     */
    public Builder port(int port) {
      this.port = port;
      return this;
    }

    /** @param startupTimeout the maximum time to wait for the container to start
     *  @return this builder */
    public Builder startupTimeout(Duration startupTimeout) {
      this.startupTimeout = startupTimeout;
      return this;
    }

    /** @param initOrgId the org ID (default: {@code "langfuse-dev-org"})
     *  @return this builder */
    public Builder initOrgId(String initOrgId) {
      this.initOrgId = initOrgId;
      return this;
    }

    /** @param initOrgName the org name (default: {@code "Langfuse Dev Org"})
     *  @return this builder */
    public Builder initOrgName(String initOrgName) {
      this.initOrgName = initOrgName;
      return this;
    }

    /** @param initProjectId the project ID (default: {@code "langfuse-dev-project"})
     *  @return this builder */
    public Builder initProjectId(String initProjectId) {
      this.initProjectId = initProjectId;
      return this;
    }

    /** @param initProjectName the project name (default: {@code "langfuse-dev"})
     *  @return this builder */
    public Builder initProjectName(String initProjectName) {
      this.initProjectName = initProjectName;
      return this;
    }

    /** @param initProjectPublicKey the public key (default: {@code "pk-lf-dev"})
     *  @return this builder */
    public Builder initProjectPublicKey(String initProjectPublicKey) {
      this.initProjectPublicKey = initProjectPublicKey;
      return this;
    }

    /** @param initProjectSecretKey the secret key (default: {@code "sk-lf-dev"})
     *  @return this builder */
    public Builder initProjectSecretKey(String initProjectSecretKey) {
      this.initProjectSecretKey = initProjectSecretKey;
      return this;
    }

    /** @param initUserEmail the user email (default: {@code "dev@langfuse.com"})
     *  @return this builder */
    public Builder initUserEmail(String initUserEmail) {
      this.initUserEmail = initUserEmail;
      return this;
    }

    /** @param initUserName the user name (default: {@code "Dev User"})
     *  @return this builder */
    public Builder initUserName(String initUserName) {
      this.initUserName = initUserName;
      return this;
    }

    /** @param initUserPassword the user password (default: {@code "password"})
     *  @return this builder */
    public Builder initUserPassword(String initUserPassword) {
      this.initUserPassword = initUserPassword;
      return this;
    }

    /** @param enableExperimentalFeatures whether to enable experimental features (default: {@code false})
     *  @return this builder */
    public Builder enableExperimentalFeatures(boolean enableExperimentalFeatures) {
      this.enableExperimentalFeatures = enableExperimentalFeatures;
      return this;
    }

    /** @param batchExportEnabled whether to enable batch export (default: {@code false})
     *  @return this builder */
    public Builder batchExportEnabled(boolean batchExportEnabled) {
      this.batchExportEnabled = batchExportEnabled;
      return this;
    }

    /**
     * Sets the ingestion queue processing delay.
     *
     * @param ingestionQueueDelay the delay between queue processing cycles
     * @return this builder
     */
    public Builder ingestionQueueDelay(Duration ingestionQueueDelay) {
      this.ingestionQueueDelay = ingestionQueueDelay;
      return this;
    }

    /**
     * Sets the interval between ClickHouse write flushes for ingested events.
     *
     * @param ingestionClickhouseWriteInterval the write interval
     * @return this builder
     */
    public Builder ingestionClickhouseWriteInterval(Duration ingestionClickhouseWriteInterval) {
      this.ingestionClickhouseWriteInterval = ingestionClickhouseWriteInterval;
      return this;
    }

    /** @param emailFromAddress the email from address (default: empty)
     *  @return this builder */
    public Builder emailFromAddress(String emailFromAddress) {
      this.emailFromAddress = emailFromAddress;
      return this;
    }

    /** @param smtpConnectionUrl the SMTP connection URL (default: empty)
     *  @return this builder */
    public Builder smtpConnectionUrl(String smtpConnectionUrl) {
      this.smtpConnectionUrl = smtpConnectionUrl;
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

    LangfuseConfig build() {
      return new DefaultLangfuseConfig(this);
    }
  }
}
