package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class HealthApiAsyncJackson2Test extends HealthApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
