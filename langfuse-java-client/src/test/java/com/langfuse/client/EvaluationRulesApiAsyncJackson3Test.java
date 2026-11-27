package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class EvaluationRulesApiAsyncJackson3Test extends EvaluationRulesApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
