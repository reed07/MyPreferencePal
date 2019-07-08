package com.airbnb.lottie.network;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.Pair;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.LottieTask;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.zip.ZipInputStream;

public class NetworkFetcher {
    private final Context appContext;
    private final NetworkCache networkCache;
    private final String url;

    public static LottieTask<LottieComposition> fetch(Context context, String str) {
        return new NetworkFetcher(context, str).fetch();
    }

    private NetworkFetcher(Context context, String str) {
        this.appContext = context.getApplicationContext();
        this.url = str;
        this.networkCache = new NetworkCache(this.appContext, str);
    }

    private LottieTask<LottieComposition> fetch() {
        return new LottieTask<>(new Callable<LottieResult<LottieComposition>>() {
            public LottieResult<LottieComposition> call() throws Exception {
                return NetworkFetcher.this.fetchSync();
            }
        });
    }

    @WorkerThread
    public LottieResult<LottieComposition> fetchSync() {
        LottieComposition fetchFromCache = fetchFromCache();
        if (fetchFromCache != null) {
            return new LottieResult<>(fetchFromCache);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Animation for ");
        sb.append(this.url);
        sb.append(" not found in cache. Fetching from network.");
        L.debug(sb.toString());
        return fetchFromNetwork();
    }

    @Nullable
    @WorkerThread
    private LottieComposition fetchFromCache() {
        LottieResult lottieResult;
        Pair fetch = this.networkCache.fetch();
        if (fetch == null) {
            return null;
        }
        FileExtension fileExtension = (FileExtension) fetch.first;
        InputStream inputStream = (InputStream) fetch.second;
        if (fileExtension == FileExtension.Zip) {
            lottieResult = LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(inputStream), this.url);
        } else {
            lottieResult = LottieCompositionFactory.fromJsonInputStreamSync(inputStream, this.url);
        }
        if (lottieResult.getValue() != null) {
            return (LottieComposition) lottieResult.getValue();
        }
        return null;
    }

    @WorkerThread
    private LottieResult<LottieComposition> fetchFromNetwork() {
        try {
            return fetchFromNetworkInternal();
        } catch (IOException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    @WorkerThread
    private LottieResult fetchFromNetworkInternal() throws IOException {
        FileExtension fileExtension;
        LottieResult lottieResult;
        StringBuilder sb = new StringBuilder();
        sb.append("Fetching ");
        sb.append(this.url);
        L.debug(sb.toString());
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.url).openConnection();
        httpURLConnection.setRequestMethod(HttpConstants.METHOD_GET);
        httpURLConnection.connect();
        if (httpURLConnection.getErrorStream() == null && httpURLConnection.getResponseCode() == 200) {
            String contentType = httpURLConnection.getContentType();
            char c = 65535;
            int hashCode = contentType.hashCode();
            boolean z = true;
            if (hashCode != -1248325150) {
                if (hashCode == -43840953 && contentType.equals(HttpConstants.CONTENT_TYPE_JSON)) {
                    c = 1;
                }
            } else if (contentType.equals("application/zip")) {
                c = 0;
            }
            if (c != 0) {
                L.debug("Received json response.");
                fileExtension = FileExtension.Json;
                lottieResult = LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(new File(this.networkCache.writeTempCacheFile(httpURLConnection.getInputStream(), fileExtension).getAbsolutePath())), this.url);
            } else {
                L.debug("Handling zip response.");
                fileExtension = FileExtension.Zip;
                lottieResult = LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(new FileInputStream(this.networkCache.writeTempCacheFile(httpURLConnection.getInputStream(), fileExtension))), this.url);
            }
            if (lottieResult.getValue() != null) {
                this.networkCache.renameTempFile(fileExtension);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Completed fetch from network. Success: ");
            if (lottieResult.getValue() == null) {
                z = false;
            }
            sb2.append(z);
            L.debug(sb2.toString());
            return lottieResult;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        StringBuilder sb3 = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb3.append(readLine);
                sb3.append(10);
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Unable to fetch ");
                sb4.append(this.url);
                sb4.append(". Failed with ");
                sb4.append(httpURLConnection.getResponseCode());
                sb4.append("\n");
                sb4.append(sb3);
                return new LottieResult((Throwable) new IllegalArgumentException(sb4.toString()));
            }
        }
    }
}
