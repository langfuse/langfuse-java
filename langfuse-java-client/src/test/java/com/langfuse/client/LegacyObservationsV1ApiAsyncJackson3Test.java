package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class LegacyObservationsV1ApiAsyncJackson3Test extends LegacyObservationsV1ApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
