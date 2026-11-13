package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class OpentelemetryApiAsyncJackson3Test extends OpentelemetryApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
