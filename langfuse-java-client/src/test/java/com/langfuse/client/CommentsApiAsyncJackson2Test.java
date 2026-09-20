package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class CommentsApiAsyncJackson2Test extends CommentsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
