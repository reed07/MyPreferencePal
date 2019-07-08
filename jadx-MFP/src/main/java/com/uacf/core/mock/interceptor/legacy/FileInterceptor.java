package com.uacf.core.mock.interceptor.legacy;

import com.google.gson.annotations.Expose;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import java.util.List;

public final class FileInterceptor {
    @Expose
    public List<FileInterceptorSet> sets = null;

    public InterceptorResponse getResponse(int i, String str, String str2, String str3, Object obj) {
        List<FileInterceptorSet> list = this.sets;
        FileInterceptorSet fileInterceptorSet = (list == null || i < 0 || i >= list.size()) ? null : (FileInterceptorSet) this.sets.get(i);
        if (fileInterceptorSet == null) {
            return null;
        }
        return fileInterceptorSet.getResponse(str, str2, str3, obj);
    }
}
