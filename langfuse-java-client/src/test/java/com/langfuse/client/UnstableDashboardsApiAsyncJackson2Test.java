package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableDashboardsApiAsyncJackson2Test extends UnstableDashboardsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
