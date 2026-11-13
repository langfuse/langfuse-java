package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class AnnotationQueuesApiAsyncJackson3Test extends AnnotationQueuesApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
