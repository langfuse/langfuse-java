package com.langfuse.testcontainers;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.testcontainers.clickhouse.ClickHouseContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import com.langfuse.testcontainers.config.LangfuseContainerConfig;
import com.redis.testcontainers.RedisContainer;

/**
 * Testcontainers-based Langfuse environment.
 *
 * <p>Orchestrates all services required by Langfuse (PostgreSQL, ClickHouse, Redis, MinIO)
 * and starts the Langfuse web server. Provides the Langfuse URL and pre-configured
 * API credentials for use in integration tests.
 *
 * <pre>{@code
 * var langfuse = new LangfuseContainer();
 * langfuse.start();
 *
 * String url = langfuse.getLangfuseUrl();
 * String publicKey = langfuse.getPublicKey();
 * String secretKey = langfuse.getSecretKey();
 * }</pre>
 *
 * @author Eric Deandrea
 */
public class LangfuseContainer extends GenericContainer<LangfuseContainer> {
    private static final String SERVICE_LABEL = "com.langfuse.testcontainers.service";
    private static final String POSTGRES_ALIAS = "postgres";
    private static final String CLICKHOUSE_ALIAS = "clickhouse";
    private static final String REDIS_ALIAS = "redis";
    private static final String MINIO_ALIAS = "minio";

    private final LangfuseContainerConfig config;
    private final Network network;
    private final PostgreSQLContainer<?> postgres;
    private final ClickHouseContainer clickhouse;
    private final RedisContainer redis;
    private final MinIOContainer minio;
    private final GenericContainer<?> worker;

    /**
     * Creates a new Langfuse test environment with default configuration.
     */
    public LangfuseContainer() {
        this(LangfuseContainerConfig.builder().build());
    }

    /**
     * Creates a new Langfuse test environment with the given configuration.
     *
     * @param config the container configuration
     */
    public LangfuseContainer(LangfuseContainerConfig config) {
        super(config.langfuse().image());
        this.config = config;
        this.network = Network.newNetwork();

        var pgConfig = config.postgres();
        this.postgres = new PostgreSQLContainer<>(pgConfig.image())
                .withNetwork(network)
                .withNetworkAliases(POSTGRES_ALIAS)
                .withLabel(SERVICE_LABEL, "postgres")
                .withUsername(pgConfig.username())
                .withPassword(pgConfig.password())
                .withDatabaseName(pgConfig.databaseName());
        pgConfig.containerEnv().forEach(postgres::withEnv);

        var chConfig = config.clickhouse();
        this.clickhouse = new ClickHouseContainer(chConfig.image())
                .withNetwork(network)
                .withNetworkAliases(CLICKHOUSE_ALIAS)
                .withLabel(SERVICE_LABEL, "clickhouse")
                .withUsername(chConfig.username())
                .withPassword(chConfig.password())
                .withDatabaseName(chConfig.databaseName());
        chConfig.containerEnv().forEach(clickhouse::withEnv);

        var redisConfig = config.redis();
        this.redis = new RedisContainer(redisConfig.image())
                .withNetwork(network)
                .withNetworkAliases(REDIS_ALIAS)
                .withLabel(SERVICE_LABEL, "redis")
                .withCommand("--requirepass", redisConfig.password(), "--maxmemory-policy", "noeviction");
        redisConfig.containerEnv().forEach(redis::withEnv);

        var minioConfig = config.minio();
        this.minio = new MinIOContainer(
          DockerImageName.parse(minioConfig.image())
                         .asCompatibleSubstituteFor("minio/minio"))
                .withNetwork(network)
                .withNetworkAliases(MINIO_ALIAS)
                .withLabel(SERVICE_LABEL, "minio")
                .withEnv("MINIO_ROOT_USER", minioConfig.rootUser())
                .withEnv("MINIO_ROOT_PASSWORD", minioConfig.rootPassword())
                .withCommand("server", "--address", ":9000", "--console-address", ":9001", "/data");
        minioConfig.containerEnv().forEach(minio::withEnv);

        var workerConfig = config.worker();
        this.worker = new GenericContainer<>(workerConfig.image())
                .withNetwork(network)
                .withLabel(SERVICE_LABEL, "worker");
        workerConfig.containerEnv().forEach(worker::withEnv);

        configureWorker();
    }

