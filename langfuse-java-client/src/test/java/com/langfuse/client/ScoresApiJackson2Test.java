package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ScoresApiJackson2Test extends ScoresApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
