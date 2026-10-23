package com.langfuse.testcontainers.config;

import java.util.HashMap;
import java.util.Map;

/**
 * MinIO configuration.
 *
 * @author Eric Deandrea
 */
public interface MinIOConfig {
  /**
   * Default container image for MinIO.
   */
  String DEFAULT_IMAGE = "cgr.dev/chainguard/minio";

  /**
   * @return the container image
   */
  String image();

  /**
   * @return the MinIO root user
   */
  String rootUser();

  /**
   * @return the MinIO root password
   */
  String rootPassword();

  /**
   * @return the bucket name to create
   */
  String bucketName();

  /**
   * @return additional container environment variables
   */
  Map<String, String> containerEnv();

  /**
   * Builder for {@link MinIOConfig}.
   *
   * @author Eric Deandrea
   */
  final class Builder {
    private final LangfuseContainerConfig.Builder parent;
    String image = DEFAULT_IMAGE;
    String rootUser = "minio";
    String rootPassword = "miniosecret";
    String bucketName = "langfuse";
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

    /** @param image the container image (default: {@value MinIOConfig#DEFAULT_IMAGE})
     *  @return this builder */
    public Builder image(String image) {
      this.image = image;
      return this;
    }

    /** @param rootUser the root user (default: {@code "minio"})
     *  @return this builder */
    public Builder rootUser(String rootUser) {
      this.rootUser = rootUser;
      return this;
    }

    /** @param rootPassword the root password (default: {@code "miniosecret"})
     *  @return this builder */
    public Builder rootPassword(String rootPassword) {
      this.rootPassword = rootPassword;
      return this;
    }

    /** @param bucketName the bucket name (default: {@code "langfuse"})
     *  @return this builder */
    public Builder bucketName(String bucketName) {
      this.bucketName = bucketName;
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

    MinIOConfig build() {
      return new DefaultMinIOConfig(this);
    }
  }
}
