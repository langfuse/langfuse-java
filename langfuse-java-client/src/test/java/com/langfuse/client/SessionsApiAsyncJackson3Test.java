package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class SessionsApiAsyncJackson3Test extends SessionsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
