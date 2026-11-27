package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class FeedbackApiJackson3Test extends FeedbackApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
