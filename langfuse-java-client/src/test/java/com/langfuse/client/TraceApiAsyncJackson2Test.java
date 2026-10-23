package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class TraceApiAsyncJackson2Test extends TraceApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
