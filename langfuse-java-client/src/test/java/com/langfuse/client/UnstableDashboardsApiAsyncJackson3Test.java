package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableDashboardsApiAsyncJackson3Test extends UnstableDashboardsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
