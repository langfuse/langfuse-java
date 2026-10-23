package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ObservationsApiAsyncJackson2Test extends ObservationsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