    @Override
    protected void configure() {
        var langfuseConfig = config.langfuse();
        var pgConfig = config.postgres();
        var chConfig = config.clickhouse();
        var redisConfig = config.redis();
        var minioConfig = config.minio();

        withNetwork(network);
        withLabel(SERVICE_LABEL, "langfuse");
        withExposedPorts(langfuseConfig.port());

        withEnv("DATABASE_URL", "postgresql://%s:%s@%s:5432/%s".formatted(
                pgConfig.username(), pgConfig.password(), POSTGRES_ALIAS, pgConfig.databaseName()));
        withEnv("CLICKHOUSE_MIGRATION_URL", "clickhouse://%s:9000".formatted(CLICKHOUSE_ALIAS));
        withEnv("CLICKHOUSE_URL", "http://%s:8123".formatted(CLICKHOUSE_ALIAS));
        withEnv("CLICKHOUSE_USER", chConfig.username());
        withEnv("CLICKHOUSE_PASSWORD", chConfig.password());
        withEnv("CLICKHOUSE_CLUSTER_ENABLED", "false");

        withEnv("REDIS_HOST", REDIS_ALIAS);
        withEnv("REDIS_PORT", "6379");
        withEnv("REDIS_AUTH", redisConfig.password());
        withEnv("REDIS_TLS_ENABLED", String.valueOf(redisConfig.tlsEnabled()));

        withEnv("LANGFUSE_S3_EVENT_UPLOAD_BUCKET", minioConfig.bucketName());
        withEnv("LANGFUSE_S3_EVENT_UPLOAD_REGION", "auto");
        withEnv("LANGFUSE_S3_EVENT_UPLOAD_ACCESS_KEY_ID", minioConfig.rootUser());
        withEnv("LANGFUSE_S3_EVENT_UPLOAD_SECRET_ACCESS_KEY", minioConfig.rootPassword());
        withEnv("LANGFUSE_S3_EVENT_UPLOAD_ENDPOINT", "http://%s:9000".formatted(MINIO_ALIAS));
        withEnv("LANGFUSE_S3_EVENT_UPLOAD_FORCE_PATH_STYLE", "true");
        withEnv("LANGFUSE_S3_EVENT_UPLOAD_PREFIX", "events/");

        withEnv("LANGFUSE_S3_MEDIA_UPLOAD_BUCKET", minioConfig.bucketName());
        withEnv("LANGFUSE_S3_MEDIA_UPLOAD_REGION", "auto");
        withEnv("LANGFUSE_S3_MEDIA_UPLOAD_ACCESS_KEY_ID", minioConfig.rootUser());
        withEnv("LANGFUSE_S3_MEDIA_UPLOAD_SECRET_ACCESS_KEY", minioConfig.rootPassword());
        withEnv("LANGFUSE_S3_MEDIA_UPLOAD_ENDPOINT", "http://%s:9000".formatted(MINIO_ALIAS));
        withEnv("LANGFUSE_S3_MEDIA_UPLOAD_FORCE_PATH_STYLE", "true");
        withEnv("LANGFUSE_S3_MEDIA_UPLOAD_PREFIX", "media/");

        withEnv("LANGFUSE_S3_BATCH_EXPORT_ENABLED", String.valueOf(langfuseConfig.batchExportEnabled()));
        withEnv("LANGFUSE_S3_BATCH_EXPORT_BUCKET", minioConfig.bucketName());
        withEnv("LANGFUSE_S3_BATCH_EXPORT_PREFIX", "exports/");
        withEnv("LANGFUSE_S3_BATCH_EXPORT_REGION", "auto");
        withEnv("LANGFUSE_S3_BATCH_EXPORT_ENDPOINT", "http://%s:9000".formatted(MINIO_ALIAS));
        withEnv("LANGFUSE_S3_BATCH_EXPORT_EXTERNAL_ENDPOINT", "http://localhost:9090");
        withEnv("LANGFUSE_S3_BATCH_EXPORT_ACCESS_KEY_ID", minioConfig.rootUser());
        withEnv("LANGFUSE_S3_BATCH_EXPORT_SECRET_ACCESS_KEY", minioConfig.rootPassword());
        withEnv("LANGFUSE_S3_BATCH_EXPORT_FORCE_PATH_STYLE", "true");

        withEnv("LANGFUSE_ENABLE_EXPERIMENTAL_FEATURES", String.valueOf(langfuseConfig.enableExperimentalFeatures()));
        withEnv("LANGFUSE_USE_AZURE_BLOB", "false");
        withEnv("LANGFUSE_USE_OCI_NATIVE_OBJECT_STORAGE", "false");
        withEnv("LANGFUSE_OCI_AUTH_TYPE", "workload_identity");
        langfuseConfig.ingestionQueueDelay()
                .ifPresent(d -> withEnv("LANGFUSE_INGESTION_QUEUE_DELAY_MS", String.valueOf(d.toMillis())));
        langfuseConfig.ingestionClickhouseWriteInterval()
                .ifPresent(d -> withEnv("LANGFUSE_INGESTION_CLICKHOUSE_WRITE_INTERVAL_MS", String.valueOf(d.toMillis())));

        withEnv("EMAIL_FROM_ADDRESS", langfuseConfig.emailFromAddress());
        withEnv("SMTP_CONNECTION_URL", langfuseConfig.smtpConnectionUrl());

        withEnv("NEXTAUTH_URL", "http://localhost:3000");
        withEnv("NEXTAUTH_SECRET", "mysecret");
        withEnv("SALT", "mysalt");
        withEnv("ENCRYPTION_KEY", "0000000000000000000000000000000000000000000000000000000000000000");
        withEnv("TELEMETRY_ENABLED", "false");

        withEnv("LANGFUSE_INIT_ORG_ID", langfuseConfig.initOrgId());
        withEnv("LANGFUSE_INIT_ORG_NAME", langfuseConfig.initOrgName());
        withEnv("LANGFUSE_INIT_PROJECT_ID", langfuseConfig.initProjectId());
        withEnv("LANGFUSE_INIT_PROJECT_NAME", langfuseConfig.initProjectName());
        withEnv("LANGFUSE_INIT_PROJECT_PUBLIC_KEY", langfuseConfig.initProjectPublicKey());
        withEnv("LANGFUSE_INIT_PROJECT_SECRET_KEY", langfuseConfig.initProjectSecretKey());
        withEnv("LANGFUSE_INIT_USER_EMAIL", langfuseConfig.initUserEmail());
        withEnv("LANGFUSE_INIT_USER_NAME", langfuseConfig.initUserName());
        withEnv("LANGFUSE_INIT_USER_PASSWORD", langfuseConfig.initUserPassword());

        langfuseConfig.containerEnv().forEach(this::withEnv);

        waitingFor(new HttpWaitStrategy()
                .forPort(langfuseConfig.port())
                .forPath("/api/public/health")
                .forStatusCode(200)
                .withStartupTimeout(langfuseConfig.startupTimeout()));
    }

