package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.adapters.v;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.settings.a.C0009a;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.b;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.google.android.exoplayer2.C;
import java.util.UUID;

public class p extends a {
    private static final String b = "p";
    private final String c = UUID.randomUUID().toString();
    private final l d = new l() {
        public void a(k kVar) {
            if (p.this.n != null) {
                p.this.n.c();
            }
        }
    };
    private final j e = new j() {
        public void a(i iVar) {
            if (p.this.n != null) {
                p.this.n.b();
            }
        }
    };
    private final d f = new d() {
        public void a(c cVar) {
            if (p.this.n != null) {
                p.this.n.h();
            }
        }
    };
    private final v g;
    private com.facebook.ads.internal.s.c h;
    @Nullable
    private b i;
    @Nullable
    private Uri j;
    @Nullable
    private String k;
    @Nullable
    private String l;
    @Nullable
    private String m;
    /* access modifiers changed from: private */
    @Nullable
    public q n;
    @Nullable
    private NativeAd o;

    public p(Context context) {
        super(context);
        this.g = new v(this, context);
        t();
    }

    public p(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new v(this, context);
        t();
    }

    public p(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.g = new v(this, context);
        t();
    }

    @TargetApi(21)
    public p(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.g = new v(this, context);
        t();
    }

    private void a(String str) {
        com.facebook.ads.internal.w.h.a.b(getContext(), "parsing", com.facebook.ads.internal.w.h.b.M, new com.facebook.ads.internal.protocol.b(AdErrorType.PARSER_FAILURE, str));
        if (AdInternalSettings.isDebugBuild()) {
            Log.w(b, str);
        }
    }

    private void t() {
        getEventBus().a((T[]) new f[]{this.d, this.e, this.f});
    }

    public void a() {
        String str;
        Context context = getContext();
        Intent intent = new Intent(context, AudienceNetworkActivity.getAdActivity());
        if (this.i == null) {
            str = "Must setClientToken first";
        } else if (this.j == null && this.l == null) {
            str = "Must setVideoURI or setVideoMPD first";
        } else {
            intent.putExtra("useNativeCtaButton", this.m);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, C0009a.FULL_SCREEN_VIDEO);
            intent.putExtra(AudienceNetworkActivity.VIDEO_URL, this.j.toString());
            String str2 = AudienceNetworkActivity.CLIENT_TOKEN;
            String str3 = this.k;
            if (str3 == null) {
                str3 = "";
            }
            intent.putExtra(str2, str3);
            intent.putExtra(AudienceNetworkActivity.VIDEO_MPD, this.l);
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, 13);
            intent.putExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, getCurrentPositionInMillis());
            intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.c);
            intent.putExtra(AudienceNetworkActivity.VIDEO_LOGGER, this.i.g());
            intent.putExtra("video_time_polling_interval", getVideoProgressReportIntervalMs());
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            a(false);
            setVisibility(8);
            context.startActivity(intent);
        }
        a(str);
        try {
            a(false);
            setVisibility(8);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            try {
                intent.setClass(context, InterstitialAdActivity.class);
                context.startActivity(intent);
            } catch (Exception e2) {
                com.facebook.ads.internal.o.b.a(com.facebook.ads.internal.o.a.a(e2, "Error occurred while loading fullscreen video activity."));
            }
        }
    }

    public void b() {
        NativeAd nativeAd = this.o;
        if (nativeAd != null) {
            nativeAd.onCtaBroadcast();
        }
    }

    @Nullable
    public q getListener() {
        return this.n;
    }

    public String getUniqueId() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.g.a();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.g.b();
        super.onDetachedFromWindow();
    }

    public void setAdEventManager(com.facebook.ads.internal.s.c cVar) {
        this.h = cVar;
    }

    public void setClientToken(@Nullable String str) {
        b bVar = this.i;
        if (bVar != null) {
            bVar.a();
        }
        this.k = str;
        this.i = str != null ? new b(getContext(), this.h, this, str) : null;
    }

    public void setEnableBackgroundVideo(boolean z) {
        this.a.setBackgroundPlaybackEnabled(z);
    }

    public void setListener(@Nullable q qVar) {
        this.n = qVar;
    }

    public void setNativeAd(@Nullable NativeAd nativeAd) {
        this.o = nativeAd;
    }

    public void setVideoCTA(@Nullable String str) {
        this.m = str;
    }

    public void setVideoMPD(@Nullable String str) {
        if (str == null || this.i != null) {
            this.l = str;
            super.setVideoMPD(str);
            return;
        }
        a("Must setClientToken first");
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null || this.i != null) {
            this.j = uri;
            super.setVideoURI(uri);
            return;
        }
        a("Must setClientToken first");
    }
}
