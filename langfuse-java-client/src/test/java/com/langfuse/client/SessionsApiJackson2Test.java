package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class SessionsApiJackson2Test extends SessionsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
