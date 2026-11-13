package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableEvaluatorsApiJackson3Test extends UnstableEvaluatorsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
