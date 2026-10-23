package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class OpentelemetryApiJackson3Test extends OpentelemetryApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
