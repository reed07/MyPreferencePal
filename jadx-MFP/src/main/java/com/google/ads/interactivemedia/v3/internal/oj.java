package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: IMASDK */
class oj implements tv<Long> {
    private oj() {
    }

    public final /* synthetic */ Object a(Uri uri, InputStream inputStream) throws IOException {
        return Long.valueOf(vf.g(new BufferedReader(new InputStreamReader(inputStream)).readLine()));
    }

    /* synthetic */ oj(byte b) {
        this();
    }
}
