# langfuse-java

This repository contains an auto-generated Langfuse API client for Java based on our [API specification](https://github.com/langfuse/langfuse/tree/main/fern/apis/server).
See the [Langfuse API reference](https://api.reference.langfuse.com) for more details on the available endpoints.

We recommend to solve tracing via the [OpenTelemetry Instrumentation](https://langfuse.com/docs/opentelemetry/get-started) instead of using the Ingestion API directly.
This allows for a more detailed and standardized tracing experience without the need to handle batching and updates internally.
Check out our [Spring AI Example](https://langfuse.com/docs/integrations/spring-ai) for more details.

## Installation

### Maven Central

The recommended way to install the langfuse-java API client is via Maven Central:

```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java</artifactId>
    <version>0.0.4</version>
</dependency>
```

### GitHub Packages

Alternatively, you can use GitHub Packages:

```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java</artifactId>
    <version>0.0.4</version>
</dependency>
```

If you're not scanning the GitHub Package Registry by default, you'll have to add:

```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub Package Registry</name>
        <url>https://maven.pkg.github.com/langfuse/langfuse-java</url>
    </repository>
</repositories>
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

Make requests using the clients:

```java
import com.langfuse.client.core.LangfuseClientApiException;
import com.langfuse.client.resources.prompts.types.PromptMetaListResponse;

try {
    PromptMetaListResponse prompts = client.prompts().list();
} catch (LangfuseClientApiException error) {
    System.out.println(error.getBody());
    System.out.println(error.getStatusCode());
}
```

## Drafting a Release

Run `./mvnw release:prepare -DreleaseVersion=` with the version you want to create.
Push the changes including the tag.

## Publishing to Maven Central and GitHub Packages

This project is configured to publish to both Maven Central and GitHub Packages. The GitHub Actions workflow will automatically publish to both repositories when a new tag is pushed.

### Required Secrets

To publish to Maven Central, you need to configure the following secrets in your GitHub repository:

- `OSSRH_USERNAME`: Your Sonatype OSSRH username
- `OSSRH_PASSWORD`: Your Sonatype OSSRH password
- `GPG_PRIVATE_KEY`: Your GPG private key for signing artifacts
- `GPG_PASSPHRASE`: The passphrase for your GPG private key

### Setting up GPG for Signing

1. Install GPG if you don't have it already:
   ```bash
   # macOS
   brew install gnupg

   # Ubuntu
   sudo apt-get install gnupg
   ```

2. Generate a GPG key pair:
   ```bash
   gpg --full-generate-key
   ```

3. List your keys:
   ```bash
   gpg --list-secret-keys --keyid-format LONG
   ```

4. Export your private key (replace `KEY_ID` with your key ID):
   ```bash
   gpg --export-secret-keys --armor KEY_ID
   ```

5. Add the exported key to your GitHub repository secrets as `GPG_PRIVATE_KEY`
6. Add your GPG passphrase to your GitHub repository secrets as `GPG_PASSPHRASE`

### Setting up Sonatype OSSRH

1. Create an account on [Sonatype OSSRH](https://s01.oss.sonatype.org/)
2. Create a new project ticket in the [Sonatype JIRA](https://issues.sonatype.org/)
3. Add your Sonatype username and password to your GitHub repository secrets as `OSSRH_USERNAME` and `OSSRH_PASSWORD`

## Updating

1. Ensure that langfuse-java is placed in the same directory as the main [langfuse](https://github.com/langfuse/langfuse) repository.
2. Setup a new Java fern generator using
   ```yaml
      - name: fernapi/fern-java-sdk
        version: 2.20.1
        output:
          location: local-file-system
          path: ../../../../langfuse-java/src/main/java/com/langfuse/client/
        config:
          client-class-name: LangfuseClient
   ```
3. Generate the new client code using `npx fern-api generate --api server`.
4. Manually set the `package` across all files to `com.langfuse.client`.
5. Overwrite `this.clientOptionsBuilder.addHeader("Authorization", "Bearer " + encodedToken);` to `Basic` in LangfuseClientBuilder.java.
6. Commit the changes in langfuse-java and push them to the repository.
