package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoresV3ApiJackson3Test extends ScoresV3ApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
