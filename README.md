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

## Updating

1. Ensure that langfuse-java is placed in the same directory as the main [langfuse](https://github.com/langfuse/langfuse) repository.
2. Setup a new Java fern generator using
   ```yaml
      - name: fernapi/fern-java-sdk
        version: 2.20.1
        output:
          location: local-file-system
          path: ../../../../langfuse-java/src/main/java/com/langfuse/
   ```
3. Generate the new client code using `npx fern-api generate --api server`.
4. Commit the changes in langfuse-java and push them to the repository.
