package com.langfuse.client;

import com.langfuse.api.LangfuseApi;
import org.junit.jupiter.api.Disabled;

@Disabled("Requires org-admin role")
class BlobStorageIntegrationsApiJackson2Test extends BlobStorageIntegrationsApiTest {

    @Override
    LangfuseApi createClient() {
        return jackson2Client();
    }
}
