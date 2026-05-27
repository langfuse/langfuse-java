# Langfuse Java SDK

Java SDK for [Langfuse](https://langfuse.com) -- the open-source LLM engineering platform for tracing, evaluation, prompt management, and metrics.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Modules

| Module | Artifact | Description |
|--------|----------|-------------|
| [langfuse-java-api](langfuse-java-api/) | `com.langfuse:langfuse-java-api` | API interfaces, generated model types, and SPI |
| [langfuse-java-client](langfuse-java-client/) | `com.langfuse:langfuse-java-client` | Reference HTTP client (Jackson 2 & 3) |
| [langfuse-java-testcontainers](langfuse-java-testcontainers/) | `com.langfuse:langfuse-java-testcontainers` | Testcontainers support for integration testing |
| [langfuse-java-legacy](langfuse-java-legacy/) | `com.langfuse:langfuse-java` | Legacy fern-generated SDK (maintained for backward compatibility) |

- **[langfuse-java-api](langfuse-java-api/)** defines the public API contract -- interfaces, generated model types from the [OpenAPI spec](https://cloud.langfuse.com/generated/api/openapi.yml), request objects with builders, and the `ServiceLoader`-based SPI for pluggable client implementations.
- **[langfuse-java-client](langfuse-java-client/)** is the reference HTTP client built on `java.net.http.HttpClient`. It supports both Jackson 2 and Jackson 3, request/response logging with sensitive header masking, and automatic HTTP/1.1 fallback for plain HTTP connections.
- **[langfuse-java-testcontainers](langfuse-java-testcontainers/)** provides `LangfuseContainer` for spinning up a complete Langfuse environment (PostgreSQL, ClickHouse, Redis, MinIO, web server, and worker) in integration tests via [Testcontainers](https://testcontainers.com).
- **[langfuse-java-legacy](langfuse-java-legacy/)** is the original fern-generated SDK, preserved for backward compatibility. New projects should use `langfuse-java-client` instead. See its [README](langfuse-java-legacy/README.md) for usage.

## Design

The Langfuse upstream project uses [Fern](https://buildwithfern.com/) to generate its SDKs. Fern produces an [OpenAPI specification](https://cloud.langfuse.com/generated/api/openapi.yml) but its generated Java code is not customizable -- it lacks builder patterns, dual Jackson support, request parameter objects, and JPMS modules.

This SDK takes a different approach: it uses the Fern-generated OpenAPI spec as input to [openapi-generator](https://openapi-generator.tech/) with [custom Mustache templates](https://openapi-generator.tech/docs/templating) that produce the API interfaces, model types, and client implementations at build time. Almost all Java source in the `langfuse-java-api` and `langfuse-java-client` modules are generated during `mvn compile` -- only a handful of hand-coded classes provide the SPI wiring, Jackson version abstraction, and builder infrastructure.

Because everything is generated at build time, the generated API interfaces, model types, and client implementations are never checked into version control -- only the OpenAPI spec, custom Mustache templates, and hand-coded infrastructure classes are stored in the repository.

This means:
- Updating to a new Langfuse API version is a matter of dropping in the updated `openapi.yml` and rebuilding.
- The generated code includes builders, bean validation annotations, request parameter objects, dual Jackson 2/3 annotations, and `@JsonInclude(NON_EMPTY)` -- none of which the Fern-generated SDK provides.
- Framework integrations (Spring, Quarkus) can provide their own `LangfuseApiBuilderFactory` via `ServiceLoader` without depending on the reference client.
- The `langfuse-java-client` module includes a comprehensive integration test suite (sync and async) that uses the `langfuse-java-testcontainers` module to verify every API operation against a real Langfuse environment.

## Requirements

- Java 17+
- Maven 3.8.1+

## Quick Start

Add the client dependency to your project:

```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java-client</artifactId>
    <version>0.2.1-SNAPSHOT</version>
</dependency>
```

You also need a Jackson implementation on the classpath. The SDK supports both Jackson 2 and Jackson 3:

```xml
<!-- Jackson 2 -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>${jackson2.version}</version>
</dependency>

<!-- OR Jackson 3 -->
<dependency>
    <groupId>tools.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>${jackson3.version}</version>
</dependency>
```

### Create a client

```java
var langfuse = LangfuseApi.builder()
        .username("pk-lf-...")    // Langfuse public key
        .password("sk-lf-...")    // Langfuse secret key
        .url("https://cloud.langfuse.com")
        .build();
```

The builder uses `ServiceLoader` to discover the client implementation on the classpath.
When both Jackson 2 and Jackson 3 are present, Jackson 3 is preferred.

### Ingest a trace

```java
var response = langfuse.ingestion().ingestionBatch(
        IngestionApi.APIIngestionBatchRequest.newBuilder()
                .ingestionBatchRequest(IngestionBatchRequest.builder()
                        .batch(List.of(new IngestionEvent(IngestionEventOneOf.builder()
                                .id(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .type(IngestionEventOneOf.TypeEnum.TRACE_CREATE)
                                .body(TraceBody.builder()
                                        .id(UUID.randomUUID().toString())
                                        .name("my-trace")
                                        .userId("user-123")
                                        .build())
                                .build())))
                        .build())
                .build());
```

### Query traces

```java
var traces = langfuse.trace().traceList(
        TraceApi.APITraceListRequest.newBuilder()
                .name("my-trace")
                .limit(10)
                .build());

traces.getData().forEach(trace ->
        System.out.println(trace.getId() + ": " + trace.getName()));
```

### Check health

```java
var health = langfuse.health().healthHealth();
System.out.println("Status: " + health.getStatus());     // OK
System.out.println("Version: " + health.getVersion());    // 3.x.x
```

### Async API

Every synchronous API has an async counterpart that returns `CompletionStage`:

```java
langfuse.asyncHealth().healthHealth()
        .thenAccept(health -> System.out.println("Status: " + health.getStatus()));
```

### Request/Response logging

```java
var langfuse = LangfuseApi.builder()
        .username("pk-lf-...")
        .password("sk-lf-...")
        .url("https://cloud.langfuse.com")
        .logRequests()
        .logResponses()
        .prettyPrint()
        .build();
```

Sensitive headers (e.g. `Authorization`) are automatically masked in log output.

## Integration Testing with Testcontainers

Add the testcontainers module to your test dependencies:

```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java-testcontainers</artifactId>
    <version>0.2.1-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

Start a full Langfuse environment (PostgreSQL, ClickHouse, Redis, MinIO, Langfuse web + worker):

```java
var langfuse = new LangfuseContainer();
langfuse.start();

var client = LangfuseApi.builder()
        .username(langfuse.getPublicKey())
        .password(langfuse.getSecretKey())
        .url(langfuse.getLangfuseUrl())
        .build();
```

For sharing a single container across test classes, use the
[Testcontainers singleton pattern](https://testcontainers.com/guides/testcontainers-container-lifecycle/#_using_singleton_containers):

```java
abstract class AbstractIntegrationTest {
    static LangfuseContainer langfuse = new LangfuseContainer();

    static {
        langfuse.start();
    }
}
```

See the [testcontainers module README](langfuse-java-testcontainers/) for configuration options.

## Building

```bash
./mvnw clean verify
```

## License

[MIT](LICENSE)
