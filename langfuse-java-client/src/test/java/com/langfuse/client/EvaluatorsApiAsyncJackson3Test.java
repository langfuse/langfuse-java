package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class EvaluatorsApiAsyncJackson3Test extends EvaluatorsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
