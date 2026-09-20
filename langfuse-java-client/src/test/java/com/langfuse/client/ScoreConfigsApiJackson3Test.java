package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoreConfigsApiJackson3Test extends ScoreConfigsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
