package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ProjectsApiAsyncJackson3Test extends ProjectsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
