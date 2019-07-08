package com.facebook.ads.internal.view.i;

import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.w.b.r;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.UserDataStore;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c implements r<Bundle> {
    /* access modifiers changed from: private */
    public final String a;
    private boolean b;
    private final Context c;
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.s.c d;
    private final a e;
    private final com.facebook.ads.internal.d.a f;
    private int g;
    private int h;
    @Nullable
    private String i;
    @Nullable
    private String j;
    private final e k;
    @Nullable
    private final Map<String, String> l;

    public interface a {
        int getCurrentPositionInMillis();

        boolean getGlobalVisibleRect(Rect rect);

        long getInitialBufferTime();

        int getMeasuredHeight();

        int getMeasuredWidth();

        com.facebook.ads.internal.view.i.a.a getVideoStartReason();

        @Nullable
        View getView();

        float getVolume();

        boolean h();

        boolean i();
    }

    protected enum b {
        PLAY(0),
        SKIP(1),
        TIME(2),
        MRC(3),
        PAUSE(4),
        RESUME(5),
        MUTE(6),
        UNMUTE(7),
        VIEWABLE_IMPRESSION(10);
        
        public final int j;

        private b(int i) {
            this.j = i;
        }
    }

    public c(Context context, com.facebook.ads.internal.s.c cVar, a aVar, List<com.facebook.ads.internal.d.b> list, String str) {
        this(context, cVar, aVar, list, str, null);
    }

    public c(Context context, com.facebook.ads.internal.s.c cVar, a aVar, List<com.facebook.ads.internal.d.b> list, String str, @Nullable Bundle bundle) {
        this(context, cVar, aVar, list, str, bundle, null);
    }

    public c(Context context, com.facebook.ads.internal.s.c cVar, a aVar, List<com.facebook.ads.internal.d.b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        List<com.facebook.ads.internal.d.b> list2 = list;
        Bundle bundle2 = bundle;
        this.b = true;
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = null;
        this.c = context;
        this.d = cVar;
        this.e = aVar;
        this.a = str;
        this.l = map;
        AnonymousClass1 r0 = new com.facebook.ads.internal.d.b(0.5d, -1.0d, 2.0d, true) {
            /* access modifiers changed from: protected */
            public void a(boolean z, boolean z2, com.facebook.ads.internal.d.c cVar) {
                if (z2) {
                    c.this.d.e(c.this.a, c.this.a(b.MRC));
                }
            }
        };
        list2.add(r0);
        AnonymousClass2 r02 = new com.facebook.ads.internal.d.b(1.0E-7d, -1.0d, 0.001d, false) {
            /* access modifiers changed from: protected */
            public void a(boolean z, boolean z2, com.facebook.ads.internal.d.c cVar) {
                if (z2) {
                    c.this.d.e(c.this.a, c.this.a(b.VIEWABLE_IMPRESSION));
                }
            }
        };
        list2.add(r02);
        if (bundle2 != null) {
            this.f = new com.facebook.ads.internal.d.a(aVar.getView(), list2, bundle2.getBundle("adQualityManager"));
            this.g = bundle2.getInt("lastProgressTimeMS");
            this.h = bundle2.getInt("lastBoundaryTimeMS");
        } else {
            this.f = new com.facebook.ads.internal.d.a(aVar.getView(), list2);
        }
        this.k = new e(new Handler(), this);
    }

    /* access modifiers changed from: private */
    public Map<String, String> a(b bVar) {
        return a(bVar, this.e.getCurrentPositionInMillis());
    }

    private Map<String, String> a(b bVar, int i2) {
        HashMap hashMap = new HashMap();
        boolean z = !this.e.i();
        hashMap.put(AudienceNetworkActivity.AUTOPLAY, this.e.getVideoStartReason() == com.facebook.ads.internal.view.i.a.a.AUTO_STARTED ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        hashMap.put("inline", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
        hashMap.put("exoplayer", String.valueOf(this.e.h()));
        hashMap.put("prep", Long.toString(this.e.getInitialBufferTime()));
        com.facebook.ads.internal.d.c c2 = this.f.c();
        com.facebook.ads.internal.d.c.a c3 = c2.c();
        hashMap.put("vwa", String.valueOf(c3.d()));
        hashMap.put("vwm", String.valueOf(c3.c()));
        hashMap.put("vwmax", String.valueOf(c3.e()));
        hashMap.put("vtime_ms", String.valueOf(c3.g() * 1000.0d));
        hashMap.put("mcvt_ms", String.valueOf(c3.h() * 1000.0d));
        String str = this.i;
        if (str != null) {
            hashMap.put("vw_d", str);
        }
        String str2 = this.j;
        if (str2 != null) {
            hashMap.put("vw_rsn", str2);
        }
        com.facebook.ads.internal.d.c.a d2 = c2.d();
        hashMap.put("vla", String.valueOf(d2.d()));
        hashMap.put("vlm", String.valueOf(d2.c()));
        hashMap.put("vlmax", String.valueOf(d2.e()));
        hashMap.put("atime_ms", String.valueOf(d2.g() * 1000.0d));
        hashMap.put("mcat_ms", String.valueOf(d2.h() * 1000.0d));
        hashMap.put("ptime", String.valueOf(((float) this.h) / 1000.0f));
        hashMap.put(TimestampAnalyticsHelper.ATTR_TIME, String.valueOf(((float) i2) / 1000.0f));
        Rect rect = new Rect();
        this.e.getGlobalVisibleRect(rect);
        hashMap.put("pt", String.valueOf(rect.top));
        hashMap.put("pl", String.valueOf(rect.left));
        hashMap.put(UserDataStore.PHONE, String.valueOf(this.e.getMeasuredHeight()));
        hashMap.put("pw", String.valueOf(this.e.getMeasuredWidth()));
        WindowManager windowManager = (WindowManager) this.c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        hashMap.put("vph", String.valueOf(displayMetrics.heightPixels));
        hashMap.put("vpw", String.valueOf(displayMetrics.widthPixels));
        Map<String, String> map = this.l;
        if (map != null) {
            hashMap.putAll(map);
        }
        hashMap.put("action", String.valueOf(bVar.j));
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        a(i2, false, false);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3) {
        a(i2, true, false);
        this.h = i3;
        this.g = i3;
        this.f.a();
        this.f.b();
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, boolean z, boolean z2) {
        if (((double) i2) > 0.0d) {
            int i3 = this.g;
            if (i2 >= i3) {
                if (i2 > i3) {
                    this.f.a((double) (((float) (i2 - i3)) / 1000.0f), (double) d());
                    this.g = i2;
                    if (z2 || i2 - this.h >= 5000) {
                        this.d.e(this.a, a(b.TIME, i2));
                        this.h = this.g;
                        this.f.a();
                        return;
                    }
                }
                if (z) {
                    this.d.e(this.a, a(b.TIME, i2));
                }
            }
        }
    }

    public void b() {
        this.c.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.k);
    }

    public void b(int i2) {
        a(i2, true, false);
        this.h = 0;
        this.g = 0;
        this.f.a();
        this.f.b();
    }

    public void c() {
        this.c.getContentResolver().unregisterContentObserver(this.k);
    }

    /* access modifiers changed from: protected */
    public float d() {
        float f2;
        AudioManager audioManager = (AudioManager) this.c.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager != null) {
            int streamVolume = audioManager.getStreamVolume(3);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (streamMaxVolume > 0) {
                f2 = (((float) streamVolume) * 1.0f) / ((float) streamMaxVolume);
                return f2 * this.e.getVolume();
            }
        }
        f2 = BitmapDescriptorFactory.HUE_RED;
        return f2 * this.e.getVolume();
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        boolean z;
        if (((double) d()) < 0.05d) {
            if (this.b) {
                this.d.e(this.a, a(b.MUTE));
                z = false;
            } else {
                return;
            }
        } else if (!this.b) {
            this.d.e(this.a, a(b.UNMUTE));
            z = true;
        } else {
            return;
        }
        this.b = z;
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        this.d.e(this.a, a(b.SKIP));
    }

    public Bundle g() {
        a(j(), j());
        Bundle bundle = new Bundle();
        bundle.putInt("lastProgressTimeMS", this.g);
        bundle.putInt("lastBoundaryTimeMS", this.h);
        bundle.putBundle("adQualityManager", this.f.g());
        return bundle;
    }

    /* access modifiers changed from: 0000 */
    public void h() {
        this.d.e(this.a, a(b.PAUSE));
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        this.d.e(this.a, a(b.RESUME));
    }

    public int j() {
        return this.g;
    }
}
