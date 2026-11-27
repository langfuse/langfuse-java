package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetItemsApiAsyncJackson2Test extends DatasetItemsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
