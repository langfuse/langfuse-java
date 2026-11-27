package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetRunItemsApiAsyncJackson2Test extends DatasetRunItemsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
