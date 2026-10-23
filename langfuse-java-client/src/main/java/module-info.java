module com.langfuse.client {
    requires transitive com.langfuse.api;
    requires transitive org.slf4j;
    requires static org.jspecify;
    requires static jakarta.annotation;
    requires static com.fasterxml.jackson.core;
    requires static com.fasterxml.jackson.databind;
    requires static com.fasterxml.jackson.datatype.jsr310;
    requires static tools.jackson.core;
    requires static tools.jackson.databind;
    requires java.net.http;

    exports com.langfuse.client;

    provides com.langfuse.api.spi.LangfuseApiBuilderFactory
        with com.langfuse.client.LangfuseClientBuilderFactory;
}
