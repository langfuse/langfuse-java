package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class PromptsApiJackson3Test extends PromptsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
