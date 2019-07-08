package com.mopub.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.DeviceUtils;
import com.mopub.volley.toolbox.BaseHttpStack;
import com.mopub.volley.toolbox.BasicNetwork;
import com.mopub.volley.toolbox.DiskBasedCache;
import com.mopub.volley.toolbox.HurlStack.UrlRewriter;
import com.mopub.volley.toolbox.ImageLoader;
import com.mopub.volley.toolbox.ImageLoader.ImageCache;
import java.io.File;

public class Networking {
    private static final String DEFAULT_USER_AGENT = System.getProperty("http.agent");
    private static volatile MaxWidthImageLoader sMaxWidthImageLoader;
    private static volatile MoPubRequestQueue sRequestQueue;
    private static UrlRewriter sUrlRewriter;
    private static boolean sUseHttps = false;
    private static volatile String sUserAgent;

    public static String getScheme() {
        return Constants.HTTPS;
    }

    @Nullable
    public static MoPubRequestQueue getRequestQueue() {
        return sRequestQueue;
    }

    @NonNull
    public static UrlRewriter getUrlRewriter(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        if (sUrlRewriter == null) {
            sUrlRewriter = new PlayServicesUrlRewriter();
        }
        return sUrlRewriter;
    }

    @NonNull
    public static MoPubRequestQueue getRequestQueue(@NonNull Context context) {
        MoPubRequestQueue moPubRequestQueue = sRequestQueue;
        if (moPubRequestQueue == null) {
            synchronized (Networking.class) {
                moPubRequestQueue = sRequestQueue;
                if (moPubRequestQueue == null) {
                    BasicNetwork basicNetwork = new BasicNetwork((BaseHttpStack) new RequestQueueHttpStack(getUserAgent(context.getApplicationContext()), getUrlRewriter(context), CustomSSLSocketFactory.getDefault(10000)));
                    StringBuilder sb = new StringBuilder();
                    sb.append(context.getCacheDir().getPath());
                    sb.append(File.separator);
                    sb.append("mopub-volley-cache");
                    File file = new File(sb.toString());
                    MoPubRequestQueue moPubRequestQueue2 = new MoPubRequestQueue(new DiskBasedCache(file, (int) DeviceUtils.diskCacheSizeBytes(file, 10485760)), basicNetwork);
                    sRequestQueue = moPubRequestQueue2;
                    moPubRequestQueue2.start();
                    moPubRequestQueue = moPubRequestQueue2;
                }
            }
        }
        return moPubRequestQueue;
    }

    @NonNull
    public static ImageLoader getImageLoader(@NonNull Context context) {
        MaxWidthImageLoader maxWidthImageLoader = sMaxWidthImageLoader;
        if (maxWidthImageLoader == null) {
            synchronized (Networking.class) {
                maxWidthImageLoader = sMaxWidthImageLoader;
                if (maxWidthImageLoader == null) {
                    MoPubRequestQueue requestQueue = getRequestQueue(context);
                    final AnonymousClass1 r3 = new LruCache<String, Bitmap>(DeviceUtils.memoryCacheSizeBytes(context)) {
                        /* access modifiers changed from: protected */
                        public int sizeOf(String str, Bitmap bitmap) {
                            if (bitmap != null) {
                                return bitmap.getRowBytes() * bitmap.getHeight();
                            }
                            return super.sizeOf(str, bitmap);
                        }
                    };
                    MaxWidthImageLoader maxWidthImageLoader2 = new MaxWidthImageLoader(requestQueue, context, new ImageCache() {
                        public Bitmap getBitmap(String str) {
                            return (Bitmap) r3.get(str);
                        }

                        public void putBitmap(String str, Bitmap bitmap) {
                            r3.put(str, bitmap);
                        }
                    });
                    sMaxWidthImageLoader = maxWidthImageLoader2;
                    maxWidthImageLoader = maxWidthImageLoader2;
                }
            }
        }
        return maxWidthImageLoader;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:15|16) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3 = DEFAULT_USER_AGENT;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0034 */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getUserAgent(@android.support.annotation.NonNull android.content.Context r3) {
        /*
            com.mopub.common.Preconditions.checkNotNull(r3)
            java.lang.String r0 = sUserAgent
            if (r0 != 0) goto L_0x003e
            java.lang.Class<com.mopub.network.Networking> r1 = com.mopub.network.Networking.class
            monitor-enter(r1)
            java.lang.String r0 = sUserAgent     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x0039
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0034 }
            r2 = 17
            if (r0 < r2) goto L_0x0019
            java.lang.String r3 = android.webkit.WebSettings.getDefaultUserAgent(r3)     // Catch:{ Exception -> 0x0034 }
            goto L_0x0036
        L_0x0019:
            android.os.Looper r0 = android.os.Looper.myLooper()     // Catch:{ Exception -> 0x0034 }
            android.os.Looper r2 = android.os.Looper.getMainLooper()     // Catch:{ Exception -> 0x0034 }
            if (r0 != r2) goto L_0x0031
            android.webkit.WebView r0 = new android.webkit.WebView     // Catch:{ Exception -> 0x0034 }
            r0.<init>(r3)     // Catch:{ Exception -> 0x0034 }
            android.webkit.WebSettings r3 = r0.getSettings()     // Catch:{ Exception -> 0x0034 }
            java.lang.String r3 = r3.getUserAgentString()     // Catch:{ Exception -> 0x0034 }
            goto L_0x0036
        L_0x0031:
            java.lang.String r3 = DEFAULT_USER_AGENT     // Catch:{ Exception -> 0x0034 }
            goto L_0x0036
        L_0x0034:
            java.lang.String r3 = DEFAULT_USER_AGENT     // Catch:{ all -> 0x003b }
        L_0x0036:
            sUserAgent = r3     // Catch:{ all -> 0x003b }
            r0 = r3
        L_0x0039:
            monitor-exit(r1)     // Catch:{ all -> 0x003b }
            goto L_0x003e
        L_0x003b:
            r3 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x003b }
            throw r3
        L_0x003e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.network.Networking.getUserAgent(android.content.Context):java.lang.String");
    }

    @NonNull
    public static String getCachedUserAgent() {
        String str = sUserAgent;
        return str == null ? DEFAULT_USER_AGENT : str;
    }

    @VisibleForTesting
    public static synchronized void clearForTesting() {
        synchronized (Networking.class) {
            sRequestQueue = null;
            sMaxWidthImageLoader = null;
            sUserAgent = null;
        }
    }

    @VisibleForTesting
    public static synchronized void setRequestQueueForTesting(MoPubRequestQueue moPubRequestQueue) {
        synchronized (Networking.class) {
            sRequestQueue = moPubRequestQueue;
        }
    }

    @VisibleForTesting
    public static synchronized void setImageLoaderForTesting(MaxWidthImageLoader maxWidthImageLoader) {
        synchronized (Networking.class) {
            sMaxWidthImageLoader = maxWidthImageLoader;
        }
    }

    @VisibleForTesting
    public static synchronized void setUserAgentForTesting(String str) {
        synchronized (Networking.class) {
            sUserAgent = str;
        }
    }

    public static void useHttps(boolean z) {
        sUseHttps = z;
    }

    public static boolean shouldUseHttps() {
        return sUseHttps;
    }

    public static String getBaseUrlScheme() {
        return shouldUseHttps() ? Constants.HTTPS : Constants.HTTP;
    }
}
