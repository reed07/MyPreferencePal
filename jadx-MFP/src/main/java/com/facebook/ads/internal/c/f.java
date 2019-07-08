package com.facebook.ads.internal.c;

import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.internal.b.e;
import com.facebook.ads.internal.c.a.C0003a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.w.b.v;
import com.facebook.stetho.websocket.CloseCodes;
import java.util.EnumSet;

@UiThread
public class f extends b {
    private final g e;
    @Nullable
    private d f;

    public f(g gVar) {
        super(gVar.a.getApplicationContext());
        this.e = gVar;
    }

    private void h() {
        a(1012, null);
        this.b.b();
        this.e.a(null);
    }

    /* access modifiers changed from: 0000 */
    public Message a() {
        Message obtain = Message.obtain(null, 1010);
        obtain.getData().putString("STR_PLACEMENT_KEY", this.e.b);
        obtain.getData().putString("STR_AD_ID_KEY", this.c);
        obtain.getData().putString("STR_BID_PAYLOAD_KEY", this.e.f);
        obtain.getData().putString("STR_EXTRA_HINTS_KEY", this.e.d);
        obtain.getData().putSerializable("SRL_INT_CACHE_FLAGS_KEY", this.e.e);
        obtain.getData().putBundle("BUNDLE_SETTINGS_KEY", AdInternalSettings.a);
        return obtain;
    }

