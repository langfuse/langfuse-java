package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class PromptsApiAsyncJackson3Test extends PromptsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
