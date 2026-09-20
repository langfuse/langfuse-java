package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class FeedbackApiAsyncJackson3Test extends FeedbackApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
