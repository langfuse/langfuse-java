package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class TraceApiJackson2Test extends TraceApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
