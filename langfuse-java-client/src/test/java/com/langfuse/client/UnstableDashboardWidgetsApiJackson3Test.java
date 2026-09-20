package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableDashboardWidgetsApiJackson3Test extends UnstableDashboardWidgetsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
