package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ModelsApiAsyncJackson3Test extends ModelsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
