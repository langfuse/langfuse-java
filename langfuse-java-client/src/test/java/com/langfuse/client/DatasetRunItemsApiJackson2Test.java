package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetRunItemsApiJackson2Test extends DatasetRunItemsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
