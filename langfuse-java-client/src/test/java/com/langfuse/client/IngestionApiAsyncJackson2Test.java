package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class IngestionApiAsyncJackson2Test extends IngestionApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
