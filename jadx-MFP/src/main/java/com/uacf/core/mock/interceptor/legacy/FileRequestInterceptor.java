package com.uacf.core.mock.interceptor.legacy;

import android.os.Environment;
import com.google.gson.FieldNamingPolicy;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.List;

public class FileRequestInterceptor implements RequestInterceptor {
    private static final String DEFAULT_INTERCEPTOR_FILENAME = "interceptor.json";
    private static final String DEFAULT_MOCK_DATA_DIR;
    public static final int NO_SET = -1;
    private static String sInterceptorFilename = DEFAULT_INTERCEPTOR_FILENAME;
    private static String sMockDataDir = DEFAULT_MOCK_DATA_DIR;
    private int currentSet = -1;
    private FileInterceptor interceptor = null;
    private final MockProvider mockProvider;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append("/MFP/mocks");
        DEFAULT_MOCK_DATA_DIR = sb.toString();
    }

    public FileRequestInterceptor(MockProvider mockProvider2) {
        this.mockProvider = mockProvider2;
        reset();
    }

    public InterceptorResponse getResponse(String str, String str2, String str3, String str4) {
        return this.interceptor.getResponse(this.currentSet, str, str2, str3, str4);
    }

    public static String getMockDataDir() {
        return sMockDataDir;
    }

    protected static void setDefaultInterceptorFilename(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("MOCKS: setting interceptor filename to ");
        sb.append(str);
        Ln.d(sb.toString(), new Object[0]);
        sInterceptorFilename = str;
        if (Strings.isEmpty(sInterceptorFilename)) {
            sInterceptorFilename = str;
        }
    }

    public List<CharSequence> getSets() {
        ArrayList arrayList = new ArrayList();
        FileInterceptor fileInterceptor = this.interceptor;
        if (!(fileInterceptor == null || fileInterceptor.sets == null)) {
            int size = this.interceptor.sets.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(((FileInterceptorSet) this.interceptor.sets.get(i)).name);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void setCurrentSet(String str) {
        this.currentSet = -1;
        if (str != null) {
            FileInterceptor fileInterceptor = this.interceptor;
            if (fileInterceptor != null && fileInterceptor.sets != null) {
                int size = this.interceptor.sets.size();
                for (int i = 0; i < size; i++) {
                    if (((FileInterceptorSet) this.interceptor.sets.get(i)).name.equals(str)) {
                        this.currentSet = i;
                        return;
                    }
                }
            }
        }
    }

    public int getCurrentSet() {
        return this.currentSet;
    }

    public void reset() {
        StringBuilder sb = new StringBuilder();
        sb.append(sMockDataDir);
        sb.append("/");
        sb.append(sInterceptorFilename);
        String sb2 = sb.toString();
        String readFileContents = FileUtils.readFileContents(sb2);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("MOCKS: reload at ");
        sb3.append(sb2);
        Ln.d(sb3.toString(), new Object[0]);
        if (!Strings.isEmpty(readFileContents)) {
            FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
            this.interceptor = (FileInterceptor) new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).tryMapFrom(readFileContents, FileInterceptor.class);
        } else {
            this.interceptor = new FileInterceptor();
        }
        setCurrentSet(this.mockProvider.getCurrentMock());
    }
}
