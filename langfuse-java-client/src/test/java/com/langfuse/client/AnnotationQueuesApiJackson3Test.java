package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class AnnotationQueuesApiJackson3Test extends AnnotationQueuesApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
