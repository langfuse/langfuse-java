package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ObservationsApiJackson3Test extends ObservationsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
