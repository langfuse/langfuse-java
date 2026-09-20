package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ObservationsApiAsyncJackson3Test extends ObservationsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
