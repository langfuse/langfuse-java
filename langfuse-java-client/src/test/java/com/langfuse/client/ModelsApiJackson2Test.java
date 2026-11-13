package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ModelsApiJackson2Test extends ModelsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
