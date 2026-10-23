package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class EvaluationRulesApiAsyncJackson2Test extends EvaluationRulesApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