    @Override
    public void start() {
        Startables.deepStart(postgres, clickhouse, redis, minio)
                .thenRun(this::createMinioBucket)
                .thenCompose(v -> CompletableFuture.allOf(
                        CompletableFuture.runAsync(super::start),
                        CompletableFuture.runAsync(worker::start)))
                .join();
    }

    @Override
    public void stop() {
        super.stop();
        worker.stop();
        postgres.stop();
        clickhouse.stop();
        redis.stop();
        minio.stop();
        network.close();
    }

    /**
     * Returns the full Langfuse URL (e.g., {@code http://localhost:32768}).
     *
     * @return the Langfuse URL
     */
    public String getLangfuseUrl() {
        return "http://%s:%d".formatted(getHost(), getMappedPort(config.langfuse().port()));
    }

    /**
     * Returns the pre-configured public API key.
     *
     * @return the public key
     */
    public String getPublicKey() {
        return config.langfuse().initProjectPublicKey();
    }

    /**
     * Returns the pre-configured secret API key.
     *
     * @return the secret key
     */
    public String getSecretKey() {
        return config.langfuse().initProjectSecretKey();
    }

    /**
     * Returns the logs from all containers in the Langfuse environment.
     *
     * @return a map of container name to log output
     */
    public Map<String, String> getAllLogs() {
        return Map.of(
                "langfuse-web", getLogs(),
                "langfuse-worker", worker.getLogs(),
                "postgres", postgres.getLogs(),
                "clickhouse", clickhouse.getLogs(),
                "redis", redis.getLogs(),
                "minio", minio.getLogs()
        );
    }

