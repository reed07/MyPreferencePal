package com.uacf.core.mock.interceptor.legacy;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.annotations.Expose;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.List;

public final class FileInterceptorSet {
    @Expose
    public String name;
    @Expose
    public List<String> requestPaths;
    public List<FileInterceptorRequest> requests;

    public InterceptorResponse getResponse(String str, String str2, String str3, Object obj) {
        if (this.requests == null) {
            Ln.d("MOCKS: InterceptorSet requests not parsed yet, populating now...", new Object[0]);
            populateRequests();
        } else {
            Ln.d("MOCKS: InterceptorSet requests already populated, continuing...", new Object[0]);
        }
        List<FileInterceptorRequest> list = this.requests;
        if (list != null) {
            for (FileInterceptorRequest fileInterceptorRequest : list) {
                if (fileInterceptorRequest.matches(str, str2, str3, obj)) {
                    return fileInterceptorRequest.getNextResponse();
                }
            }
        }
        return null;
    }

    private void populateRequests() {
        List<String> list = this.requestPaths;
        if (list != null && list.size() > 0) {
            for (String str : this.requestPaths) {
                StringBuilder sb = new StringBuilder();
                sb.append(FileRequestInterceptor.getMockDataDir());
                sb.append("/");
                sb.append(str);
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("MOCKS: requestPath = ");
                sb3.append(sb2);
                Ln.d(sb3.toString(), new Object[0]);
                String readFileContents = FileUtils.readFileContents(sb2);
                if (Strings.isEmpty(readFileContents)) {
                    Ln.d("empty mock api file: %s", sb2);
                } else {
                    FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
                    FileInterceptorRequest fileInterceptorRequest = (FileInterceptorRequest) new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).tryMapFrom(readFileContents, FileInterceptorRequest.class);
                    if (fileInterceptorRequest != null) {
                        if (this.requests == null) {
                            this.requests = new ArrayList();
                        }
                        this.requests.add(fileInterceptorRequest);
                    }
                }
            }
            this.requestPaths.clear();
        }
    }
}
