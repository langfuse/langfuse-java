package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoreConfigsApiAsyncJackson2Test extends ScoreConfigsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
