package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoresV3ApiJackson2Test extends ScoresV3ApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
