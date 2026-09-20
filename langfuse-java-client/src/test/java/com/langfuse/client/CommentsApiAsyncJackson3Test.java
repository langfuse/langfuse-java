package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class CommentsApiAsyncJackson3Test extends CommentsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
