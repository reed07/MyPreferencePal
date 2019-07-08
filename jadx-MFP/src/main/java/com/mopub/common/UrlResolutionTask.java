package com.mopub.common;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

@VisibleForTesting
public class UrlResolutionTask extends AsyncTask<String, Void, String> {
    @NonNull
    private final UrlResolutionListener mListener;

    interface UrlResolutionListener {
        void onFailure(@NonNull String str, @Nullable Throwable th);

        void onSuccess(@NonNull String str);
    }

    public static void getResolvedUrl(@NonNull String str, @NonNull UrlResolutionListener urlResolutionListener) {
        try {
            AsyncTasks.safeExecuteOnExecutor(new UrlResolutionTask(urlResolutionListener), str);
        } catch (Exception e) {
            urlResolutionListener.onFailure("Failed to resolve url", e);
        }
    }

    UrlResolutionTask(@NonNull UrlResolutionListener urlResolutionListener) {
        this.mListener = urlResolutionListener;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String doInBackground(@Nullable String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        int i = 0;
        try {
            String str = strArr[0];
            String str2 = null;
            while (str != null && i < 10) {
                if (!UrlAction.OPEN_IN_APP_BROWSER.shouldTryHandlingUrl(Uri.parse(str)) || UrlAction.OPEN_NATIVE_BROWSER.shouldTryHandlingUrl(Uri.parse(str))) {
                    return str;
                }
                i++;
                str2 = str;
                str = getRedirectLocation(str);
            }
            return str2;
        } catch (IOException unused) {
            return null;
        } catch (URISyntaxException unused2) {
            return null;
        } catch (NullPointerException unused3) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002f  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getRedirectLocation(@android.support.annotation.NonNull java.lang.String r3) throws java.io.IOException, java.net.URISyntaxException {
        /*
            r2 = this;
            java.net.URL r0 = new java.net.URL
            r0.<init>(r3)
            r1 = 0
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ all -> 0x002b }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ all -> 0x002b }
            r1 = 0
            r0.setInstanceFollowRedirects(r1)     // Catch:{ all -> 0x0029 }
            java.lang.String r3 = resolveRedirectLocation(r3, r0)     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0028
            java.io.InputStream r1 = r0.getInputStream()
            if (r1 == 0) goto L_0x0025
            r1.close()     // Catch:{ IOException -> 0x0020 }
            goto L_0x0025
        L_0x0020:
            java.lang.String r1 = "IOException when closing httpUrlConnection. Ignoring."
            com.mopub.common.logging.MoPubLog.d(r1)
        L_0x0025:
            r0.disconnect()
        L_0x0028:
            return r3
        L_0x0029:
            r3 = move-exception
            goto L_0x002d
        L_0x002b:
            r3 = move-exception
            r0 = r1
        L_0x002d:
            if (r0 == 0) goto L_0x0041
            java.io.InputStream r1 = r0.getInputStream()
            if (r1 == 0) goto L_0x003e
            r1.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x003e
        L_0x0039:
            java.lang.String r1 = "IOException when closing httpUrlConnection. Ignoring."
            com.mopub.common.logging.MoPubLog.d(r1)
        L_0x003e:
            r0.disconnect()
        L_0x0041:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.UrlResolutionTask.getRedirectLocation(java.lang.String):java.lang.String");
    }

    @Nullable
    @VisibleForTesting
    static String resolveRedirectLocation(@NonNull String str, @NonNull HttpURLConnection httpURLConnection) throws IOException, URISyntaxException {
        URI uri = new URI(str);
        int responseCode = httpURLConnection.getResponseCode();
        String headerField = httpURLConnection.getHeaderField("location");
        if (responseCode < 300 || responseCode >= 400) {
            return null;
        }
        try {
            return uri.resolve(headerField).toString();
        } catch (IllegalArgumentException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid URL redirection. baseUrl=");
            sb.append(str);
            sb.append("\n redirectUrl=");
            sb.append(headerField);
            MoPubLog.d(sb.toString());
            throw new URISyntaxException(headerField, "Unable to parse invalid URL");
        } catch (NullPointerException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Invalid URL redirection. baseUrl=");
            sb2.append(str);
            sb2.append("\n redirectUrl=");
            sb2.append(headerField);
            MoPubLog.d(sb2.toString());
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(@Nullable String str) {
        super.onPostExecute(str);
        if (isCancelled() || str == null) {
            onCancelled();
        } else {
            this.mListener.onSuccess(str);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
        this.mListener.onFailure("Task for resolving url was cancelled", null);
    }
}
