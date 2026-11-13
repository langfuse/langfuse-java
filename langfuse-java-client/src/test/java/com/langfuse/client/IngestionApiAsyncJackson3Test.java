package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class IngestionApiAsyncJackson3Test extends IngestionApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
