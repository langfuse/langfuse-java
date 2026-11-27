package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class PromptVersionApiAsyncJackson3Test extends PromptVersionApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
