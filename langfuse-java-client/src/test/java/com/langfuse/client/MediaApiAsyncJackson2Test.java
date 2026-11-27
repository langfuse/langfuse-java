package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class MediaApiAsyncJackson2Test extends MediaApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
