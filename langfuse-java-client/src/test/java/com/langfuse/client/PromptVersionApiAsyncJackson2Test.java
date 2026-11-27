package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class PromptVersionApiAsyncJackson2Test extends PromptVersionApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
