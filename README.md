# langfuse-java

This repository contains an auto-generated Langfuse API client for Java based on our [API specification](https://github.com/langfuse/langfuse/tree/main/fern/apis/server).
See the [Langfuse API reference](https://api.reference.langfuse.com) for more details on the available endpoints.

## Installation

Add the langfuse-java API client as a dependency using 
```xml
<dependency>
    <groupId>com.langfuse</groupId>
    <artifactId>langfuse-java</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
to fetch our [GitHub package](https://github.com/langfuse/langfuse-java/packages/2423464).

If you're not scanning the GitHub Package Registry by default, you'll have to add
```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub Package Registry</name>
        <url>https://maven.pkg.github.com/langfuse/langfuse-java</url>
    </repository>
</repositories>
```
as well.

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
