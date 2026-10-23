package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableEvaluatorsApiAsyncJackson3Test extends UnstableEvaluatorsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
