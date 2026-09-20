package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ObservationsApiJackson2Test extends ObservationsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
