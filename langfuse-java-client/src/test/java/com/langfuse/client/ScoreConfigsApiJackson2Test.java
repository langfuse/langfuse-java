package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoreConfigsApiJackson2Test extends ScoreConfigsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
