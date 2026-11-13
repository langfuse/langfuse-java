package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class UnstableEvaluatorsApiJackson2Test extends UnstableEvaluatorsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
