package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class EvaluatorsApiJackson2Test extends EvaluatorsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
