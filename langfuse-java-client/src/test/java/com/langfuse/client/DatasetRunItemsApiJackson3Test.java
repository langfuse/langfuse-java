package com.langfuse.client;

import com.langfuse.api.LangfuseApi;

class DatasetRunItemsApiJackson3Test extends DatasetRunItemsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
