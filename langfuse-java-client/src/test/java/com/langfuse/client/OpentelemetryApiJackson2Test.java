package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class OpentelemetryApiJackson2Test extends OpentelemetryApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
