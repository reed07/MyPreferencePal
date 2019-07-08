package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.Build.VERSION;
import com.google.common.net.HttpHeaders;
import com.google.firebase.appindexing.Indexable;
import com.squareup.picasso.Downloader.Response;
import com.squareup.picasso.Downloader.ResponseException;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnectionDownloader implements Downloader {
    private static final ThreadLocal<StringBuilder> CACHE_HEADER_BUILDER = new ThreadLocal<StringBuilder>() {
        /* access modifiers changed from: protected */
        public StringBuilder initialValue() {
            return new StringBuilder();
        }
    };
    private static final String FORCE_CACHE = "only-if-cached,max-age=2147483647";
    static final String RESPONSE_SOURCE = "X-Android-Response-Source";
    static volatile Object cache;
    private static final Object lock = new Object();
    private final Context context;

    private static class ResponseCacheIcs {
        private ResponseCacheIcs() {
        }

        static Object install(Context context) throws IOException {
            File createDefaultCacheDir = Utils.createDefaultCacheDir(context);
            HttpResponseCache installed = HttpResponseCache.getInstalled();
            return installed == null ? HttpResponseCache.install(createDefaultCacheDir, Utils.calculateDiskCacheSize(createDefaultCacheDir)) : installed;
        }

        static void close(Object obj) {
            try {
                ((HttpResponseCache) obj).close();
            } catch (IOException unused) {
            }
        }
    }

    public UrlConnectionDownloader(Context context2) {
        this.context = context2.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection openConnection(Uri uri) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(Indexable.MAX_STRING_LENGTH);
        return httpURLConnection;
    }

    public Response load(Uri uri, int i) throws IOException {
        String str;
        if (VERSION.SDK_INT >= 14) {
            installCacheIfNeeded(this.context);
        }
        HttpURLConnection openConnection = openConnection(uri);
        openConnection.setUseCaches(true);
        if (i != 0) {
            if (NetworkPolicy.isOfflineOnly(i)) {
                str = FORCE_CACHE;
            } else {
                StringBuilder sb = (StringBuilder) CACHE_HEADER_BUILDER.get();
                sb.setLength(0);
                if (!NetworkPolicy.shouldReadFromDiskCache(i)) {
                    sb.append("no-cache");
                }
                if (!NetworkPolicy.shouldWriteToDiskCache(i)) {
                    if (sb.length() > 0) {
                        sb.append(',');
                    }
                    sb.append("no-store");
                }
                str = sb.toString();
            }
            openConnection.setRequestProperty(HttpHeaders.CACHE_CONTROL, str);
        }
        int responseCode = openConnection.getResponseCode();
        if (responseCode < 300) {
            long headerFieldInt = (long) openConnection.getHeaderFieldInt("Content-Length", -1);
            return new Response(openConnection.getInputStream(), Utils.parseResponseSourceHeader(openConnection.getHeaderField(RESPONSE_SOURCE)), headerFieldInt);
        }
        openConnection.disconnect();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(responseCode);
        sb2.append(" ");
        sb2.append(openConnection.getResponseMessage());
        throw new ResponseException(sb2.toString(), i, responseCode);
    }

    public void shutdown() {
        if (VERSION.SDK_INT >= 14 && cache != null) {
            ResponseCacheIcs.close(cache);
        }
    }

    private static void installCacheIfNeeded(Context context2) {
        if (cache == null) {
            try {
                synchronized (lock) {
                    if (cache == null) {
                        cache = ResponseCacheIcs.install(context2);
                    }
                }
            } catch (IOException unused) {
            }
        }
    }
}
