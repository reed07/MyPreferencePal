package com.facebook.ads.internal.s;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.v.a.n;
import com.facebook.ads.internal.v.a.p;
import com.facebook.ads.internal.w.e.d;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.mopub.common.Constants;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class b {
    private static final String a = "b";
    private final a b;
    /* access modifiers changed from: private */
    public final ThreadPoolExecutor c;
    private final ConnectivityManager d;
    private final com.facebook.ads.internal.v.a.a e;
    private final Handler f;
    private final long g;
    private final long h;
    private final Context i;
    /* access modifiers changed from: private */
    public final Runnable j = new Runnable() {
        public void run() {
            b.a(b.this);
            if (b.this.n > 0) {
                try {
                    Thread.sleep(b.this.n);
                } catch (InterruptedException unused) {
                }
            }
            b.this.c();
        }
    };
    private final Runnable k = new Runnable() {
        public void run() {
            b.this.l = false;
            if (b.this.c.getQueue().isEmpty()) {
                b.this.c.execute(b.this.j);
            }
        }
    };
    /* access modifiers changed from: private */
    public volatile boolean l;
    private int m;
    /* access modifiers changed from: private */
    public long n;

    public interface a {
        JSONObject a();

        boolean a(JSONArray jSONArray);

        void b();

        void b(JSONArray jSONArray);

        void c();

        boolean d();
    }

    @UiThread
    b(Context context, a aVar) {
        this.b = aVar;
        this.i = context;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.c = threadPoolExecutor;
        this.d = (ConnectivityManager) context.getSystemService("connectivity");
        this.e = d.b(context);
        this.f = new Handler(Looper.getMainLooper());
        this.g = com.facebook.ads.internal.r.a.v(context);
        this.h = com.facebook.ads.internal.r.a.w(context);
    }

    static /* synthetic */ int a(b bVar) {
        int i2 = bVar.m + 1;
        bVar.m = i2;
        return i2;
    }

    private void a(long j2) {
        this.f.postDelayed(this.k, j2);
    }

    private void d() {
        int i2 = this.m;
        if (i2 >= 5) {
            e();
            b();
            return;
        }
        this.n = i2 == 1 ? AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS : this.n * 2;
        a();
    }

    private void e() {
        this.m = 0;
        this.n = 0;
        if (this.c.getQueue().size() == 0) {
            this.b.b();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.l = true;
        this.f.removeCallbacks(this.k);
        a(this.g);
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        if (!this.l) {
            this.l = true;
            this.f.removeCallbacks(this.k);
            a(this.h);
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void c() {
        a aVar;
        JSONArray jSONArray;
        try {
            NetworkInfo activeNetworkInfo = this.d.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnectedOrConnecting()) {
                    JSONObject a2 = this.b.a();
                    if (a2 == null) {
                        e();
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("attempt", String.valueOf(this.m));
                    a2.put("data", jSONObject);
                    p pVar = new p();
                    pVar.put("payload", a2.toString());
                    com.facebook.ads.internal.v.a.a aVar2 = this.e;
                    Context context = this.i;
                    String urlPrefix = AdInternalSettings.getUrlPrefix();
                    String format = TextUtils.isEmpty(urlPrefix) ? "https://www.facebook.com/adnw_logging/" : String.format(Locale.US, "https://www.%s.facebook.com/adnw_logging/", new Object[]{urlPrefix});
                    String M = com.facebook.ads.internal.r.a.M(context);
                    if (!TextUtils.isEmpty(M)) {
                        format = format.replace("www", M);
                    }
                    n b2 = aVar2.b(format, pVar);
                    String e2 = b2 != null ? b2.e() : null;
                    if (TextUtils.isEmpty(e2)) {
                        if (a2.has(Constants.VIDEO_TRACKING_EVENTS_KEY)) {
                            aVar = this.b;
                            jSONArray = a2.getJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
                        }
                        d();
                        return;
                    }
                    if (b2.a() == 200) {
                        if (this.b.a(new JSONArray(e2))) {
                            if (this.b.d()) {
                            }
                        }
                        d();
                        return;
                    } else if (b2.a() != 413 || !com.facebook.ads.internal.r.a.J(this.i)) {
                        if (a2.has(Constants.VIDEO_TRACKING_EVENTS_KEY)) {
                            aVar = this.b;
                            jSONArray = a2.getJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
                        }
                        d();
                        return;
                    } else {
                        this.b.c();
                    }
                    e();
                    return;
                    aVar.b(jSONArray);
                    d();
                    return;
                }
            }
            a(this.h);
        } catch (Exception unused) {
            d();
        }
    }
}
