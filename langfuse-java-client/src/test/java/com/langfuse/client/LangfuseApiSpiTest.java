package com.langfuse.client;

import static org.assertj.core.api.Assertions.assertThat;

import com.langfuse.api.LangfuseApi;

import org.junit.jupiter.api.Test;

class LangfuseApiSpiTest {

    @Test
    void serviceLoaderDiscoversFactory() {
        LangfuseApi api = LangfuseApi.builder()
                .username("test-pk")
                .password("test-sk")
                .url("http://localhost:3000")
                .build();

        assertThat(api).isInstanceOf(LangfuseClient.class);
        assertThat(api.health()).isNotNull();
    }
}
