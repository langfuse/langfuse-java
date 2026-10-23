package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class PromptsApiJackson2Test extends PromptsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
