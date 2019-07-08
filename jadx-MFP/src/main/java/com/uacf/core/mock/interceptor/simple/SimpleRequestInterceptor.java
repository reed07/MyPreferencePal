package com.uacf.core.mock.interceptor.simple;

import android.content.Context;
import android.content.res.AssetManager;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.mock.interceptor.legacy.FileRequestInterceptor;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.UriUtils;
import io.uacf.core.api.UacfApiException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class SimpleRequestInterceptor implements RequestInterceptor {
    private static final String MOCKS_DIR = "mocks/unit-tests/responses/";
    private static final SimpleInterceptorResponse NO_MATCH_RESPONSE = new SimpleInterceptorResponse(null, 555, null);
    /* access modifiers changed from: private */
    public static Map<String, String> fileContentsCache = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static GsonObjectMapper mapper = new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    private SimpleInterceptorRequest lastRequest = null;
    private InterceptorResponse next;
    private NoMatchMode noMatchMode = NoMatchMode.Passthrough;
    private Map<SimpleInterceptorRequest, SimpleInterceptorResponseList> requestResponseMap = new ConcurrentHashMap();

    public enum NoMatchMode {
        Error,
        Passthrough
    }

    public static final class ResponseBuilder {
        private String body;
        private Map<String, String> headers = new HashMap();
        private int statusCode;

        public static SimpleInterceptorResponse getMockResponse(Context context, String str) throws UacfApiException {
            InputStream inputStream;
            JsonParser jsonParser = new JsonParser();
            try {
                AssetManager assets = context.getAssets();
                StringBuilder sb = new StringBuilder();
                sb.append(SimpleRequestInterceptor.MOCKS_DIR);
                sb.append(str);
                inputStream = assets.open(sb.toString());
            } catch (IOException e) {
                Ln.e(e);
                inputStream = null;
            }
            try {
                return (SimpleInterceptorResponse) new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).tryMapFrom(jsonParser.parse((Reader) new InputStreamReader(inputStream)).getAsJsonObject().toString(), SimpleInterceptorResponse.class);
            } catch (JsonIOException e2) {
                throw new UacfApiException(e2.getLocalizedMessage());
            } catch (JsonSyntaxException e3) {
                throw new UacfApiException(e3.getLocalizedMessage());
            }
        }

        public static SimpleInterceptorResponse fromFile(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(FileRequestInterceptor.getMockDataDir());
            sb.append("/");
            sb.append(str);
            String sb2 = sb.toString();
            String str2 = (String) SimpleRequestInterceptor.fileContentsCache.get(sb2);
            if (Strings.isEmpty(str2)) {
                str2 = FileUtils.readFileContents(sb2);
            }
            if (Strings.notEmpty(str2)) {
                SimpleInterceptorResponse simpleInterceptorResponse = (SimpleInterceptorResponse) SimpleRequestInterceptor.mapper.tryMapFrom(str2, SimpleInterceptorResponse.class);
                if (simpleInterceptorResponse != null && Strings.notEmpty(str2)) {
                    SimpleRequestInterceptor.fileContentsCache.put(sb2, str2);
                }
                return simpleInterceptorResponse;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("invalid response file specified: ");
            sb3.append(str);
            throw new RuntimeException(sb3.toString());
        }

        public ResponseBuilder setBody(String str) {
            this.body = str;
            return this;
        }

        public ResponseBuilder setStatusCode(int i) {
            this.statusCode = i;
            return this;
        }

        public ResponseBuilder addHeader(String str, String str2) {
            this.headers.put(str, str2);
            return this;
        }

        public SimpleInterceptorResponse build() {
            return new SimpleInterceptorResponse(this.body, this.statusCode, this.headers);
        }
    }

    static {
        FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
    }

    public synchronized InterceptorResponse getResponse(String str, String str2, String str3, String str4) {
        this.lastRequest = new SimpleInterceptorRequest(str, str2, str3, str4);
        if (this.next != null) {
            InterceptorResponse interceptorResponse = this.next;
            this.next = null;
            return interceptorResponse;
        }
        return findMatch(this.lastRequest);
    }

    public void reset() {
        this.requestResponseMap.clear();
    }

    public SimpleInterceptorRequest getLastRequest() {
        return this.lastRequest;
    }

    public void overrideNextRequest(InterceptorResponse interceptorResponse) {
        this.next = interceptorResponse;
    }

    public void addRequestOverride(String str, String str2, String str3, String str4, SimpleInterceptorResponse simpleInterceptorResponse) {
        addRequestOverrides(str, str2, str3, str4, simpleInterceptorResponse);
    }

    public void addRequestOverride(String str, String str2, String str3, SimpleInterceptorResponse simpleInterceptorResponse) {
        addRequestOverride(str, str2, str3, null, simpleInterceptorResponse);
    }

    public void addRequestOverride(String str, String str2, SimpleInterceptorResponse simpleInterceptorResponse) {
        addRequestOverride(str, str2, null, null, simpleInterceptorResponse);
    }

    public void addRequestOverrides(String str, String str2, String str3, String str4, SimpleInterceptorResponse... simpleInterceptorResponseArr) {
        this.requestResponseMap.put(new SimpleInterceptorRequest(str, str2, str3, str4), new SimpleInterceptorResponseList(Arrays.asList(simpleInterceptorResponseArr)));
    }

    public void addRequestOverrides(String str, String str2, String str3, SimpleInterceptorResponse... simpleInterceptorResponseArr) {
        addRequestOverrides(str, str2, str3, null, simpleInterceptorResponseArr);
    }

    public void addRequestOverrides(String str, String str2, SimpleInterceptorResponse... simpleInterceptorResponseArr) {
        addRequestOverrides(str, str2, null, null, simpleInterceptorResponseArr);
    }

    public void setNoMatchMode(NoMatchMode noMatchMode2) {
        this.noMatchMode = noMatchMode2;
    }

    private InterceptorResponse findMatch(SimpleInterceptorRequest simpleInterceptorRequest) {
        for (SimpleInterceptorRequest simpleInterceptorRequest2 : this.requestResponseMap.keySet()) {
            if (matches(simpleInterceptorRequest, simpleInterceptorRequest2)) {
                return ((SimpleInterceptorResponseList) this.requestResponseMap.get(simpleInterceptorRequest2)).next();
            }
        }
        return this.noMatchMode == NoMatchMode.Error ? NO_MATCH_RESPONSE : null;
    }

    private static boolean matches(SimpleInterceptorRequest simpleInterceptorRequest, SimpleInterceptorRequest simpleInterceptorRequest2) {
        if (!simpleInterceptorRequest.getMethod().equalsIgnoreCase(simpleInterceptorRequest2.getMethod()) || !simpleInterceptorRequest2.getUriPathPattern().matcher(simpleInterceptorRequest.getUrlPath()).matches()) {
            return false;
        }
        if (Strings.notEmpty(simpleInterceptorRequest.getBody()) && Strings.notEmpty(simpleInterceptorRequest2.getBody()) && !Pattern.compile(simpleInterceptorRequest2.getBody(), 34).matcher(Strings.toString(simpleInterceptorRequest.getBody())).matches()) {
            return false;
        }
        Map queryStringToMap = UriUtils.queryStringToMap(simpleInterceptorRequest.getQuery());
        Map queryStringToMap2 = UriUtils.queryStringToMap(simpleInterceptorRequest2.getQuery());
        if (queryStringToMap.size() != queryStringToMap2.size()) {
            return false;
        }
        if (queryStringToMap.size() == queryStringToMap2.size() && queryStringToMap.size() > 0) {
            for (String str : queryStringToMap.keySet()) {
                if (queryStringToMap2.get(str) == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
