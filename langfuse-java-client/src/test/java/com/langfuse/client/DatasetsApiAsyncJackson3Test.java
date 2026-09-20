package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetsApiAsyncJackson3Test extends DatasetsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
