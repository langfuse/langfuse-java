package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ExperimentsApiAsyncJackson3Test extends ExperimentsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
