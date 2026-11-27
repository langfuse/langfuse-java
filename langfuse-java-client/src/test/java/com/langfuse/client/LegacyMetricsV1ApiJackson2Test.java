package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LegacyMetricsV1ApiJackson2Test extends LegacyMetricsV1ApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