    private void configureWorker() {
        var langfuseConfig = config.langfuse();
        var pgConfig = config.postgres();
        var chConfig = config.clickhouse();
        var redisConfig = config.redis();
        var minioConfig = config.minio();

        worker.withEnv("DATABASE_URL", "postgresql://%s:%s@%s:5432/%s".formatted(
                pgConfig.username(), pgConfig.password(), POSTGRES_ALIAS, pgConfig.databaseName()));
        worker.withEnv("CLICKHOUSE_MIGRATION_URL", "clickhouse://%s:9000".formatted(CLICKHOUSE_ALIAS));
        worker.withEnv("CLICKHOUSE_URL", "http://%s:8123".formatted(CLICKHOUSE_ALIAS));
        worker.withEnv("CLICKHOUSE_USER", chConfig.username());
        worker.withEnv("CLICKHOUSE_PASSWORD", chConfig.password());
        worker.withEnv("CLICKHOUSE_CLUSTER_ENABLED", "false");

        worker.withEnv("REDIS_HOST", REDIS_ALIAS);
        worker.withEnv("REDIS_PORT", "6379");
        worker.withEnv("REDIS_AUTH", redisConfig.password());
        worker.withEnv("REDIS_TLS_ENABLED", String.valueOf(redisConfig.tlsEnabled()));

        worker.withEnv("LANGFUSE_S3_EVENT_UPLOAD_BUCKET", minioConfig.bucketName());
        worker.withEnv("LANGFUSE_S3_EVENT_UPLOAD_REGION", "auto");
        worker.withEnv("LANGFUSE_S3_EVENT_UPLOAD_ACCESS_KEY_ID", minioConfig.rootUser());
        worker.withEnv("LANGFUSE_S3_EVENT_UPLOAD_SECRET_ACCESS_KEY", minioConfig.rootPassword());
        worker.withEnv("LANGFUSE_S3_EVENT_UPLOAD_ENDPOINT", "http://%s:9000".formatted(MINIO_ALIAS));
        worker.withEnv("LANGFUSE_S3_EVENT_UPLOAD_FORCE_PATH_STYLE", "true");
        worker.withEnv("LANGFUSE_S3_EVENT_UPLOAD_PREFIX", "events/");

        worker.withEnv("LANGFUSE_S3_MEDIA_UPLOAD_BUCKET", minioConfig.bucketName());
        worker.withEnv("LANGFUSE_S3_MEDIA_UPLOAD_REGION", "auto");
        worker.withEnv("LANGFUSE_S3_MEDIA_UPLOAD_ACCESS_KEY_ID", minioConfig.rootUser());
        worker.withEnv("LANGFUSE_S3_MEDIA_UPLOAD_SECRET_ACCESS_KEY", minioConfig.rootPassword());
        worker.withEnv("LANGFUSE_S3_MEDIA_UPLOAD_ENDPOINT", "http://%s:9000".formatted(MINIO_ALIAS));
        worker.withEnv("LANGFUSE_S3_MEDIA_UPLOAD_FORCE_PATH_STYLE", "true");
        worker.withEnv("LANGFUSE_S3_MEDIA_UPLOAD_PREFIX", "media/");

        worker.withEnv("LANGFUSE_S3_BATCH_EXPORT_ENABLED", String.valueOf(langfuseConfig.batchExportEnabled()));
        worker.withEnv("LANGFUSE_S3_BATCH_EXPORT_BUCKET", minioConfig.bucketName());
        worker.withEnv("LANGFUSE_S3_BATCH_EXPORT_PREFIX", "exports/");
        worker.withEnv("LANGFUSE_S3_BATCH_EXPORT_REGION", "auto");
        worker.withEnv("LANGFUSE_S3_BATCH_EXPORT_ENDPOINT", "http://%s:9000".formatted(MINIO_ALIAS));
        worker.withEnv("LANGFUSE_S3_BATCH_EXPORT_EXTERNAL_ENDPOINT", "http://localhost:9090");
        worker.withEnv("LANGFUSE_S3_BATCH_EXPORT_ACCESS_KEY_ID", minioConfig.rootUser());
        worker.withEnv("LANGFUSE_S3_BATCH_EXPORT_SECRET_ACCESS_KEY", minioConfig.rootPassword());
        worker.withEnv("LANGFUSE_S3_BATCH_EXPORT_FORCE_PATH_STYLE", "true");

        worker.withEnv("LANGFUSE_ENABLE_EXPERIMENTAL_FEATURES", String.valueOf(langfuseConfig.enableExperimentalFeatures()));
        worker.withEnv("LANGFUSE_USE_AZURE_BLOB", "false");
        worker.withEnv("LANGFUSE_USE_OCI_NATIVE_OBJECT_STORAGE", "false");
        worker.withEnv("LANGFUSE_OCI_AUTH_TYPE", "workload_identity");
        langfuseConfig.ingestionQueueDelay()
                .ifPresent(d -> worker.withEnv("LANGFUSE_INGESTION_QUEUE_DELAY_MS", String.valueOf(d.toMillis())));
        langfuseConfig.ingestionClickhouseWriteInterval()
                .ifPresent(d -> worker.withEnv("LANGFUSE_INGESTION_CLICKHOUSE_WRITE_INTERVAL_MS", String.valueOf(d.toMillis())));

        worker.withEnv("EMAIL_FROM_ADDRESS", langfuseConfig.emailFromAddress());
        worker.withEnv("SMTP_CONNECTION_URL", langfuseConfig.smtpConnectionUrl());

        worker.withEnv("NEXTAUTH_URL", "http://localhost:3000");
        worker.withEnv("SALT", "mysalt");
        worker.withEnv("ENCRYPTION_KEY", "0000000000000000000000000000000000000000000000000000000000000000");
        worker.withEnv("TELEMETRY_ENABLED", "false");
    }

    private void createMinioBucket() {
        try {
            minio.execInContainer("mkdir", "-p", "/data/" + config.minio().bucketName());
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create MinIO bucket", e);
        }
    }
}
