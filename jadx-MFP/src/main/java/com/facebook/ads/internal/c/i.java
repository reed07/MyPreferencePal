package com.facebook.ads.internal.c;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.internal.b.e;
import com.facebook.ads.internal.c.a.C0003a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.w.b.v;

@UiThread
public class i extends b {
    private final j e;
    @Nullable
    private e f;

    public i(j jVar) {
        super(jVar.a);
        this.e = jVar;
    }

    private void h() {
        a(2002, null);
        this.b.b();
        this.e.a(null);
    }

    /* access modifiers changed from: 0000 */
    public Message a() {
        Message obtain = Message.obtain(null, 2000);
        obtain.getData().putString("STR_PLACEMENT_KEY", this.e.b);
        obtain.getData().putString("STR_AD_ID_KEY", this.c);
        obtain.getData().putString("STR_BID_PAYLOAD_KEY", this.e.f);
        obtain.getData().putString("STR_EXTRA_HINTS_KEY", this.e.d);
        obtain.getData().putBoolean("BOOL_RV_FAIL_ON_CACHE_FAILURE_KEY", this.e.g);
        obtain.getData().putSerializable("SRL_RV_REWARD_DATA_KEY", this.e.e);
        obtain.getData().putBundle("BUNDLE_SETTINGS_KEY", AdInternalSettings.a);
        return obtain;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.os.Message r10) {
        /*
            r9 = this;
            com.facebook.ads.internal.c.j r0 = r9.e
            com.facebook.ads.RewardedVideoAd r0 = r0.a()
            if (r0 != 0) goto L_0x0019
            android.content.Context r10 = r9.a
            java.lang.String r0 = "api"
            int r1 = com.facebook.ads.internal.w.h.b.n
            java.lang.Exception r2 = new java.lang.Exception
            java.lang.String r3 = "Ad object is null"
            r2.<init>(r3)
            com.facebook.ads.internal.w.h.a.b(r10, r0, r1, r2)
            return
        L_0x0019:
            int r1 = r10.what
            r2 = 10
            r3 = 0
            if (r1 == r2) goto L_0x0108
            r2 = 2100(0x834, float:2.943E-42)
            if (r1 == r2) goto L_0x0053
            r4 = 2103(0x837, float:2.947E-42)
            if (r1 == r4) goto L_0x0108
            r4 = 2106(0x83a, float:2.951E-42)
            if (r1 == r4) goto L_0x003d
            switch(r1) {
                case 2010: goto L_0x0035;
                case 2011: goto L_0x0030;
                default: goto L_0x002f;
            }
        L_0x002f:
            goto L_0x009b
        L_0x0030:
            com.facebook.ads.internal.c.h r1 = r9.b
            java.lang.String r3 = "Received show confirmation."
            goto L_0x0039
        L_0x0035:
            com.facebook.ads.internal.c.h r1 = r9.b
            java.lang.String r3 = "Received load confirmation."
        L_0x0039:
            r1.a(r3)
            goto L_0x009b
        L_0x003d:
            com.facebook.ads.internal.c.a r1 = r9.d
            com.facebook.ads.internal.c.a$a r4 = com.facebook.ads.internal.c.a.C0003a.SHOWN
            r1.a(r4)
            com.facebook.ads.internal.c.h r1 = r9.b
            boolean r1 = r1.b
            if (r1 == 0) goto L_0x004d
            r9.h()
        L_0x004d:
            com.facebook.ads.internal.c.j r1 = r9.e
            r1.a(r3)
            goto L_0x009b
        L_0x0053:
            com.facebook.ads.internal.c.a r1 = r9.d
            com.facebook.ads.internal.c.a$a r4 = com.facebook.ads.internal.c.a.C0003a.LOADED
            r1.a(r4)
            android.os.Bundle r1 = r10.getData()
            java.lang.String r4 = "BUNDLE_EXTRAS_KEY"
            android.os.Bundle r1 = r1.getBundle(r4)
            if (r1 == 0) goto L_0x007b
            com.facebook.ads.internal.c.j r4 = r9.e
            java.lang.String r5 = "LONG_INVALIDATION_TIME_KEY"
            long r5 = r1.getLong(r5)
            r4.i = r5
            com.facebook.ads.internal.c.j r4 = r9.e
            java.lang.String r5 = "INT_RV_VIDEO_DURATION_KEY"
            int r1 = r1.getInt(r5)
            r4.h = r1
            goto L_0x004d
        L_0x007b:
            android.content.Context r1 = r9.a
            java.lang.String r4 = "api"
            int r5 = com.facebook.ads.internal.w.h.b.m
            java.lang.Exception r6 = new java.lang.Exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Missing bundle for message: "
            r7.append(r8)
            r7.append(r10)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            com.facebook.ads.internal.w.h.a.b(r1, r4, r5, r6)
            goto L_0x004d
        L_0x009b:
            com.facebook.ads.internal.c.j r1 = r9.e
            com.facebook.ads.RewardedVideoAdListener r1 = r1.c
            if (r1 != 0) goto L_0x00a2
            return
        L_0x00a2:
            int r10 = r10.what
            if (r10 == r2) goto L_0x0100
            switch(r10) {
                case 2104: goto L_0x00f8;
                case 2105: goto L_0x00f0;
                case 2106: goto L_0x00de;
                case 2107: goto L_0x00d6;
                case 2108: goto L_0x00c4;
                case 2109: goto L_0x00b2;
                case 2110: goto L_0x00aa;
                default: goto L_0x00a9;
            }
        L_0x00a9:
            goto L_0x00dd
        L_0x00aa:
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            r10.onRewardedVideoClosed()
            return
        L_0x00b2:
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            boolean r10 = r10 instanceof com.facebook.ads.S2SRewardedVideoAdListener
            if (r10 == 0) goto L_0x00c3
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            com.facebook.ads.S2SRewardedVideoAdListener r10 = (com.facebook.ads.S2SRewardedVideoAdListener) r10
            r10.onRewardServerFailed()
        L_0x00c3:
            return
        L_0x00c4:
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            boolean r10 = r10 instanceof com.facebook.ads.S2SRewardedVideoAdListener
            if (r10 == 0) goto L_0x00d5
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            com.facebook.ads.S2SRewardedVideoAdListener r10 = (com.facebook.ads.S2SRewardedVideoAdListener) r10
            r10.onRewardServerSuccess()
        L_0x00d5:
            return
        L_0x00d6:
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            r10.onRewardedVideoCompleted()
        L_0x00dd:
            return
        L_0x00de:
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            boolean r10 = r10 instanceof com.facebook.ads.RewardedVideoAdExtendedListener
            if (r10 == 0) goto L_0x00ef
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            com.facebook.ads.RewardedVideoAdExtendedListener r10 = (com.facebook.ads.RewardedVideoAdExtendedListener) r10
            r10.onRewardedVideoActivityDestroyed()
        L_0x00ef:
            return
        L_0x00f0:
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            r10.onLoggingImpression(r0)
            return
        L_0x00f8:
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            r10.onAdClicked(r0)
            return
        L_0x0100:
            com.facebook.ads.internal.c.j r10 = r9.e
            com.facebook.ads.RewardedVideoAdListener r10 = r10.c
            r10.onAdLoaded(r0)
            return
        L_0x0108:
            com.facebook.ads.internal.c.a r1 = r9.d
            com.facebook.ads.internal.c.a$a r2 = com.facebook.ads.internal.c.a.C0003a.ERROR
            r1.a(r2)
            com.facebook.ads.internal.c.h r1 = r9.b
            boolean r1 = r1.b
            if (r1 == 0) goto L_0x0118
            r9.h()
        L_0x0118:
            android.os.Bundle r1 = r10.getData()
            java.lang.String r2 = "BUNDLE_EXTRAS_KEY"
            android.os.Bundle r1 = r1.getBundle(r2)
            if (r1 == 0) goto L_0x0149
            java.lang.String r10 = "INT_ERROR_CODE_KEY"
            int r10 = r1.getInt(r10)
            java.lang.String r2 = "STR_ERROR_MESSAGE_KEY"
            java.lang.String r1 = r1.getString(r2)
            com.facebook.ads.internal.c.j r2 = r9.e
            com.facebook.ads.RewardedVideoAdListener r2 = r2.c
            if (r2 == 0) goto L_0x0143
            com.facebook.ads.internal.c.j r2 = r9.e
            com.facebook.ads.RewardedVideoAdListener r2 = r2.c
            com.facebook.ads.AdError r4 = new com.facebook.ads.AdError
            r4.<init>(r10, r1)
            r2.onError(r0, r4)
            goto L_0x0168
        L_0x0143:
            java.lang.String r10 = "FBAudienceNetwork"
            android.util.Log.e(r10, r1)
            goto L_0x0168
        L_0x0149:
            android.content.Context r0 = r9.a
            java.lang.String r1 = "api"
            int r2 = com.facebook.ads.internal.w.h.b.m
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Missing bundle for message: "
            r5.append(r6)
            r5.append(r10)
            java.lang.String r10 = r5.toString()
            r4.<init>(r10)
            com.facebook.ads.internal.w.h.a.b(r0, r1, r2, r4)
        L_0x0168:
            com.facebook.ads.internal.c.j r10 = r9.e
            r10.a(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.c.i.a(android.os.Message):void");
    }

    public void a(RewardData rewardData) {
        this.e.e = rewardData;
        if (this.b.b) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("SRL_RV_REWARD_DATA_KEY", rewardData);
            a(2003, bundle);
            return;
        }
        e eVar = this.f;
        if (eVar != null) {
            eVar.a(rewardData);
        }
    }

    public void a(RewardedVideoAd rewardedVideoAd, String str, boolean z) {
        a a = e.a(this.a, Integer.valueOf(0), Integer.valueOf(1));
        if (a != null) {
            a(10, AdErrorType.MISSING_DEPENDENCIES_ERROR, a.b());
        } else if (!this.d.a(C0003a.LOADING, "load()")) {
            this.e.a(rewardedVideoAd);
            e eVar = this.f;
            if (eVar != null) {
                eVar.a(str, z);
                return;
            }
            j jVar = this.e;
            jVar.f = str;
            jVar.g = z;
            if (!a(jVar.a)) {
                c();
            } else if (this.b.b) {
                b();
            } else {
                this.b.c = true;
                this.b.a();
            }
        }
    }

    public boolean a(RewardedVideoAd rewardedVideoAd, int i) {
        if (this.d.a(C0003a.SHOWING, "show()")) {
            return false;
        }
        this.e.a(rewardedVideoAd);
        if (this.b.b) {
            Bundle bundle = new Bundle();
            bundle.putInt("INT_RV_APP_ORIENTATION_KEY", i);
            a(2001, bundle);
            return true;
        }
        e eVar = this.f;
        if (eVar != null) {
            return eVar.a(i);
        }
        this.f = new e(this.e, this, this.c);
        this.f.a(i);
        return false;
    }

    public void c() {
        this.f = new e(this.e, this, this.c);
        this.f.a(this.e.f, this.e.g);
    }

    public void d() {
        if (this.b.b) {
            h();
        }
        e eVar = this.f;
        if (eVar != null) {
            eVar.a();
        }
        this.d.a(C0003a.DESTROYED);
    }

    public boolean f() {
        e eVar = this.f;
        if (eVar != null) {
            return eVar.d();
        }
        return this.d.a == C0003a.LOADED;
    }

    public boolean g() {
        e eVar = this.f;
        if (eVar != null) {
            return eVar.c();
        }
        return this.e.i > 0 && v.a() > this.e.i;
    }
}
