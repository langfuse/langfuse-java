package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class IngestionApiJackson3Test extends IngestionApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
