package com.brightcove.player.drm;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.C;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public final class DrmUtil extends BrightcoveDrmUtil {
    @NonNull
    public static HttpDataSource createHttpDataSource(@Nullable Map<String, String> map) {
        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(C.HTTP_USER_AGENT, null);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                defaultHttpDataSource.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return defaultHttpDataSource;
    }

    @NonNull
    public static HttpDataSource createHttpDataSource() {
        return createHttpDataSource(null);
    }

    public static byte[] executePost(String str, @Nullable byte[] bArr, @Nullable Map<String, String> map) throws IOException {
        HttpDataSource createHttpDataSource = createHttpDataSource(map);
        DataSpec dataSpec = new DataSpec(Uri.parse(str), bArr == null ? new byte[0] : bArr, 0, 0, -1, null, 1);
        DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(createHttpDataSource, dataSpec);
        try {
            return Util.toByteArray(dataSourceInputStream);
        } finally {
            Util.closeQuietly((Closeable) dataSourceInputStream);
        }
    }
}
