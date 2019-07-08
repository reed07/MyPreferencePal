package com.inmobi.ads.cache;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.inmobi.ads.bm;
import com.inmobi.ads.c.b;
import com.inmobi.ads.c.k;
import com.inmobi.ads.cache.a.C0044a;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.g;
import com.squareup.picasso.Callback;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AssetStore implements c {
    public static final Object e = new Object();
    /* access modifiers changed from: private */
    public static final String f = "AssetStore";
    private static AssetStore o;
    private static final Object p = new Object();
    public d a;
    public b b;
    public ExecutorService c;
    public AtomicBoolean d = new AtomicBoolean(false);
    private k g;
    private ExecutorService h;
    private a i;
    private HandlerThread j;
    private AtomicBoolean k = new AtomicBoolean(false);
    private ConcurrentHashMap<String, a> l;
    private g.b m;
    private g.b n;
    private List<b> q = new ArrayList();
    /* access modifiers changed from: private */
    public final e r = new e() {
        public final void a(@NonNull d dVar, @NonNull String str, @NonNull a aVar) {
            AssetStore.f;
            StringBuilder sb = new StringBuilder("Asset fetch succeeded for URL ");
            sb.append(aVar.d);
            sb.append(" Updating location on disk (file://");
            sb.append(str);
            sb.append(")");
            a a2 = new C0044a().a(aVar.d, str, dVar, AssetStore.this.b.a, AssetStore.this.b.e).a();
            AssetStore.this.a;
            d.b(a2);
            a2.k = aVar.k;
            a2.a = aVar.a;
            AssetStore.this.a(a2, true);
            try {
                AssetStore.c(AssetStore.this);
            } catch (Exception e) {
                AssetStore.f;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }

        public final void a(@NonNull a aVar) {
            AssetStore.f;
            StringBuilder sb = new StringBuilder("Asset fetch failed for remote URL (");
            sb.append(aVar.d);
            sb.append(")");
            AssetStore.this.c(aVar.d);
            if (aVar.c <= 0) {
                AssetStore.f;
                AssetStore.this.a(aVar, false);
                AssetStore.this.a;
                d.c(aVar);
            } else {
                AssetStore.f;
                aVar.f = System.currentTimeMillis();
                AssetStore.this.a;
                d.b(aVar);
                if (!com.inmobi.commons.core.utilities.d.a()) {
                    AssetStore.this.a(aVar, false);
                }
            }
            try {
                AssetStore.c(AssetStore.this);
            } catch (Exception e) {
                AssetStore.f;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    };

    private class PicassoCallback implements Callback {
        private CountDownLatch b;
        private String c;

        PicassoCallback(CountDownLatch countDownLatch, String str) {
            this.b = countDownLatch;
            this.c = str;
        }

        public void onSuccess() {
            AssetStore.this.a(this.c);
            this.b.countDown();
        }

        public void onError() {
            AssetStore.this.b(this.c);
            this.b.countDown();
        }
    }

    private static final class a extends Handler {
        /* access modifiers changed from: private */
        public WeakReference<AssetStore> a;
        private final e b = new e() {
            public final void a(d dVar, String str, a aVar) {
                AssetStore assetStore = (AssetStore) a.this.a.get();
                if (assetStore != null) {
                    AssetStore.f;
                    StringBuilder sb = new StringBuilder("Asset fetch succeeded for URL ");
                    sb.append(aVar.d);
                    sb.append(" Updating location on disk (file://");
                    sb.append(str);
                    sb.append(")");
                    a a2 = new C0044a().a(aVar.d, str, dVar, assetStore.b.a, assetStore.b.e).a();
                    assetStore.a;
                    d.b(a2);
                    a2.k = aVar.k;
                    a2.a = aVar.a;
                    assetStore.a(a2, true);
                    a.this.a();
                    return;
                }
                AssetStore.f;
            }

            public final void a(a aVar) {
                AssetStore assetStore = (AssetStore) a.this.a.get();
                if (assetStore != null) {
                    AssetStore.f;
                    StringBuilder sb = new StringBuilder("Asset fetch failed for remote URL (");
                    sb.append(aVar.d);
                    sb.append(")");
                    assetStore.c(aVar.d);
                    if (aVar.c > 0) {
                        aVar.c--;
                        aVar.f = System.currentTimeMillis();
                        assetStore.a;
                        d.b(aVar);
                        a.this.b();
                        return;
                    }
                    assetStore.a(aVar, false);
                    a.this.a(aVar);
                    return;
                }
                AssetStore.f;
            }
        };

        a(@NonNull Looper looper, @NonNull AssetStore assetStore) {
            super(looper);
            this.a = new WeakReference<>(assetStore);
        }

        public final void handleMessage(Message message) {
            try {
                AssetStore assetStore = (AssetStore) this.a.get();
                switch (message.what) {
                    case 1:
                        if (assetStore != null) {
                            b h = assetStore.b;
                            if (h == null) {
                                com.inmobi.ads.c cVar = new com.inmobi.ads.c();
                                com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) cVar, (c) null);
                                h = cVar.n;
                            }
                            assetStore.a;
                            List e = d.e();
                            if (e.size() <= 0) {
                                AssetStore.f;
                                assetStore.c();
                                return;
                            }
                            AssetStore.f;
                            a aVar = (a) e.get(0);
                            Iterator it = e.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    a aVar2 = (a) it.next();
                                    if (!assetStore.l.containsKey(aVar.d)) {
                                        aVar = aVar2;
                                    }
                                }
                            }
                            Message obtain = Message.obtain();
                            obtain.what = 2;
                            long currentTimeMillis = System.currentTimeMillis() - aVar.f;
                            try {
                                if (currentTimeMillis < ((long) (h.b * 1000))) {
                                    sendMessageDelayed(obtain, ((long) (h.b * 1000)) - currentTimeMillis);
                                    return;
                                } else if (assetStore.l.containsKey(aVar.d)) {
                                    sendMessageDelayed(obtain, (long) (h.b * 1000));
                                    return;
                                } else {
                                    AssetStore.f;
                                    Message obtain2 = Message.obtain();
                                    obtain2.what = 2;
                                    obtain2.obj = aVar.d;
                                    sendMessage(obtain2);
                                    return;
                                }
                            } catch (Exception e2) {
                                AssetStore.f;
                                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e2.getMessage());
                                return;
                            }
                        }
                        break;
                    case 2:
                        if (assetStore != null) {
                            String str = (String) message.obj;
                            assetStore.a;
                            a b2 = d.b(str);
                            if (b2 == null) {
                                b();
                                return;
                            } else if (!b2.a()) {
                                int i = (assetStore.b.a - b2.c) + 1;
                                if (b2.c == 0) {
                                    b2.l = 11;
                                    assetStore.a(b2, false);
                                    a(b2);
                                    return;
                                } else if (!com.inmobi.commons.core.utilities.d.a()) {
                                    assetStore.a(b2, false);
                                    assetStore.c();
                                    return;
                                } else if (assetStore.a(b2, this.b)) {
                                    AssetStore.f;
                                    new StringBuilder("Cache miss in handler; attempting to cache asset: ").append(b2.d);
                                    AssetStore.f;
                                    StringBuilder sb = new StringBuilder("Download attempt # ");
                                    sb.append(i);
                                    sb.append(" in handler  to cache asset (");
                                    sb.append(b2.d);
                                    sb.append(")");
                                    return;
                                } else {
                                    AssetStore.f;
                                    new StringBuilder("Cache miss in handler; but already attempting: ").append(b2.d);
                                    b();
                                    return;
                                }
                            } else {
                                AssetStore.f;
                                a();
                                assetStore.a(b2, true);
                                return;
                            }
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        if (assetStore != null) {
                            a aVar3 = (a) message.obj;
                            assetStore.a;
                            d.c(aVar3);
                        }
                        b();
                        break;
                    default:
                        return;
                }
                b();
            } catch (Exception e3) {
                AssetStore.f;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            }
        }

        /* access modifiers changed from: private */
        public void a(a aVar) {
            try {
                Message obtain = Message.obtain();
                obtain.what = 4;
                obtain.obj = aVar;
                sendMessage(obtain);
            } catch (Exception e) {
                AssetStore.f;
                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
            }
        }

        /* access modifiers changed from: private */
        public void a() {
            try {
                sendEmptyMessage(3);
            } catch (Exception e) {
                AssetStore.f;
                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
            }
        }

        /* access modifiers changed from: private */
        public void b() {
            try {
                sendEmptyMessage(1);
            } catch (Exception e) {
                AssetStore.f;
                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
            }
        }
    }

    private AssetStore() {
        com.inmobi.ads.c cVar = new com.inmobi.ads.c();
        com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) cVar, (c) this);
        this.b = cVar.n;
        this.g = cVar.m;
        this.a = d.a();
        this.c = Executors.newCachedThreadPool();
        this.h = Executors.newFixedThreadPool(1);
        this.j = new HandlerThread("assetFetcher");
        this.j.start();
        this.i = new a(this.j.getLooper(), this);
        this.m = new g.b() {
            public final void a(boolean z) {
                if (z) {
                    AssetStore.c(AssetStore.this);
                } else {
                    AssetStore.this.c();
                }
            }
        };
        if (VERSION.SDK_INT >= 23) {
            this.n = new g.b() {
                public final void a(boolean z) {
                    if (z) {
                        AssetStore.this.c();
                    } else {
                        AssetStore.c(AssetStore.this);
                    }
                }
            };
        }
        this.l = new ConcurrentHashMap<>(2, 0.9f, 2);
    }

    public static AssetStore a() {
        AssetStore assetStore = o;
        if (assetStore == null) {
            synchronized (p) {
                assetStore = o;
                if (assetStore == null) {
                    assetStore = new AssetStore();
                    o = assetStore;
                }
            }
        }
        return assetStore;
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        com.inmobi.ads.c cVar = (com.inmobi.ads.c) aVar;
        this.b = cVar.n;
        this.g = cVar.m;
    }

    /* access modifiers changed from: private */
    public synchronized void a(String str) {
        boolean z;
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            b bVar = (b) this.q.get(i2);
            Set<bm> set = bVar.b;
            Set<String> set2 = bVar.c;
            Iterator it = set.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((bm) it.next()).b.equals(str)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z && !set2.contains(str)) {
                bVar.c.add(str);
                bVar.d++;
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void b(String str) {
        boolean z;
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            b bVar = (b) this.q.get(i2);
            Iterator it = bVar.b.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((bm) it.next()).b.equals(str)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                bVar.e++;
            }
        }
    }

    private synchronized void b(a aVar) {
        boolean z;
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            b bVar = (b) this.q.get(i2);
            Iterator it = bVar.b.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((bm) it.next()).b.equals(aVar.d)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z && !bVar.a.contains(aVar)) {
                bVar.a.add(aVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void b(b bVar) {
        if (!this.q.contains(bVar)) {
            this.q.add(bVar);
        }
    }

    private synchronized void a(List<b> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.q.remove(list.get(i2));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x011e, code lost:
        r8.l = 7;
        r8.c = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0126, code lost:
        if (r11.exists() == false) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0128, code lost:
        r11.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x012b, code lost:
        r4.disconnect();
        com.inmobi.commons.core.utilities.d.a((java.io.Closeable) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0132, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0196, code lost:
        r8.l = 8;
        r10.a.a(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x019e, code lost:
        r8.l = 8;
        r10.a.a(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01a6, code lost:
        r8.l = 3;
        r10.a.a(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01af, code lost:
        r8.l = 4;
        r10.a.a(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01b8, code lost:
        r8.l = 4;
        r10.a.a(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01c0, code lost:
        r2 = true;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[ExcHandler: IOException (unused java.io.IOException), SYNTHETIC, Splitter:B:10:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[ExcHandler: ProtocolException (unused java.net.ProtocolException), SYNTHETIC, Splitter:B:10:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[ExcHandler: MalformedURLException (unused java.net.MalformedURLException), SYNTHETIC, Splitter:B:10:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[ExcHandler: FileNotFoundException (unused java.io.FileNotFoundException), SYNTHETIC, Splitter:B:10:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:10:0x0063] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.inmobi.ads.cache.a r24, com.inmobi.ads.cache.e r25) {
        /*
            r23 = this;
            r1 = r23
            r8 = r24
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.inmobi.ads.cache.a> r0 = r1.l
            java.lang.String r2 = r8.d
            java.lang.Object r0 = r0.putIfAbsent(r2, r8)
            com.inmobi.ads.cache.a r0 = (com.inmobi.ads.cache.a) r0
            r9 = 0
            if (r0 != 0) goto L_0x01cc
            com.inmobi.ads.cache.c r10 = new com.inmobi.ads.cache.c
            r0 = r25
            r10.<init>(r0)
            com.inmobi.ads.c$k r0 = r1.g
            long r2 = r0.c
            com.inmobi.ads.c$k r0 = r1.g
            java.util.ArrayList<java.lang.String> r0 = r0.e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Fetching asset ("
            r4.<init>(r5)
            java.lang.String r5 = r8.d
            r4.append(r5)
            java.lang.String r5 = ")"
            r4.append(r5)
            boolean r4 = com.inmobi.commons.core.utilities.d.a()
            r12 = 8
            if (r4 != 0) goto L_0x0043
            r8.l = r12
            com.inmobi.ads.cache.e r0 = r10.a
            r0.a(r8)
            r2 = 1
            goto L_0x01cb
        L_0x0043:
            java.lang.String r4 = r8.d
            java.lang.String r5 = ""
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x01c2
            java.lang.String r4 = r8.d
            boolean r4 = android.webkit.URLUtil.isValidUrl(r4)
            if (r4 != 0) goto L_0x0057
            goto L_0x01c2
        L_0x0057:
            int r4 = r0.size()
            java.lang.String[] r4 = new java.lang.String[r4]
            java.lang.Object[] r0 = r0.toArray(r4)
            java.lang.String[] r0 = (java.lang.String[]) r0
            long r21 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.net.URL r4 = new java.net.URL     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.lang.String r5 = r8.d     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r4.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.lang.String r5 = "GET"
            r4.setRequestMethod(r5)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r5 = 60000(0xea60, float:8.4078E-41)
            r4.setConnectTimeout(r5)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r4.setReadTimeout(r5)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            int r5 = r4.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r6 = 400(0x190, float:5.6E-43)
            if (r5 >= r6) goto L_0x00bd
            java.lang.String r5 = r4.getContentType()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            int r6 = r0.length     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r7 = 0
        L_0x0090:
            if (r7 >= r6) goto L_0x00ad
            r15 = r0[r7]     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            if (r5 == 0) goto L_0x00aa
            java.util.Locale r11 = java.util.Locale.ENGLISH     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.lang.String r11 = r15.toLowerCase(r11)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.util.Locale r15 = java.util.Locale.ENGLISH     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.lang.String r15 = r5.toLowerCase(r15)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            boolean r11 = r11.equals(r15)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            if (r11 == 0) goto L_0x00aa
            r0 = 1
            goto L_0x00ae
        L_0x00aa:
            int r7 = r7 + 1
            goto L_0x0090
        L_0x00ad:
            r0 = 0
        L_0x00ae:
            if (r0 != 0) goto L_0x00bd
            r0 = 6
            r8.l = r0     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r8.c = r9     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            com.inmobi.ads.cache.e r0 = r10.a     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r0.a(r8)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r2 = 1
            goto L_0x01cb
        L_0x00bd:
            int r0 = r4.getContentLength()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            long r5 = (long) r0     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r0 = 7
            r15 = 0
            int r7 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r7 < 0) goto L_0x00eb
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.lang.String r11 = "ContentSize: "
            r7.<init>(r11)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r7.append(r5)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.lang.String r11 = " max size: "
            r7.append(r11)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r7.append(r2)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 <= 0) goto L_0x00eb
            r8.l = r0     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r8.c = r9     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            com.inmobi.ads.cache.e r0 = r10.a     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r0.a(r8)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r2 = 1
            goto L_0x01cb
        L_0x00eb:
            r4.connect()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.lang.String r5 = r8.d     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.io.File r11 = com.inmobi.commons.a.a.a(r5)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            boolean r5 = r11.exists()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            if (r5 == 0) goto L_0x00fd
            r11.delete()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
        L_0x00fd:
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r7.<init>(r11)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r6.<init>(r7)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r17 = r15
        L_0x0111:
            int r15 = r5.read(r7)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            if (r15 <= 0) goto L_0x0154
            long r13 = (long) r15     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            long r17 = r17 + r13
            int r13 = (r17 > r2 ? 1 : (r17 == r2 ? 0 : -1))
            if (r13 <= 0) goto L_0x0150
            r8.l = r0     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r8.c = r9     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            boolean r0 = r11.exists()     // Catch:{ Exception -> 0x0132, SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196 }
            if (r0 == 0) goto L_0x012b
            r11.delete()     // Catch:{ Exception -> 0x0132, SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196 }
        L_0x012b:
            r4.disconnect()     // Catch:{ Exception -> 0x0132, SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196 }
            com.inmobi.commons.core.utilities.d.a(r6)     // Catch:{ Exception -> 0x0132, SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196 }
            goto L_0x013f
        L_0x0132:
            r0 = move-exception
            com.inmobi.commons.core.a.a r2 = com.inmobi.commons.core.a.a.a()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            com.inmobi.commons.core.e.a r3 = new com.inmobi.commons.core.e.a     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r3.<init>(r0)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r2.a(r3)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
        L_0x013f:
            long r19 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r15 = r21
            com.inmobi.ads.cache.c.a(r15, r17, r19)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            com.inmobi.ads.cache.e r0 = r10.a     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r0.a(r8)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r2 = 1
            goto L_0x01cb
        L_0x0150:
            r6.write(r7, r9, r15)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            goto L_0x0111
        L_0x0154:
            r6.flush()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r4.disconnect()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            com.inmobi.commons.core.utilities.d.a(r6)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            long r13 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r15 = r21
            r19 = r13
            com.inmobi.ads.cache.c.a(r15, r17, r19)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            com.inmobi.commons.core.network.d r0 = new com.inmobi.commons.core.network.d     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.util.Map r2 = r4.getHeaderFields()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r0.d = r2     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r2 = r24
            r3 = r11
            r4 = r21
            r6 = r13
            java.lang.String r2 = com.inmobi.ads.cache.c.a(r2, r3, r4, r6)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r8.k = r2     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            long r13 = r13 - r21
            r8.a = r13     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            com.inmobi.ads.cache.e r2 = r10.a     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            java.lang.String r3 = r11.getAbsolutePath()     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            r2.a(r0, r3, r8)     // Catch:{ SocketTimeoutException -> 0x01b8, FileNotFoundException -> 0x01af, MalformedURLException -> 0x01a6, ProtocolException -> 0x019e, IOException -> 0x0196, Exception -> 0x018d }
            goto L_0x01c0
        L_0x018d:
            r8.l = r9
            com.inmobi.ads.cache.e r0 = r10.a
            r0.a(r8)
            r2 = 1
            goto L_0x01cb
        L_0x0196:
            r8.l = r12
            com.inmobi.ads.cache.e r0 = r10.a
            r0.a(r8)
            goto L_0x01c0
        L_0x019e:
            r8.l = r12
            com.inmobi.ads.cache.e r0 = r10.a
            r0.a(r8)
            goto L_0x01c0
        L_0x01a6:
            r2 = 3
            r8.l = r2
            com.inmobi.ads.cache.e r0 = r10.a
            r0.a(r8)
            goto L_0x01c0
        L_0x01af:
            r2 = 4
            r8.l = r2
            com.inmobi.ads.cache.e r0 = r10.a
            r0.a(r8)
            goto L_0x01c0
        L_0x01b8:
            r2 = 4
            r8.l = r2
            com.inmobi.ads.cache.e r0 = r10.a
            r0.a(r8)
        L_0x01c0:
            r2 = 1
            goto L_0x01cb
        L_0x01c2:
            r2 = 3
            r8.l = r2
            com.inmobi.ads.cache.e r0 = r10.a
            r0.a(r8)
            r2 = 1
        L_0x01cb:
            return r2
        L_0x01cc:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.cache.AssetStore.a(com.inmobi.ads.cache.a, com.inmobi.ads.cache.e):boolean");
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        this.l.remove(str);
    }

    /* access modifiers changed from: private */
    public synchronized void a(@NonNull a aVar, boolean z) {
        b(aVar);
        c(aVar.d);
        if (z) {
            a(aVar.d);
            e();
            return;
        }
        b(aVar.d);
        f();
    }

    /* access modifiers changed from: private */
    public synchronized void e() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            b bVar = (b) this.q.get(i2);
            if (bVar.d == bVar.b.size()) {
                try {
                    f a2 = bVar.a();
                    if (a2 != null) {
                        a2.b(bVar);
                    }
                    arrayList.add(bVar);
                } catch (Exception e2) {
                    new StringBuilder("Encountered unexpected error in onAssetFetchSucceeded handler: ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        }
        a((List<b>) arrayList);
    }

    /* access modifiers changed from: private */
    public synchronized void f() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            b bVar = (b) this.q.get(i2);
            if (bVar.e > 0) {
                try {
                    f a2 = bVar.a();
                    if (a2 != null) {
                        a2.a(bVar);
                    }
                    arrayList.add(bVar);
                } catch (Exception e2) {
                    new StringBuilder("Encountered unexpected error in onAssetFetchFailed handler: ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        }
        a((List<b>) arrayList);
    }

    public final void b() {
        this.d.set(false);
        if (!com.inmobi.commons.core.utilities.d.a()) {
            g();
            h();
            return;
        }
        synchronized (e) {
            if (this.k.compareAndSet(false, true)) {
                if (this.j == null) {
                    this.j = new HandlerThread("assetFetcher");
                    this.j.start();
                }
                if (this.i == null) {
                    this.i = new a(this.j.getLooper(), this);
                }
                if (d.e().isEmpty()) {
                    c();
                } else {
                    g();
                    h();
                    this.i.sendEmptyMessage(1);
                }
            }
        }
    }

    public static void a(a aVar) {
        d.c(aVar);
        File file = new File(aVar.e);
        if (file.exists()) {
            file.delete();
        }
    }

    public final void a(final b bVar) {
        this.c.execute(new Runnable() {
            public final void run() {
                AssetStore.this.b(bVar);
                AssetStore.f;
                StringBuilder sb = new StringBuilder("Attempting to cache ");
                sb.append(bVar.b.size());
                sb.append("remote URLs ");
                ArrayList arrayList = new ArrayList();
                ArrayList<String> arrayList2 = new ArrayList<>();
                for (bm bmVar : bVar.b) {
                    if (bmVar.b.trim().length() <= 0 || bmVar.a != 2) {
                        arrayList2.add(bmVar.b);
                    } else {
                        arrayList.add(bmVar.b);
                    }
                }
                AssetStore.a(AssetStore.this, (List) arrayList);
                AssetStore.this.e();
                AssetStore.this.f();
                for (String b2 : arrayList2) {
                    AssetStore.b(AssetStore.this, b2);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(a aVar) {
        File file = new File(aVar.e);
        long min = Math.min(System.currentTimeMillis() + (aVar.h - aVar.f), System.currentTimeMillis() + (this.b.e * 1000));
        C0044a aVar2 = new C0044a();
        String str = aVar.d;
        String str2 = aVar.e;
        int i2 = this.b.a;
        long j2 = aVar.i;
        aVar2.c = str;
        aVar2.d = str2;
        aVar2.b = i2;
        aVar2.g = min;
        aVar2.h = j2;
        a a2 = aVar2.a();
        a2.f = System.currentTimeMillis();
        d.b(a2);
        a2.k = c.a(aVar, file, aVar.f, aVar.f);
        a2.j = true;
        a(a2, true);
    }

    @TargetApi(23)
    private void g() {
        g.a();
        g.b bVar = this.m;
        if (VERSION.SDK_INT < 28) {
            g.a(bVar, "android.net.conn.CONNECTIVITY_CHANGE");
        } else {
            g.a(bVar, "SYSTEM_CONNECTIVITY_CHANGE");
        }
        if (VERSION.SDK_INT >= 23) {
            g.a();
            g.a(this.n, "android.os.action.DEVICE_IDLE_MODE_CHANGED");
        }
    }

    @TargetApi(23)
    private void h() {
        g.a().a(this.m);
        if (VERSION.SDK_INT >= 23) {
            g.a().a("android.os.action.DEVICE_IDLE_MODE_CHANGED", this.n);
        }
    }

    public final void c() {
        synchronized (e) {
            this.k.set(false);
            this.l.clear();
            if (this.j != null) {
                this.j.getLooper().quit();
                this.j.interrupt();
                this.j = null;
                this.i = null;
            }
        }
    }

    static /* synthetic */ void c(AssetStore assetStore) {
        if (!assetStore.d.get()) {
            assetStore.b();
        }
    }

    static /* synthetic */ void b(AssetStore assetStore, final String str) {
        a a2 = d.a(str);
        if (a2 == null || !a2.a()) {
            a a3 = new C0044a().a(str, assetStore.b.a, assetStore.b.e).a();
            if (d.a(str) == null) {
                assetStore.a.a(a3);
            }
            assetStore.h.execute(new Runnable() {
                public final void run() {
                    AssetStore.this.a;
                    a a2 = d.a(str);
                    if (a2 != null) {
                        if (a2.a()) {
                            AssetStore.this.c(a2);
                            return;
                        }
                        AssetStore assetStore = AssetStore.this;
                        if (assetStore.a(a2, assetStore.r)) {
                            AssetStore.f;
                            new StringBuilder("Cache miss; attempting to cache asset: ").append(str);
                            return;
                        }
                        AssetStore.f;
                        new StringBuilder("Cache miss; but already attempting: ").append(str);
                    }
                }
            });
            return;
        }
        StringBuilder sb = new StringBuilder("Cache hit; file exists location on disk (");
        sb.append(a2.e);
        sb.append(")");
        assetStore.c(a2);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.String>, for r5v0, types: [java.util.List, java.util.List<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.inmobi.ads.cache.AssetStore r4, java.util.List<java.lang.String> r5) {
        /*
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch
            int r1 = r5.size()
            r0.<init>(r1)
            java.util.Iterator r5 = r5.iterator()
        L_0x000d:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0032
            java.lang.Object r1 = r5.next()
            java.lang.String r1 = (java.lang.String) r1
            android.content.Context r2 = com.inmobi.commons.a.a.b()     // Catch:{ Exception -> 0x002e }
            com.squareup.picasso.Picasso r2 = com.inmobi.ads.bh.a(r2)     // Catch:{ Exception -> 0x002e }
            com.squareup.picasso.RequestCreator r2 = r2.load(r1)     // Catch:{ Exception -> 0x002e }
            com.inmobi.ads.cache.AssetStore$PicassoCallback r3 = new com.inmobi.ads.cache.AssetStore$PicassoCallback     // Catch:{ Exception -> 0x002e }
            r3.<init>(r0, r1)     // Catch:{ Exception -> 0x002e }
            r2.fetch(r3)     // Catch:{ Exception -> 0x002e }
            goto L_0x000d
        L_0x002e:
            r0.countDown()
            goto L_0x000d
        L_0x0032:
            r0.await()     // Catch:{ InterruptedException -> 0x0036 }
            return
        L_0x0036:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.cache.AssetStore.a(com.inmobi.ads.cache.AssetStore, java.util.List):void");
    }
}
