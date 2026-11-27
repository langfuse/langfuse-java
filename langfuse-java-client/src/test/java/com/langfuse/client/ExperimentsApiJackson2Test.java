package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ExperimentsApiJackson2Test extends ExperimentsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
