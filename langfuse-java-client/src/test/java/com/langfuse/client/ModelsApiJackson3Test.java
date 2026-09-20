package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ModelsApiJackson3Test extends ModelsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
