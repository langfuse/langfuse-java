# langfuse-java

This repository contains an auto-generated Langfuse API client for Java based on our [API specification](https://github.com/langfuse/langfuse/tree/main/fern/apis/server).
See the [Langfuse API reference](https://api.reference.langfuse.com) for more details on the available endpoints.

**Note:** We recommend to solve tracing via the [OpenTelemetry Instrumentation](https://langfuse.com/docs/opentelemetry/get-started) instead of using the Ingestion API directly. You can use the [OpenTelemetry Java SDK](https://github.com/open-telemetry/opentelemetry-java) and export spans to the [Langfuse OTel endpoint](https://langfuse.com/integrations/native/opentelemetry).
This allows for a more detailed and standardized tracing experience without the need to handle batching and updates internally.
Check out our [Spring AI Example](https://langfuse.com/docs/integrations/spring-ai) for more details.

## Installation

The recommended way to install the langfuse-java API client is via Maven Central:

```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java</artifactId>
    <version>0.1.3</version>
</dependency>
```

## Usage

Instantiate the Langfuse Client with the respective endpoint and your API Keys.

```java
import com.langfuse.client.LangfuseClient;

LangfuseClient client = LangfuseClient.builder()
        .url("https://cloud.langfuse.com") // 🇪🇺 EU data region
        // .url("https://us.cloud.langfuse.com") // 🇺🇸 US data region
        // .url("http://localhost:3000") // 🏠 Local deployment
        .credentials("pk-lf-...", "sk-lf-...")
        .build();
```

An async client is also available via `AsyncLangfuseClient.builder()` with the same configuration options.

Make requests using the clients:

```java
import com.langfuse.client.core.LangfuseClientApiException;
import com.langfuse.client.resources.prompts.types.PromptMetaListResponse;

try {
    PromptMetaListResponse prompts = client.prompts().list();
} catch (LangfuseClientApiException error) {
    System.out.println(error.body());
    System.out.println(error.statusCode());
}
```

## Testing

### Unit tests

Unit tests (deserialization, query string mapping) run without any credentials:

```bash
mvn test
```

### Integration tests

Integration tests connect to a real Langfuse project. They require credentials and are excluded from `mvn test`.

1. Copy `.env.example` to `.env` and fill in your API keys:
   ```bash
   cp .env.example .env
   ```

2. Ensure your Langfuse project contains the following prompts:
   - `test-chat-prompt` — chat type, at least one message with `role` and `content`
   - `test-text-prompt` — text type, non-empty prompt text

3. Run all tests (unit + integration):
   ```bash
   mvn verify
   ```

   Or run only integration tests:
   ```bash
   mvn failsafe:integration-test
   ```

Integration tests skip gracefully when credentials are absent.

## Drafting a Release

Run `./mvnw release:prepare -DreleaseVersion=` with the version you want to create.
Push the changes including the tag.

## Publishing to Maven Central

This project is configured to publish to Maven Central.
To publish to Maven Central, you need to configure the following secrets in your GitHub repository:

- `OSSRH_USERNAME`: Your Sonatype OSSRH username
- `OSSRH_PASSWORD`: Your Sonatype OSSRH password
- `GPG_PRIVATE_KEY`: Your GPG private key for signing artifacts
- `GPG_PASSPHRASE`: The passphrase for your GPG private key

## Updating

1. Ensure that langfuse-java is placed in the same directory as the main [langfuse](https://github.com/langfuse/langfuse) repository.
2. Setup a new Java fern generator using
   ```yaml
      - name: fernapi/fern-java-sdk
        version: 3.38.1
        output:
          location: local-file-system
          path: ../../../../langfuse-java/src/main/java/com/langfuse/client/
        config:
          client-class-name: LangfuseClient
   ```
3. Generate the new client code using `npx fern-api generate --api server`.
4. Manually set the `package` across all files to `com.langfuse.client`.
5. Verify that `LangfuseClientBuilder.setAuthentication()` uses `Basic` auth (not `Bearer`).
6. Adjust Javadoc strings with HTML properties as the apidocs package does not support them.
7. Commit the changes in langfuse-java and push them to the repository.
