package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class MediaApiJackson2Test extends MediaApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
