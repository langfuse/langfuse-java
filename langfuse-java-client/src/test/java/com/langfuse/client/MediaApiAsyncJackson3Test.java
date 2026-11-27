package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class MediaApiAsyncJackson3Test extends MediaApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
