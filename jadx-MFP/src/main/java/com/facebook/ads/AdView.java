package com.facebook.ads;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.b.a;
import com.facebook.ads.internal.b.b;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.protocol.f;
import com.facebook.ads.internal.view.c.c;

public class AdView extends RelativeLayout implements Ad {
    /* access modifiers changed from: private */
    public final DisplayMetrics a;
    /* access modifiers changed from: private */
    public final d b;
    private final String c;
    /* access modifiers changed from: private */
    public b d;
    /* access modifiers changed from: private */
    public AdListener e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public c g;
    private String h;

    public AdView(Context context, final String str, AdSize adSize) {
        super(context);
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        this.a = getContext().getResources().getDisplayMetrics();
        this.b = adSize.toInternalAdSize();
        this.c = str;
        a aVar = new a(str, f.a(this.b), AdPlacementType.BANNER, adSize.toInternalAdSize(), 1);
        aVar.a(this.h);
        this.d = new b(context, aVar);
        this.d.a((com.facebook.ads.internal.adapters.a) new com.facebook.ads.internal.adapters.a() {
            public void a() {
                if (AdView.this.e != null) {
                    AdView.this.e.onAdClicked(AdView.this);
                }
            }

            public void a(View view) {
                if (view != null) {
                    AdView.this.f = view;
                    AdView.this.removeAllViews();
                    AdView adView = AdView.this;
                    adView.addView(adView.f);
                    if (AdView.this.f instanceof com.facebook.ads.internal.view.c.a) {
                        f.a(AdView.this.a, AdView.this.f, AdView.this.b);
                    }
                    if (AdView.this.e != null) {
                        AdView.this.e.onAdLoaded(AdView.this);
                    }
                    if (com.facebook.ads.internal.r.a.b(AdView.this.getContext())) {
                        AdView.this.g = new c();
                        AdView.this.g.a(str);
                        AdView.this.g.b(AdView.this.getContext().getPackageName());
                        if (AdView.this.d.b() != null) {
                            AdView.this.g.a(AdView.this.d.b().a());
                        }
                        if (AdView.this.f instanceof com.facebook.ads.internal.view.c.a) {
                            AdView.this.g.a(((com.facebook.ads.internal.view.c.a) AdView.this.f).getViewabilityChecker());
                        }
                        AdView.this.f.setOnLongClickListener(new OnLongClickListener() {
                            public boolean onLongClick(View view) {
                                AdView.this.g.setBounds(0, 0, AdView.this.f.getWidth(), AdView.this.f.getHeight());
                                AdView.this.g.a(!AdView.this.g.a());
                                return true;
                            }
                        });
                        AdView.this.f.getOverlay().add(AdView.this.g);
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("Cannot present null view");
            }

            public void a(AdAdapter adAdapter) {
                if (AdView.this.d != null) {
                    AdView.this.d.e();
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (AdView.this.e != null) {
                    AdView.this.e.onError(AdView.this, AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void b() {
                if (AdView.this.e != null) {
                    AdView.this.e.onLoggingImpression(AdView.this);
                }
            }
        });
    }

    private void a(String str) {
        this.d.b(str);
    }

    public void destroy() {
        b bVar = this.d;
        if (bVar != null) {
            bVar.a(true);
            this.d = null;
        }
        if (this.g != null && com.facebook.ads.internal.r.a.b(getContext())) {
            this.g.b();
            this.f.getOverlay().remove(this.g);
        }
        removeAllViews();
        this.f = null;
        this.e = null;
    }

    @Deprecated
    public void disableAutoRefresh() {
    }

    public String getPlacementId() {
        return this.c;
    }

    public boolean isAdInvalidated() {
        b bVar = this.d;
        return bVar == null || bVar.g();
    }

    public void loadAd() {
        a((String) null);
    }

    public void loadAdFromBid(String str) {
        a(str);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        View view = this.f;
        if (view != null) {
            f.a(this.a, view, this.b);
        }
    }

    public void setAdListener(AdListener adListener) {
        this.e = adListener;
    }

    public void setExtraHints(ExtraHints extraHints) {
        this.h = extraHints.getHints();
    }
}
