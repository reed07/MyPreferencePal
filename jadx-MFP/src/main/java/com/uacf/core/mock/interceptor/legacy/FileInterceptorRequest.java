package com.uacf.core.mock.interceptor.legacy;

import com.google.gson.annotations.Expose;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public final class FileInterceptorRequest {
    @Expose
    public String body;
    protected Pattern bodyPattern;
    @Expose
    public String method;
    @Expose
    public List<InterceptorRequestParameter> parameters;
    private int responseCount = 0;
    @Expose
    public List<FileInterceptorResponse> responses;
    @Expose
    public String uri;
    protected Pattern uriPattern;

    private static class InterceptorRequestParameter {
        @Expose
        public String name;
        @Expose
        public String type;
        @Expose
        public Object value;

        private InterceptorRequestParameter() {
        }
    }

    public InterceptorResponse getNextResponse() {
        if (this.responseCount >= this.responses.size()) {
            this.responseCount = this.responses.size() - 1;
        }
        FileInterceptorResponse fileInterceptorResponse = (FileInterceptorResponse) this.responses.get(this.responseCount);
        int i = fileInterceptorResponse.sent + 1;
        fileInterceptorResponse.sent = i;
        if (i >= fileInterceptorResponse.count) {
            this.responseCount++;
        }
        return fileInterceptorResponse;
    }

    public boolean matches(String str, String str2, String str3, Object obj) {
        if (!str.equalsIgnoreCase(this.method)) {
            return false;
        }
        if (!Strings.isEmpty(this.uri)) {
            if (this.uriPattern == null) {
                if (!this.uri.startsWith("/")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("/");
                    sb.append(this.uri);
                    this.uri = sb.toString();
                }
                this.uriPattern = Pattern.compile(this.uri, 34);
            }
            String str4 = null;
            try {
                str4 = new URI(str2).getPath();
            } catch (URISyntaxException unused) {
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("MOCKS: ");
            sb2.append(String.format("attempt to match %s against %s", new Object[]{str4, this.uri}));
            Ln.d(sb2.toString(), new Object[0]);
            if (str4 == null) {
                return false;
            }
            if (!this.uriPattern.matcher(str4).matches()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("MOCKS: ");
                sb3.append(String.format("  --> path did not match", new Object[0]));
                Ln.d(sb3.toString(), new Object[0]);
                return false;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("MOCKS: ");
            sb4.append(String.format("  --> path matched", new Object[0]));
            Ln.d(sb4.toString(), new Object[0]);
        }
        if (Strings.notEmpty(this.body) && obj != null) {
            if (this.bodyPattern == null) {
                this.bodyPattern = Pattern.compile(this.body, 34);
            }
            Ln.d("MOCKS: body pattern = %s, requestBody = %s", this.bodyPattern, Strings.toString(obj));
            if (!this.bodyPattern.matcher(Strings.toString(obj)).matches()) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("MOCKS: ");
                sb5.append(String.format("  --> body did not match", new Object[0]));
                Ln.d(sb5.toString(), new Object[0]);
                return false;
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append("MOCKS: ");
            sb6.append(String.format("  --> body matched", new Object[0]));
            Ln.d(sb6.toString(), new Object[0]);
        }
        List<InterceptorRequestParameter> list = this.parameters;
        if (list != null && list.size() > 0 && !Strings.isEmpty(str3)) {
            HashMap hashMap = new HashMap();
            for (String split : str3.split("&")) {
                String[] split2 = split.split("=");
                StringBuilder sb7 = new StringBuilder();
                sb7.append("MOCKS: ");
                sb7.append(String.format("  --> param %s = %s", new Object[]{split2[0], split2[1]}));
                Ln.d(sb7.toString(), new Object[0]);
                hashMap.put(split2[0], split2[1]);
            }
            for (InterceptorRequestParameter interceptorRequestParameter : this.parameters) {
                Object obj2 = hashMap.get(interceptorRequestParameter.name);
                StringBuilder sb8 = new StringBuilder();
                sb8.append("MOCKS: ");
                sb8.append(String.format("requestParam: %s --> %s, looking for %s", new Object[]{interceptorRequestParameter.name, obj2, interceptorRequestParameter.value}));
                Ln.d(sb8.toString(), new Object[0]);
                if (obj2 == null) {
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append("MOCKS: ");
                    sb9.append(String.format("  --> null value", new Object[0]));
                    Ln.d(sb9.toString(), new Object[0]);
                    return false;
                } else if (!Pattern.matches(interceptorRequestParameter.value.toString(), obj2.toString())) {
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append("MOCKS: ");
                    sb10.append(String.format("  --> unequal", new Object[0]));
                    Ln.d(sb10.toString(), new Object[0]);
                    return false;
                }
            }
        }
        StringBuilder sb11 = new StringBuilder();
        sb11.append("MOCKS: ");
        sb11.append(String.format("  --> TOTAL MATCH", new Object[0]));
        Ln.d(sb11.toString(), new Object[0]);
        return true;
    }
}
