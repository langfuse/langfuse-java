package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableEvaluationRulesApiAsyncJackson2Test extends UnstableEvaluationRulesApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
