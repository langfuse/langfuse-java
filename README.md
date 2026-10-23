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

## Langfuse Version Compatibility

This SDK targets **Langfuse v4** (`events_only` write mode). See [Migrating from Langfuse v3 to v4](#migrating-from-langfuse-v3-to-v4) for details on breaking changes and API replacements.

## Requirements

- Java 17+
- Maven 3.8.1+

## Quick Start

Add the client dependency to your project:

```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java-client</artifactId>
    <version>0.3.1-SNAPSHOT</version>
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

### Ingest a trace (via OpenTelemetry)

Langfuse v4 uses the OpenTelemetry endpoint for trace ingestion:

```java
var traceId = UUID.randomUUID().toString().replace("-", "");
var spanId = traceId.substring(0, 16);
var nowNanos = String.valueOf(System.currentTimeMillis() * 1_000_000L);

langfuse.opentelemetry().opentelemetryExportTraces(
        OpentelemetryApi.APIOpentelemetryExportTracesRequest.newBuilder()
                .opentelemetryExportTracesRequest(OpentelemetryExportTracesRequest.builder()
                        .resourceSpans(List.of(OtelResourceSpan.builder()
                                .resource(OtelResource.builder()
                                        .attributes(List.of(
                                                OtelAttribute.builder()
                                                        .key("langfuse.trace.name")
                                                        .value(OtelAttributeValue.builder()
                                                                .stringValue("my-trace")
                                                                .build())
                                                        .build(),
                                                OtelAttribute.builder()
                                                        .key("langfuse.trace.user.id")
                                                        .value(OtelAttributeValue.builder()
                                                                .stringValue("user-123")
                                                                .build())
                                                        .build()))
                                        .build())
                                .scopeSpans(List.of(OtelScopeSpan.builder()
                                        .scope(OtelScope.builder().name("my-service").build())
                                        .spans(List.of(OtelSpan.builder()
                                                .traceId(traceId)
                                                .spanId(spanId)
                                                .name("root-span")
                                                .kind(1)
                                                .startTimeUnixNano(nowNanos)
                                                .endTimeUnixNano(nowNanos)
                                                .build()))
                                        .build()))
                                .build()))
                        .build())
                .build());
```

### Query observations

```java
var observations = langfuse.observations().observationsGetMany(
        ObservationsApi.APIObservationsGetManyRequest.newBuilder()
                .traceId(traceId)
                .fields("core,basic,usage")
                .build());

observations.getData().forEach(obs ->
        System.out.println(obs.getId() + ": " + obs.getName()));
```

### Check health

```java
var health = langfuse.health().healthHealth();
System.out.println("Status: " + health.getStatus());     // OK
System.out.println("Version: " + health.getVersion());    // 4.x.x
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
    <version>0.3.1-SNAPSHOT</version>
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

## Migrating from Langfuse v3 to v4

This SDK was updated from Langfuse v3 to v4 (`events_only` write mode). If you are upgrading from a v3-based version, the following changes apply.

### Infrastructure

The Langfuse container images have been updated:

| Component | v3 | v4 |
|---|---|---|
| Langfuse Web | `langfuse/langfuse:3` | `langfuse/langfuse:4` |
| Langfuse Worker | `langfuse/langfuse-worker:3` | `langfuse/langfuse-worker:4` |
| ClickHouse | `clickhouse/clickhouse-server` (untagged) | `clickhouse/clickhouse-server:25.12` (minimum) |

Langfuse v4 requires ClickHouse 25.12+, PostgreSQL 15+, and Redis 7.0+.

### Ingestion: legacy batch API replaced by OpenTelemetry

The legacy batch ingestion endpoint (`POST /api/public/ingestion` with `TRACE_CREATE`, `SPAN_CREATE`, `GENERATION_CREATE`, etc.) returns **400** in `events_only` mode. All trace ingestion must use the OpenTelemetry endpoint:

| v3 | v4 |
|---|---|
| `langfuse.ingestion().ingestionBatch(...)` | `langfuse.opentelemetry().opentelemetryExportTraces(...)` |

Key differences in the OTel format:
- **Trace IDs** are 32-character hex strings (no dashes): `UUID.randomUUID().toString().replace("-", "")`
- **Span IDs** are 16-character hex strings: `traceId.substring(0, 16)`
- **Timestamps** are nanoseconds since epoch as strings: `String.valueOf(System.currentTimeMillis() * 1_000_000L)`
- **Trace metadata** (name, user ID, session ID) is set via OTel resource attributes prefixed with `langfuse.trace.`:
  - `langfuse.trace.name` -- trace name
  - `langfuse.trace.user.id` -- user ID
  - `langfuse.trace.session.id` -- session ID

### Read APIs: legacy endpoints replaced by v2/v3

Several legacy read endpoints return **404** in `events_only` mode. Use the v4 replacements:

| Legacy Endpoint (404 in v4) | v4 Replacement |
|---|---|
| `trace().traceGet(...)` / `traceList(...)` | `observations().observationsGetMany(...)` (v2, with `traceId` filter) |
| `sessions().sessionsGet(...)` / `sessionsList(...)` | `observations().observationsGetMany(...)` (v2, with `traceId` filter) |
| `scores().scoresGetMany(...)` (v2) | `scoresV3().scoresV3GetManyV3(...)` |
| `legacyObservationsV1().legacyObservationsV1GetMany(...)` | `observations().observationsGetMany(...)` (v2) |
| `legacyMetricsV1().legacyMetricsV1Metrics(...)` | `metrics().metricsGetMany(...)` (v2) |

The v2 observations endpoint uses cursor-based pagination (not page-based) and supports field selection via the `fields` parameter (`core`, `basic`, `time`, `io`, `metadata`, `model`, `usage`, `prompt`, `metrics`, `trace_context`).

### Score creation: renamed types

The score creation API was re-tagged from `LegacyScoreV1` to `Scores`, and the request/response types were renamed:

| v3 | v4 |
|---|---|
| `LegacyCreateScoreRequest` | `CreateScoreRequest` |
| `LegacyCreateScoreSource` | `CreateScoreSource` |
| `LegacyCreateScoreResponse` | `CreateScoreResponse` |
| `legacyScoreV1().legacyScoreV1Create(...)` | `scores().scoresCreate(...)` |

### New APIs in v4

The following API groups are new in v4:

| API | Accessor | Description |
|---|---|---|
| Evaluators | `evaluators()` | CRUD for LLM-as-judge and code evaluators (v2) |
| Evaluation Rules | `evaluationRules()` | CRUD for evaluation rule assignments (v2) |
| Experiments | `experiments()` | List experiments and experiment items |
| Feedback | `feedback()` | Submit feedback (cloud-hosted only) |
| Scores V3 | `scoresV3()` | Query scores with polymorphic `value` field and cursor pagination |
| Unstable Dashboards | `unstableDashboards()` | CRUD for custom dashboards |
| Unstable Dashboard Widgets | `unstableDashboardWidgets()` | CRUD for dashboard widgets |

### OpenAPI spec: `const` keyword incompatibility

The v4 OpenAPI spec uses the JSON Schema `const` keyword in the `unstableCodeEvaluationRuleEvaluatorReference` schema. This keyword is not supported by [openapi-generator](https://openapi-generator.tech/), so spec validation is disabled (`<skipValidateSpec>true</skipValidateSpec>`) in the Maven plugin configuration. The `const` keyword is silently ignored during code generation -- the generated field is typed as a plain `String` rather than a single-value enum. This does not affect runtime behavior since the server enforces the constraint.

## License

[MIT](LICENSE)
