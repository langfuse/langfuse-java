package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetItemsApiJackson2Test extends DatasetItemsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
