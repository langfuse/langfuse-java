package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LegacyScoreV1ApiAsyncJackson3Test extends LegacyScoreV1ApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
