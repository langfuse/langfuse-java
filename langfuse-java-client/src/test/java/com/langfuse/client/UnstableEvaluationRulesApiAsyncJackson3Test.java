package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableEvaluationRulesApiAsyncJackson3Test extends UnstableEvaluationRulesApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
