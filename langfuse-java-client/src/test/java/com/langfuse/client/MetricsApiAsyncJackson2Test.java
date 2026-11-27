package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class MetricsApiAsyncJackson2Test extends MetricsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
