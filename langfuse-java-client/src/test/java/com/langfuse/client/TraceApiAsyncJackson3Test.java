package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class TraceApiAsyncJackson3Test extends TraceApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
