package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.data.aa;
import com.google.ads.interactivemedia.v3.impl.data.c;

/* compiled from: IMASDK */
public final class aed implements aep {
    private final VideoAdPlayer a;
    private final acg b;
    private final acl c;
    private final aeq d;
    private final ace e;
    private boolean f;
    private boolean g;
    private boolean h;

    public aed(String str, aec aec, aeb aeb, acl acl, AdDisplayContainer adDisplayContainer, Context context) throws AdError {
        this(str, aec, aeb, acl, adDisplayContainer, null, null, context);
    }

    private aed(String str, aec aec, aeb aeb, acl acl, AdDisplayContainer adDisplayContainer, acg acg, aeq aeq, Context context) throws AdError {
        this.g = false;
        this.h = false;
        if (adDisplayContainer.getPlayer() != null) {
            this.a = adDisplayContainer.getPlayer();
            this.f = true;
        } else if (VERSION.SDK_INT >= 16) {
            this.a = new ade(context, adDisplayContainer.getAdContainer());
            this.f = false;
        } else {
            throw new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Ad Player was not provided. SDK-owned ad playback requires API 16+");
        }
        this.b = new acg(this.a, aec.a());
        this.c = acl;
        this.d = new aeq(aeb.b(), adDisplayContainer.getAdContainer());
        this.e = new ace(aeb, str, this.b, this.a);
    }

    public final void a(boolean z) {
        this.b.a(this.e);
        this.g = z;
    }

    public final VideoProgressUpdate getAdProgress() {
        return this.a.getAdProgress();
    }

    public final void a(adr adr, aa aaVar) {
        int ordinal = adr.ordinal();
        if (ordinal != 33) {
            if (ordinal == 45) {
                this.a.resumeAd();
            } else if (ordinal != 54 && ordinal != 56) {
                if (ordinal != 61) {
                    switch (ordinal) {
                        case 41:
                            this.a.pauseAd();
                            return;
                        case 42:
                            if (f()) {
                                ((aej) this.a).a();
                            }
                            this.a.playAd();
                            return;
                    }
                } else if (f()) {
                    ((aej) this.a).b();
                }
            }
        } else if (aaVar == null || aaVar.videoUrl == null) {
            this.c.a((AdErrorEvent) new acc(new AdError(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Load message must contain video url.")));
        } else {
            if (!this.h) {
                this.a.addCallback(this.e);
                this.b.b();
                this.h = true;
            }
            this.a.loadAd(aaVar.videoUrl);
        }
    }

    public final void a() {
        this.b.c();
        this.a.removeCallback(this.e);
        this.h = false;
    }

    public final void b() {
        this.a.stopAd();
    }

    public final void onAdError(AdErrorEvent adErrorEvent) {
        e();
    }

    public final void c() {
        Log.d("SDK_DEBUG", "Destroying NativeVideoDisplay");
        this.b.b(this.e);
        if (aes.a) {
            this.d.d();
        } else {
            this.d.b();
        }
        a();
        if (f()) {
            VideoAdPlayer videoAdPlayer = this.a;
            if (videoAdPlayer instanceof aej) {
                ((aej) videoAdPlayer).c();
            }
        }
    }

    public final boolean d() {
        return this.f;
    }

    private final boolean f() {
        return !this.f;
    }

    /* access modifiers changed from: 0000 */
    public final void a(c cVar) {
        if (!this.g || !cVar.canDisableUi()) {
            cVar.setUiDisabled(false);
            if (!aes.a || !cVar.isLinear()) {
                this.d.a(cVar);
            } else {
                this.d.c();
            }
        } else {
            cVar.setUiDisabled(true);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void e() {
        if (aes.a) {
            this.d.d();
        } else {
            this.d.a();
        }
    }
}
