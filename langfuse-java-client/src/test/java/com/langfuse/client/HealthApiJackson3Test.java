package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class HealthApiJackson3Test extends HealthApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
