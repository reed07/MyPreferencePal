package com.google.android.gms.tagmanager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

final class zzfv implements zzfx {
    zzfv() {
    }

    public final HttpURLConnection zzc(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
