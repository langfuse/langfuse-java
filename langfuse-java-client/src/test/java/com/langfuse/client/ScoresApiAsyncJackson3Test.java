package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoresApiAsyncJackson3Test extends ScoresApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
