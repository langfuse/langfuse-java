package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class FeedbackApiAsyncJackson2Test extends FeedbackApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
