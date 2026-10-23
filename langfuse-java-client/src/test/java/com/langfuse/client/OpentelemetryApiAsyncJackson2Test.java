package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class OpentelemetryApiAsyncJackson2Test extends OpentelemetryApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
