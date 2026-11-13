package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoresApiJackson3Test extends ScoresApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
