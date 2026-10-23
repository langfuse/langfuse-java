package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class PromptsApiAsyncJackson2Test extends PromptsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
