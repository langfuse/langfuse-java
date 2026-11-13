package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoresV3ApiAsyncJackson2Test extends ScoresV3ApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
