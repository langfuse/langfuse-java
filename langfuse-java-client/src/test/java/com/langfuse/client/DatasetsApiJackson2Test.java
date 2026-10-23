package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetsApiJackson2Test extends DatasetsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
