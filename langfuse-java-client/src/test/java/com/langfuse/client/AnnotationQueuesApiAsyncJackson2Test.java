package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class AnnotationQueuesApiAsyncJackson2Test extends AnnotationQueuesApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
