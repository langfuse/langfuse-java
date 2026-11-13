package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class AnnotationQueuesApiJackson2Test extends AnnotationQueuesApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
