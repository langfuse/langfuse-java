package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LegacyObservationsV1ApiJackson2Test extends LegacyObservationsV1ApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
