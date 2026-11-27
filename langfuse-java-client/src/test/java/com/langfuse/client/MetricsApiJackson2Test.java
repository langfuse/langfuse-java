package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class MetricsApiJackson2Test extends MetricsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
