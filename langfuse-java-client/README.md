# langfuse-java-client

Reference HTTP client implementation for the Langfuse Java SDK. Uses `java.net.http.HttpClient` for HTTP and supports both Jackson 2 and Jackson 3 for JSON serialization.

## Maven Coordinates

```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java-client</artifactId>
    <version>0.2.1-SNAPSHOT</version>
</dependency>
```

You also need a Jackson implementation on the classpath (provided scope):

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

When both are present, Jackson 3 is preferred.

## Usage

### Basic client

```java
var langfuse = LangfuseApi.builder()
        .username("pk-lf-...")
        .password("sk-lf-...")
        .url("https://cloud.langfuse.com")
        .build();
```

The `builder()` method uses `ServiceLoader` to discover the client implementation. You can also instantiate a specific Jackson version directly:

```java
// Jackson 2
var client = LangfuseJackson2Client.builder()
        .username("pk-lf-...")
        .password("sk-lf-...")
        .url("https://cloud.langfuse.com")
        .build();

// Jackson 3
var client = LangfuseJackson3Client.builder()
        .username("pk-lf-...")
        .password("sk-lf-...")
        .url("https://cloud.langfuse.com")
        .build();
```

### Custom ObjectMapper

```java
var client = LangfuseJackson2Client.builder()
        .username("pk-lf-...")
        .password("sk-lf-...")
        .url("https://cloud.langfuse.com")
        .objectMapper(myCustomObjectMapper)
        .build();
```

### Configuration options

See the [API module builder configuration](../langfuse-java-api/README.md#builder-configuration) for all available options (`username`, `password`, `url`, `readTimeout`, `logRequests`, `logResponses`, `prettyPrint`, etc.).

This module adds one additional option per Jackson version:

| Method | Description |
|--------|-------------|
| `objectMapper(ObjectMapper)` | Custom Jackson 2 ObjectMapper (on `LangfuseJackson2Client.Builder`) |
| `jsonMapper(JsonMapper)` | Custom Jackson 3 JsonMapper (on `LangfuseJackson3Client.Builder`) |

### Request/response logging

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

Log output uses SLF4J at `INFO` level. Sensitive headers (`Authorization`) are automatically masked:

```
-> REQUEST: POST https://cloud.langfuse.com/api/public/ingestion
  HEADERS:
  Authorization: **********************************
  Content-Type: application/json

-> REQUEST BODY:
{
  "batch" : [ ... ]
}

<- RESPONSE: 207
  HEADERS:
  content-type: application/json; charset=utf-8
  BODY:
{
  "successes" : [ ... ],
  "errors" : [ ]
}
```

## HTTP/1.1 Fallback

When the base URL uses `http` (not `https`), the client automatically falls back to HTTP/1.1 to avoid protocol negotiation issues with servers that don't support HTTP/2 upgrade over plain HTTP. HTTPS connections use HTTP/2 with ALPN negotiation.
