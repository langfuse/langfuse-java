package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LlmConnectionsApiAsyncJackson3Test extends LlmConnectionsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
