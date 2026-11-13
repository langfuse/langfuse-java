package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoreConfigsApiAsyncJackson3Test extends ScoreConfigsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
