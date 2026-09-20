package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableEvaluatorsApiAsyncJackson2Test extends UnstableEvaluatorsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
