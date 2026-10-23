package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class MetricsApiAsyncJackson3Test extends MetricsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
