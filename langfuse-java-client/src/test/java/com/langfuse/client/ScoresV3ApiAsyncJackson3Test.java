package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoresV3ApiAsyncJackson3Test extends ScoresV3ApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
