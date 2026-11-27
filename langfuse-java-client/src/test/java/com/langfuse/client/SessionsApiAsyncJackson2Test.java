package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class SessionsApiAsyncJackson2Test extends SessionsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
