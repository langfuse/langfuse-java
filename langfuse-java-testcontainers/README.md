# langfuse-java-testcontainers

[Testcontainers](https://testcontainers.com) support for the Langfuse Java SDK. Starts a complete Langfuse environment for integration testing, including all required infrastructure services.

## Maven Coordinates

```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java-testcontainers</artifactId>
    <version>0.2.1-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

## What Gets Started

`LangfuseContainer` orchestrates 6 Docker containers:

| Service | Default Image | Purpose |
|---------|--------------|---------|
| PostgreSQL | `postgres:17` | Primary database |
| ClickHouse | `clickhouse/clickhouse-server` | Analytics data store |
| Redis | `redis:7` | Queue and cache |
| MinIO | `cgr.dev/chainguard/minio` | S3-compatible object storage |
| Langfuse Web | `langfuse/langfuse:3` | API server |
| Langfuse Worker | `langfuse/langfuse-worker:3` | Async event processing |

Infrastructure containers start in parallel, then the web server and worker start in parallel after infrastructure is ready.

## Quick Start

```java
var langfuse = new LangfuseContainer();
langfuse.start();

String url = langfuse.getLangfuseUrl();         // http://localhost:<port>
String publicKey = langfuse.getPublicKey();      // pk-lf-dev
String secretKey = langfuse.getSecretKey();      // sk-lf-dev
```

### With the client

```java
var client = LangfuseApi.builder()
        .username(langfuse.getPublicKey())
        .password(langfuse.getSecretKey())
        .url(langfuse.getLangfuseUrl())
        .build();

var health = client.health().healthHealth();
```

## Singleton Pattern

For test suites with multiple test classes, use the [Testcontainers singleton pattern](https://testcontainers.com/guides/testcontainers-container-lifecycle/#_using_singleton_containers) to start the container once per JVM:

```java
abstract class AbstractIntegrationTest {
    static LangfuseContainer langfuse = new LangfuseContainer();
    static LangfuseApi client;

    static {
        langfuse.start();
        client = LangfuseApi.builder()
                .username(langfuse.getPublicKey())
                .password(langfuse.getSecretKey())
                .url(langfuse.getLangfuseUrl())
                .build();
    }
}
```

Do **not** use `@Testcontainers` or `@Container` annotations with the singleton pattern -- they would stop the container after each test class.

## Configuration

Customize the environment using the builder API:

```java
var langfuse = new LangfuseContainer(
        LangfuseContainerConfig.builder()
                .langfuse()
                    .image("docker.io/langfuse/langfuse:3")
                    .port(3000)
                    .startupTimeout(Duration.ofMinutes(5))
                    .initProjectPublicKey("my-public-key")
                    .initProjectSecretKey("my-secret-key")
                    .initOrgName("My Org")
                    .initProjectName("My Project")
                    .ingestionQueueDelay(Duration.ofMillis(100))
                    .ingestionClickhouseWriteInterval(Duration.ofMillis(500))
                    .and()
                .postgres()
                    .image("postgres:16")
                    .username("myuser")
                    .password("mypass")
                    .databaseName("langfuse")
                    .and()
                .clickhouse()
                    .username("default")
                    .password("secret")
                    .and()
                .redis()
                    .password("redis-secret")
                    .and()
                .minio()
                    .rootUser("minio-user")
                    .rootPassword("minio-secret")
                    .bucketName("my-bucket")
                    .and()
                .build());
```

### Defaults

All defaults are aligned with the project's `docker-compose.yml`:

| Setting | Default |
|---------|---------|
| Langfuse image | `docker.io/langfuse/langfuse:3` |
| Langfuse port | `3000` |
| Startup timeout | 3 minutes |
| Public key | `pk-lf-dev` |
| Secret key | `sk-lf-dev` |
| PostgreSQL | `postgres:17`, user/pass `postgres/postgres` |
| ClickHouse | `clickhouse/clickhouse-server`, user/pass `clickhouse/clickhouse` |
| Redis | `redis:7`, password `myredissecret` |
| MinIO | `cgr.dev/chainguard/minio`, user/pass `minio/miniosecret` |

## Diagnostics

On test failure, retrieve logs from all containers:

```java
Map<String, String> logs = langfuse.getAllLogs();
logs.forEach((container, log) ->
        System.err.println("=== " + container + " ===\n" + log));
```

Returns a map with keys: `langfuse-web`, `langfuse-worker`, `postgres`, `clickhouse`, `redis`, `minio`.

## Podman

If using Podman instead of Docker, set `service_timeout=0` in the Podman machine to avoid connection drops when starting multiple containers:

```bash
podman machine ssh "sudo sh -c 'echo -e \"[engine]\nservice_timeout=0\" > /etc/containers/containers.conf'"
podman machine stop && podman machine start
```
