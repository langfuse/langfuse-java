package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class PromptVersionApiJackson3Test extends PromptVersionApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
