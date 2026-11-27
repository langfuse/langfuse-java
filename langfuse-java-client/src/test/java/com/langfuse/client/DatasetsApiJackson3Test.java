package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetsApiJackson3Test extends DatasetsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
