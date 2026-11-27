package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class CommentsApiJackson2Test extends CommentsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
