package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class EvaluatorsApiAsyncJackson2Test extends EvaluatorsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
