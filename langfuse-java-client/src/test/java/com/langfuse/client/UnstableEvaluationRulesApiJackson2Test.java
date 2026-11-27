package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableEvaluationRulesApiJackson2Test extends UnstableEvaluationRulesApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
