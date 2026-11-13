package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class FeedbackApiJackson2Test extends FeedbackApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
