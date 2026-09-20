package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class SessionsApiJackson3Test extends SessionsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
