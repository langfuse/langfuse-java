package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class EvaluationRulesApiJackson3Test extends EvaluationRulesApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
