package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ExperimentsApiAsyncJackson2Test extends ExperimentsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
