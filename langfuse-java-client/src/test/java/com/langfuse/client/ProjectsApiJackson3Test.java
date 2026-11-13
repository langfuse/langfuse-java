package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class ProjectsApiJackson3Test extends ProjectsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
