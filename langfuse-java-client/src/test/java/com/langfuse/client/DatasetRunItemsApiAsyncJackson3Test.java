package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetRunItemsApiAsyncJackson3Test extends DatasetRunItemsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
