package com.uacf.core.mock.interceptor.legacy;

import com.google.gson.annotations.Expose;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public final class FileContent {
    public byte[] bytes;
    @Expose
    public String file;
    public Map<String, String> headers = new HashMap();
    public String raw;

    public Map<String, String> getHeaders() {
        return new HashMap(this.headers);
    }

    public byte[] asBytes() {
        if (this.bytes == null && !Strings.isEmpty(this.file)) {
            StringBuilder sb = new StringBuilder();
            sb.append(FileRequestInterceptor.getMockDataDir());
            sb.append("/");
            sb.append(this.file);
            this.bytes = FileUtils.readFileBytes(sb.toString());
        }
        return this.bytes;
    }

    public String asString() {
        if (Strings.isEmpty(this.raw) && !Strings.isEmpty(this.file)) {
            StringBuilder sb = new StringBuilder();
            sb.append(FileRequestInterceptor.getMockDataDir());
            sb.append("/");
            sb.append(this.file);
            this.raw = FileUtils.readFileContents(sb.toString());
        }
        return this.raw;
    }
}
