package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableDashboardsApiJackson3Test extends UnstableDashboardsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
