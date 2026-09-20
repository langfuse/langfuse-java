package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class EvaluatorsApiJackson3Test extends EvaluatorsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
