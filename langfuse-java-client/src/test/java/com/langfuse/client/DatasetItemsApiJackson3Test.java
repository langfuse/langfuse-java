package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetItemsApiJackson3Test extends DatasetItemsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
