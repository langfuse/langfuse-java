package com.langfuse.client;

import com.langfuse.api.LangfuseApi;
import org.junit.jupiter.api.Disabled;

@Disabled("Requires org-admin role")
class OrganizationsApiAsyncJackson3Test extends OrganizationsApiAsyncTest {

    @Override
    LangfuseApi createClient() {
        return jackson3Client();
    }
}
