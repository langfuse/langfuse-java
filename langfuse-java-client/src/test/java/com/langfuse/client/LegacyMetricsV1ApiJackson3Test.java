package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LegacyMetricsV1ApiJackson3Test extends LegacyMetricsV1ApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
