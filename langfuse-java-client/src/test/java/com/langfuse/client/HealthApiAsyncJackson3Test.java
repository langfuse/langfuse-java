package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class HealthApiAsyncJackson3Test extends HealthApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
