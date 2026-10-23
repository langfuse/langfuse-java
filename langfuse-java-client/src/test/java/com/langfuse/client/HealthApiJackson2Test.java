package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class HealthApiJackson2Test extends HealthApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
