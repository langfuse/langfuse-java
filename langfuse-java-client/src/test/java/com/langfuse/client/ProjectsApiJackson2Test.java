package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ProjectsApiJackson2Test extends ProjectsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
