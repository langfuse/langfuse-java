package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LlmConnectionsApiJackson2Test extends LlmConnectionsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
