package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class EvaluationRulesApiJackson2Test extends EvaluationRulesApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
