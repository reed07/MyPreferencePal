package com.myfitnesspal.feature.images.service.api;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public final class InputStreamPostBody {
    public static RequestBody create(String str, final InputStream inputStream) {
        final MediaType parse = MediaType.parse(str);
        return new RequestBody() {
            public MediaType contentType() {
                return parse;
            }

            public long contentLength() {
                try {
                    return (long) inputStream.available();
                } catch (IOException unused) {
                    return 0;
                }
            }

            public void writeTo(BufferedSink bufferedSink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(inputStream);
                    bufferedSink.writeAll(source);
                } finally {
                    Util.closeQuietly((Closeable) source);
                }
            }
        };
    }
}
