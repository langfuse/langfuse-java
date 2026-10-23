package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableDashboardsApiJackson2Test extends UnstableDashboardsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
