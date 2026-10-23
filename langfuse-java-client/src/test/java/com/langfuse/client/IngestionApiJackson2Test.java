package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class IngestionApiJackson2Test extends IngestionApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