    /* JADX INFO: used method not loaded: com.facebook.ads.internal.c.h.a(java.lang.String):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        r1.a(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.os.Message r9) {
        /*
            r8 = this;
            com.facebook.ads.internal.c.g r0 = r8.e
            com.facebook.ads.InterstitialAd r0 = r0.a()
            if (r0 != 0) goto L_0x0019
            android.content.Context r9 = r8.a
            java.lang.String r0 = "api"
            int r1 = com.facebook.ads.internal.w.h.b.n
            java.lang.Exception r2 = new java.lang.Exception
            java.lang.String r3 = "Ad object is null"
            r2.<init>(r3)
            com.facebook.ads.internal.w.h.a.b(r9, r0, r1, r2)
            return
        L_0x0019:
            int r1 = r9.what
            r2 = 10
            r3 = 0
            if (r1 == r2) goto L_0x00d7
            r2 = 1020(0x3fc, float:1.43E-42)
            if (r1 == r2) goto L_0x004e
            switch(r1) {
                case 1015: goto L_0x0046;
                case 1016: goto L_0x0041;
                case 1017: goto L_0x003c;
                default: goto L_0x0027;
            }
        L_0x0027:
            switch(r1) {
                case 1022: goto L_0x002b;
                case 1023: goto L_0x00d7;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x0090
        L_0x002b:
            com.facebook.ads.internal.c.a r1 = r8.d
            com.facebook.ads.internal.c.a$a r2 = com.facebook.ads.internal.c.a.C0003a.SHOWN
            r1.a(r2)
            com.facebook.ads.internal.c.h r1 = r8.b
            boolean r1 = r1.b
            if (r1 == 0) goto L_0x0090
            r8.h()
            goto L_0x0090
        L_0x003c:
            com.facebook.ads.internal.c.h r1 = r8.b
            java.lang.String r2 = "Received destroy confirmation."
            goto L_0x004a
        L_0x0041:
            com.facebook.ads.internal.c.h r1 = r8.b
            java.lang.String r2 = "Received show confirmation."
            goto L_0x004a
        L_0x0046:
            com.facebook.ads.internal.c.h r1 = r8.b
            java.lang.String r2 = "Received load confirmation."
        L_0x004a:
            r1.a(r2)
            goto L_0x0090
        L_0x004e:
            com.facebook.ads.internal.c.a r1 = r8.d
            com.facebook.ads.internal.c.a$a r2 = com.facebook.ads.internal.c.a.C0003a.LOADED
            r1.a(r2)
            android.os.Bundle r1 = r9.getData()
            java.lang.String r2 = "BUNDLE_EXTRAS_KEY"
            android.os.Bundle r1 = r1.getBundle(r2)
            if (r1 == 0) goto L_0x006c
            com.facebook.ads.internal.c.g r2 = r8.e
            java.lang.String r4 = "LONG_INVALIDATION_TIME_KEY"
            long r4 = r1.getLong(r4)
            r2.g = r4
            goto L_0x008b
        L_0x006c:
            android.content.Context r1 = r8.a
            java.lang.String r2 = "api"
            int r4 = com.facebook.ads.internal.w.h.b.m
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Missing bundle for message: "
            r6.append(r7)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            com.facebook.ads.internal.w.h.a.b(r1, r2, r4, r5)
        L_0x008b:
            com.facebook.ads.internal.c.g r1 = r8.e
            r1.a(r3)
        L_0x0090:
            com.facebook.ads.internal.c.g r1 = r8.e
            com.facebook.ads.InterstitialAdListener r1 = r1.c
            if (r1 != 0) goto L_0x0097
            return
        L_0x0097:
            int r9 = r9.what
            switch(r9) {
                case 1020: goto L_0x00cf;
                case 1021: goto L_0x00c7;
                case 1022: goto L_0x00bf;
                case 1023: goto L_0x009c;
                case 1024: goto L_0x00b7;
                case 1025: goto L_0x00af;
                case 1026: goto L_0x009d;
                default: goto L_0x009c;
            }
        L_0x009c:
            goto L_0x00d6
        L_0x009d:
            com.facebook.ads.internal.c.g r9 = r8.e
            com.facebook.ads.InterstitialAdListener r9 = r9.c
            boolean r9 = r9 instanceof com.facebook.ads.InterstitialAdExtendedListener
            if (r9 == 0) goto L_0x00d6
            com.facebook.ads.internal.c.g r9 = r8.e
            com.facebook.ads.InterstitialAdListener r9 = r9.c
            com.facebook.ads.InterstitialAdExtendedListener r9 = (com.facebook.ads.InterstitialAdExtendedListener) r9
            r9.onInterstitialActivityDestroyed()
            goto L_0x00d6
        L_0x00af:
            com.facebook.ads.internal.c.g r9 = r8.e
            com.facebook.ads.InterstitialAdListener r9 = r9.c
            r9.onLoggingImpression(r0)
            return
        L_0x00b7:
            com.facebook.ads.internal.c.g r9 = r8.e
            com.facebook.ads.InterstitialAdListener r9 = r9.c
            r9.onAdClicked(r0)
            return
        L_0x00bf:
            com.facebook.ads.internal.c.g r9 = r8.e
            com.facebook.ads.InterstitialAdListener r9 = r9.c
            r9.onInterstitialDismissed(r0)
            return
        L_0x00c7:
            com.facebook.ads.internal.c.g r9 = r8.e
            com.facebook.ads.InterstitialAdListener r9 = r9.c
            r9.onInterstitialDisplayed(r0)
            return
        L_0x00cf:
            com.facebook.ads.internal.c.g r9 = r8.e
            com.facebook.ads.InterstitialAdListener r9 = r9.c
            r9.onAdLoaded(r0)
        L_0x00d6:
            return
        L_0x00d7:
            com.facebook.ads.internal.c.a r1 = r8.d
            com.facebook.ads.internal.c.a$a r2 = com.facebook.ads.internal.c.a.C0003a.ERROR
            r1.a(r2)
            com.facebook.ads.internal.c.h r1 = r8.b
            boolean r1 = r1.b
            if (r1 == 0) goto L_0x00e7
            r8.h()
        L_0x00e7:
            android.os.Bundle r1 = r9.getData()
            java.lang.String r2 = "BUNDLE_EXTRAS_KEY"
            android.os.Bundle r1 = r1.getBundle(r2)
            if (r1 == 0) goto L_0x0118
            java.lang.String r9 = "INT_ERROR_CODE_KEY"
            int r9 = r1.getInt(r9)
            java.lang.String r2 = "STR_ERROR_MESSAGE_KEY"
            java.lang.String r1 = r1.getString(r2)
            com.facebook.ads.internal.c.g r2 = r8.e
            com.facebook.ads.InterstitialAdListener r2 = r2.c
            if (r2 == 0) goto L_0x0112
            com.facebook.ads.internal.c.g r2 = r8.e
            com.facebook.ads.InterstitialAdListener r2 = r2.c
            com.facebook.ads.AdError r4 = new com.facebook.ads.AdError
            r4.<init>(r9, r1)
            r2.onError(r0, r4)
            goto L_0x0137
        L_0x0112:
            java.lang.String r9 = "FBAudienceNetwork"
            android.util.Log.e(r9, r1)
            goto L_0x0137
        L_0x0118:
            android.content.Context r0 = r8.a
            java.lang.String r1 = "api"
            int r2 = com.facebook.ads.internal.w.h.b.m
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Missing bundle for message: "
            r5.append(r6)
            r5.append(r9)
            java.lang.String r9 = r5.toString()
            r4.<init>(r9)
            com.facebook.ads.internal.w.h.a.b(r0, r1, r2, r4)
        L_0x0137:
            com.facebook.ads.internal.c.g r9 = r8.e
            r9.a(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.c.f.a(android.os.Message):void");
    }

    public void a(InterstitialAd interstitialAd, EnumSet<CacheFlag> enumSet, String str) {
        a a = e.a(this.a, Integer.valueOf(0), Integer.valueOf(1));
        if (a != null) {
            a(10, AdErrorType.MISSING_DEPENDENCIES_ERROR, a.b());
        } else if (!this.d.a(C0003a.LOADING, "load()")) {
            this.e.a(interstitialAd);
            d dVar = this.f;
            if (dVar != null) {
                dVar.a(enumSet, str);
                return;
            }
            g gVar = this.e;
            gVar.e = enumSet;
            gVar.f = str;
            if (!a(gVar.a)) {
                c();
            } else if (this.b.b) {
                b();
            } else {
                this.b.c = true;
                this.b.a();
            }
        }
    }

    public boolean a(InterstitialAd interstitialAd) {
        if (this.d.a(C0003a.SHOWING, "show()")) {
            return false;
        }
        this.e.a(interstitialAd);
        if (this.b.b) {
            a(CloseCodes.UNEXPECTED_CONDITION, null);
            return true;
        }
        d dVar = this.f;
        if (dVar != null) {
            return dVar.e();
        }
        this.f = new d(this.e, this, this.c);
        this.f.e();
        return false;
    }

    public void c() {
        this.f = new d(this.e, this, this.c);
        this.f.a(this.e.e, this.e.f);
    }

    public void d() {
        if (this.b.b) {
            h();
        }
        d dVar = this.f;
        if (dVar != null) {
            dVar.a();
        }
        this.d.a(C0003a.DESTROYED);
    }

    public boolean f() {
        d dVar = this.f;
        if (dVar != null) {
            return dVar.d();
        }
        return this.d.a == C0003a.LOADED;
    }

    public boolean g() {
        d dVar = this.f;
        if (dVar != null) {
            return dVar.c();
        }
        return this.e.g > 0 && v.a() > this.e.g;
    }
}
