package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LlmConnectionsApiJackson3Test extends LlmConnectionsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
