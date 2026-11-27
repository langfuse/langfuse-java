package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableDashboardWidgetsApiAsyncJackson2Test extends UnstableDashboardWidgetsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
