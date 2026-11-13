package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class TraceApiJackson3Test extends TraceApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
