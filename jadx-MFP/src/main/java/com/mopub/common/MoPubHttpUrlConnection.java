package com.mopub.common;

import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;
import com.mopub.network.Networking;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

public abstract class MoPubHttpUrlConnection extends HttpURLConnection {
    public static HttpURLConnection getHttpUrlConnection(@NonNull String str) throws IOException {
        Preconditions.checkNotNull(str);
        if (!isUrlImproperlyEncoded(str)) {
            try {
                str = urlEncode(str);
            } catch (Exception unused) {
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("user-agent", Networking.getCachedUserAgent());
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            return httpURLConnection;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("URL is improperly encoded: ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    @NonNull
    public static String urlEncode(@NonNull String str) throws Exception {
        URI uri;
        Preconditions.checkNotNull(str);
        if (!isUrlImproperlyEncoded(str)) {
            if (isUrlUnencoded(str)) {
                uri = encodeUrl(str);
            } else {
                uri = new URI(str);
            }
            return uri.toURL().toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("URL is improperly encoded: ");
        sb.append(str);
        throw new UnsupportedEncodingException(sb.toString());
    }

    static boolean isUrlImproperlyEncoded(@NonNull String str) {
        try {
            URLDecoder.decode(str, "UTF-8");
            return false;
        } catch (UnsupportedEncodingException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Url is improperly encoded: ");
            sb.append(str);
            MoPubLog.w(sb.toString());
            return true;
        }
    }

    static boolean isUrlUnencoded(@NonNull String str) {
        try {
            new URI(str);
            return false;
        } catch (URISyntaxException unused) {
            return true;
        }
    }

    @NonNull
    static URI encodeUrl(@NonNull String str) throws Exception {
        try {
            URL url = new URL(str);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            return uri;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to encode url: ");
            sb.append(str);
            MoPubLog.w(sb.toString());
            throw e;
        }
    }
}
