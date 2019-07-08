package com.myfitnesspal.shared.task;

import android.content.Context;
import com.myfitnesspal.feature.images.util.ImageUploadUtil;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import dagger.Lazy;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

public class DownloadImageTask extends EventedTaskBase<String, IOException> {
    private static final String FILE_NAME_BASE = "mfp-image-%s.jpg";
    private final String imageUrl;
    private final Lazy<ApiUrlProvider> urlProvider;

    public static class CompletedEvent extends TaskEventBase<String, IOException> {
    }

    public DownloadImageTask(Lazy<ApiUrlProvider> lazy, String str) {
        super(CompletedEvent.class);
        this.urlProvider = lazy;
        this.imageUrl = str;
    }

    /* access modifiers changed from: protected */
    public String exec(Context context) throws IOException {
        Builder url = new Builder().url(this.imageUrl);
        if (this.imageUrl.startsWith(((ApiUrlProvider) this.urlProvider.get()).getApiV2BaseUrl())) {
            Map credentials = ((ApiUrlProvider) this.urlProvider.get()).getCredentials();
            for (String str : credentials.keySet()) {
                url.addHeader(str, (String) credentials.get(str));
            }
        }
        Response execute = new OkHttpClient().newCall(url.build()).execute();
        if (!execute.isSuccessful()) {
            return null;
        }
        File file = new File(ImageUploadUtil.getTemporaryImageFilepath(context, String.format(FILE_NAME_BASE, new Object[]{Long.valueOf(System.currentTimeMillis())})).getPath());
        BufferedSink buffer = Okio.buffer(Okio.sink(file));
        buffer.writeAll(execute.body().source());
        buffer.close();
        return file.getPath();
    }
}
