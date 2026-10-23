package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoresApiAsyncJackson2Test extends ScoresApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
