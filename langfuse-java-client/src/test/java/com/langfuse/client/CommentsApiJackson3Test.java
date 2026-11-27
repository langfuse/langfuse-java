package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class CommentsApiJackson3Test extends CommentsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
