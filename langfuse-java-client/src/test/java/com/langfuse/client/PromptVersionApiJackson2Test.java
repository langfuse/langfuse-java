package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class PromptVersionApiJackson2Test extends PromptVersionApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
