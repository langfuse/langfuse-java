package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LegacyObservationsV1ApiJackson3Test extends LegacyObservationsV1ApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
