package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetsApiAsyncJackson2Test extends DatasetsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
