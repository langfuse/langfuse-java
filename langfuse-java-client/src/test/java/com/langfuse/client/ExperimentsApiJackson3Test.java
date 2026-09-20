package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ExperimentsApiJackson3Test extends ExperimentsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
