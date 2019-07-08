package io.uacf.net.retrofit;

import com.google.gson.FieldNamingPolicy;
import com.myfitnesspal.shared.constants.HttpConstants;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import com.uacf.core.mock.interceptor.simple.SimpleInterceptorResponse;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.json.JSONObject;

public final class UacfMockInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Object obj;
        Request request = chain.request();
        String method = request.method();
        RequestBody body = request.body();
        if (body != null) {
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            obj = buffer.readUtf8();
        } else {
            obj = null;
        }
        HttpUrl url = request.url();
        InterceptorResponse response = UacfRetrofitHelper.GlobalRequestInterceptor.getResponse(method, url.encodedPath(), url.query(), Strings.toString(obj));
        if (response != null) {
            return convertInterceptorResponseToOkHttpResponse(request, response);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"status_code\": 400,\n  \"body\": {\"error\" : \"This http request is missing a mock interceptor - [");
        sb.append(request.method());
        sb.append("] ");
        sb.append(request.url());
        sb.append("\"}\n");
        sb.append("}");
        try {
            return convertInterceptorResponseToOkHttpResponse(request, (SimpleInterceptorResponse) new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).tryMapFrom(new JSONObject(sb.toString()).toString(), SimpleInterceptorResponse.class));
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    private Response convertInterceptorResponseToOkHttpResponse(Request request, InterceptorResponse interceptorResponse) {
        Builder code = new Builder().protocol(Protocol.HTTP_1_1).request(request).code(interceptorResponse.getStatusCode());
        for (Entry entry : interceptorResponse.getHeaders().entrySet()) {
            code.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        final String body = interceptorResponse.getBody();
        code.body(new ResponseBody() {
            public MediaType contentType() {
                return MediaType.parse(HttpConstants.CONTENT_TYPE_JSON);
            }

            public long contentLength() {
                return (long) Strings.length(body);
            }

            public BufferedSource source() {
                Buffer buffer = new Buffer();
                buffer.writeString(Strings.toString(body), Charset.defaultCharset());
                return buffer;
            }
        });
        return code.build();
    }
}
