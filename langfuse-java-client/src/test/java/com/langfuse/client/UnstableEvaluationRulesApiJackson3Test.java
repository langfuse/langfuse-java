package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableEvaluationRulesApiJackson3Test extends UnstableEvaluationRulesApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
