# langfuse-java-api

API interfaces, generated model types, and SPI for the Langfuse Java SDK.

This module defines the public contract that client implementations must fulfill. It contains no HTTP or serialization logic -- only interfaces, model classes, and the `ServiceLoader`-based discovery mechanism.

## Maven Coordinates

```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java-api</artifactId>
    <version>0.2.1-SNAPSHOT</version>
</dependency>
```

## What's Inside

### LangfuseApi

The top-level interface providing access to all Langfuse API operations:

```java
var langfuse = LangfuseApi.builder()
        .username("pk-lf-...")
        .password("sk-lf-...")
        .url("https://cloud.langfuse.com")
        .build();

// Synchronous
var health = langfuse.health().healthHealth();

// Asynchronous
langfuse.asyncHealth().healthHealth()
        .thenAccept(h -> System.out.println(h.getStatus()));
```

Each API area has a sync and async accessor:

| Accessor | Async Accessor | Description |
|----------|---------------|-------------|
| `health()` | `asyncHealth()` | Health checks |
| `ingestion()` | `asyncIngestion()` | Batch event ingestion |
| `trace()` | `asyncTrace()` | Trace CRUD |
| `prompts()` | `asyncPrompts()` | Prompt management |
| `scores()` | `asyncScores()` | Score queries |
| `datasets()` | `asyncDatasets()` | Dataset management |
| `observations()` | `asyncObservations()` | Observation queries |
| `sessions()` | `asyncSessions()` | Session management |
| ... | ... | 28 API areas total |

### Builder Configuration

The `LangfuseApiBuilder` interface defines the configuration options available to all implementations:

| Method | Description |
|--------|-------------|
| `username(String)` | Langfuse public key (used for Basic auth) |
| `password(String)` | Langfuse secret key (used for Basic auth) |
| `url(String)` | Langfuse server base URL |
| `readTimeout(Duration)` | HTTP read timeout |
| `addHeader(String, String)` | Custom HTTP header sent with every request |
| `logRequests()` / `logRequests(boolean)` | Enable/disable HTTP request logging |
| `logResponses()` / `logResponses(boolean)` | Enable/disable HTTP response logging |
| `prettyPrint()` / `prettyPrint(boolean)` | Pretty-print JSON in logged requests and responses |

### Request Objects

API methods with parameters use request objects with builders, avoiding long parameter lists:

```java
var traces = langfuse.trace().traceList(
        TraceApi.APITraceListRequest.newBuilder()
                .name("my-trace")
                .userId("user-123")
                .limit(10)
                .build());
```

No-argument methods (e.g. `healthHealth()`) keep their direct signature.

### Generated Models

Model types are generated from the [Langfuse OpenAPI spec](https://cloud.langfuse.com/generated/api/openapi.yml) with:

- **Builders** on all model classes (`TraceBody.builder().name("...").build()`)
- **Protected constructors** -- all creation goes through builders
- **Bean Validation annotations** (`@NotNull`, `@Size`, etc.) from the OpenAPI schema
- **Dual Jackson 2 + 3 annotations** for serialization compatibility
- **Empty container defaults** -- Lists, Maps, and Sets initialize to empty, never null
- **`@JsonInclude(NON_EMPTY)`** -- empty optional containers are omitted from serialized JSON

### SPI

The `LangfuseApiBuilder` interface and `LangfuseApiBuilderFactory` SPI allow framework-specific implementations (e.g. Spring, Quarkus) to provide their own client without depending on `langfuse-java-client`.
