/**
 * This file was auto-generated by Fern from our API Definition.
 */

package core;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import okhttp3.OkHttpClient;

public final class ClientOptions {
  private final Environment environment;

  private final Map<String, String> headers;

  private final Map<String, Supplier<String>> headerSuppliers;

  private final OkHttpClient httpClient;

  private final int timeout;

  private ClientOptions(Environment environment, Map<String, String> headers,
      Map<String, Supplier<String>> headerSuppliers, OkHttpClient httpClient, int timeout) {
    this.environment = environment;
    this.headers = new HashMap<>();
    this.headers.putAll(headers);
    this.headers.putAll(new HashMap<String,String>() {{put("X-Fern-Language", "JAVA");put("X-Fern-SDK-Name", "com.langfuse.fern:langfuse-sdk");put("X-Fern-SDK-Version", "0.0.106");}});
    this.headerSuppliers = headerSuppliers;
    this.httpClient = httpClient;
    this.timeout = timeout;
  }

  public Environment environment() {
    return this.environment;
  }

  public Map<String, String> headers(RequestOptions requestOptions) {
    Map<String, String> values = new HashMap<>(this.headers);
    headerSuppliers.forEach((key, supplier) ->  {
      values.put(key, supplier.get());
    } );
    if (requestOptions != null) {
      values.putAll(requestOptions.getHeaders());
    }
    return values;
  }

  public OkHttpClient httpClient() {
    return this.httpClient;
  }

  public OkHttpClient httpClientWithTimeout(RequestOptions requestOptions) {
    if (requestOptions == null) {
      return this.httpClient;
    }
    return this.httpClient.newBuilder().callTimeout(requestOptions.getTimeout().get(), requestOptions.getTimeoutTimeUnit()).connectTimeout(0, TimeUnit.SECONDS).writeTimeout(0, TimeUnit.SECONDS).readTimeout(0, TimeUnit.SECONDS).build();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Environment environment;

    private final Map<String, String> headers = new HashMap<>();

    private final Map<String, Supplier<String>> headerSuppliers = new HashMap<>();

    private int timeout = 60;

    private OkHttpClient httpClient = new OkHttpClient.Builder()
        .addInterceptor(new RetryInterceptor(3))
        .callTimeout(this.timeout, TimeUnit.SECONDS)
        .build();

    public Builder environment(Environment environment) {
      this.environment = environment;
      return this;
    }

    public Builder addHeader(String key, String value) {
      this.headers.put(key, value);
      return this;
    }

    public Builder addHeader(String key, Supplier<String> value) {
      this.headerSuppliers.put(key, value);
      return this;
    }

    /**
     * Override the timeout in seconds. Defaults to 60 seconds.
     */
    public Builder timeout(int timeout) {
      this.timeout = timeout;
      return this;
    }

    public Builder httpClient(OkHttpClient httpClient) {
      this.httpClient = httpClient;
      return this;
    }

    public ClientOptions build() {
      return new ClientOptions(environment, headers, headerSuppliers, httpClient, this.timeout);
    }
  }
}
