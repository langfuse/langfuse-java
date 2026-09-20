package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableDashboardWidgetsApiJackson2Test extends UnstableDashboardWidgetsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
