package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LegacyMetricsV1ApiAsyncJackson3Test extends LegacyMetricsV1ApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
