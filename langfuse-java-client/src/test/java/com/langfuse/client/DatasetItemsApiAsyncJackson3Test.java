package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetItemsApiAsyncJackson3Test extends DatasetItemsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
