package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ProjectsApiAsyncJackson2Test extends ProjectsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
