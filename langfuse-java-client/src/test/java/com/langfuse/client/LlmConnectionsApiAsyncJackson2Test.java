package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LlmConnectionsApiAsyncJackson2Test extends LlmConnectionsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
