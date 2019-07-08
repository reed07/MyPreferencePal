package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.o.d;
import com.facebook.ads.internal.view.a.C0012a;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.b;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.e;
import com.facebook.ads.internal.view.i.b.f;
import com.facebook.ads.internal.view.i.b.g;
import com.facebook.ads.internal.view.i.b.h;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.q;
import com.facebook.ads.internal.w.b.x;

public class z implements a {
    private final l a = new l() {
        public void a(k kVar) {
            z.this.h.a("videoInterstitalEvent", (d) kVar);
        }
    };
    private final j b = new j() {
        public void a(i iVar) {
            z.this.h.a("videoInterstitalEvent", (d) iVar);
        }
    };
    private final com.facebook.ads.internal.view.i.b.d c = new com.facebook.ads.internal.view.i.b.d() {
        public void a(c cVar) {
            z.this.h.a("videoInterstitalEvent", (d) cVar);
        }
    };
    private final f d = new f() {
        public void a(e eVar) {
            z.this.e.finish();
        }
    };
    /* access modifiers changed from: private */
    public final AudienceNetworkActivity e;
    private final com.facebook.ads.internal.s.c f;
    private final a g;
    /* access modifiers changed from: private */
    public final C0012a h;
    private b i;
    private int j;

    public z(final AudienceNetworkActivity audienceNetworkActivity, com.facebook.ads.internal.s.c cVar, C0012a aVar) {
        this.e = audienceNetworkActivity;
        this.f = cVar;
        this.g = new a(audienceNetworkActivity);
        this.g.a((com.facebook.ads.internal.view.i.a.b) new com.facebook.ads.internal.view.i.c.b(audienceNetworkActivity));
        this.g.getEventBus().a((T[]) new com.facebook.ads.internal.o.f[]{this.a, this.b, this.c, this.d});
        this.h = aVar;
        this.g.setIsFullScreen(true);
        this.g.setVolume(1.0f);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(15);
        this.g.setLayoutParams(layoutParams);
        aVar.a((View) this.g);
        g gVar = new g(audienceNetworkActivity);
        gVar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                audienceNetworkActivity.finish();
            }
        });
        aVar.a((View) gVar);
    }

    public void a(int i2) {
        this.g.setVideoProgressReportIntervalMs(i2);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        String stringExtra = intent.getStringExtra("useNativeCtaButton");
        if (stringExtra != null && !stringExtra.isEmpty()) {
            com.facebook.ads.internal.view.e.b bVar = new com.facebook.ads.internal.view.e.b(audienceNetworkActivity, stringExtra);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            int i2 = (int) (x.b * 16.0f);
            layoutParams.setMargins(i2, i2, i2, i2);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            bVar.setLayoutParams(layoutParams);
            bVar.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    z.this.h.a("performCtaClick");
                }
            });
            this.h.a((View) bVar);
        }
        this.j = intent.getIntExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, 0);
        b bVar2 = new b((Context) audienceNetworkActivity, this.f, this.g, intent.getStringExtra(AudienceNetworkActivity.CLIENT_TOKEN), intent.getBundleExtra(AudienceNetworkActivity.VIDEO_LOGGER));
        this.i = bVar2;
        this.g.setVideoMPD(intent.getStringExtra(AudienceNetworkActivity.VIDEO_MPD));
        this.g.setVideoURI(intent.getStringExtra(AudienceNetworkActivity.VIDEO_URL));
        int i3 = this.j;
        if (i3 > 0) {
            this.g.a(i3);
        }
        if (intent.getBooleanExtra(AudienceNetworkActivity.AUTOPLAY, false)) {
            this.g.a(com.facebook.ads.internal.view.i.a.a.USER_STARTED);
        }
    }

    public void a(Bundle bundle) {
    }

    public void a(View view) {
        this.g.setControlsAnchorView(view);
    }

    public void a_(boolean z) {
        this.h.a("videoInterstitalEvent", (d) new g());
        this.g.e();
    }

    public void b(boolean z) {
        this.h.a("videoInterstitalEvent", (d) new h());
        this.g.a(com.facebook.ads.internal.view.i.a.a.USER_STARTED);
    }

    public void onDestroy() {
        this.h.a("videoInterstitalEvent", (d) new q(this.j, this.g.getCurrentPositionInMillis()));
        this.i.b(this.g.getCurrentPositionInMillis());
        this.g.g();
        this.g.l();
    }

    public void setListener(C0012a aVar) {
    }
}
