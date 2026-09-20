package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LegacyScoreV1ApiJackson2Test extends LegacyScoreV1ApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
