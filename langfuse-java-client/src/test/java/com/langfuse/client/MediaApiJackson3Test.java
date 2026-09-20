package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class MediaApiJackson3Test extends MediaApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
