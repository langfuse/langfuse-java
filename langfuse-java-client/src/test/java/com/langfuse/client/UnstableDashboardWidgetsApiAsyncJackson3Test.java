package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableDashboardWidgetsApiAsyncJackson3Test extends UnstableDashboardWidgetsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
