package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LegacyScoreV1ApiAsyncJackson2Test extends LegacyScoreV1ApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
