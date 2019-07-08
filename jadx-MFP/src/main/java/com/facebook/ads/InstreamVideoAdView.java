package com.facebook.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.a.a;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.f;
import com.facebook.ads.internal.adapters.n;
import com.facebook.ads.internal.b.d;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.view.c.c;
import java.util.EnumSet;

public class InstreamVideoAdView extends RelativeLayout implements Ad {
    /* access modifiers changed from: private */
    public final Context a;
    /* access modifiers changed from: private */
    public final String b;
    private final AdSize c;
    /* access modifiers changed from: private */
    public d d;
    @Nullable
    private f e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    @Nullable
    public InstreamVideoAdListener g;
    /* access modifiers changed from: private */
    @Nullable
    public View h;
    @Nullable
    private Bundle i;
    /* access modifiers changed from: private */
    @Nullable
    public c j;

    public InstreamVideoAdView(Context context, Bundle bundle) {
        this(context, bundle.getString("placementID"), (AdSize) bundle.get("adSize"));
        this.i = bundle;
    }

    public InstreamVideoAdView(Context context, String str, AdSize adSize) {
        super(context);
        this.f = false;
        this.a = context;
        this.b = str;
        this.c = adSize;
        this.d = getController();
    }

    private void a(String str) {
        if (this.i != null) {
            this.e = (f) new com.facebook.ads.internal.adapters.d().a(AdPlacementType.INSTREAM);
            this.e.a(getContext(), (a) new a() {
                public void a(n nVar) {
                    InstreamVideoAdView.this.f = true;
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdLoaded(InstreamVideoAdView.this);
                    }
                }

                public void a(n nVar, View view) {
                    if (view != null) {
                        InstreamVideoAdView.this.h = view;
                        InstreamVideoAdView.this.removeAllViews();
                        InstreamVideoAdView.this.h.setLayoutParams(new LayoutParams(-1, -1));
                        InstreamVideoAdView instreamVideoAdView = InstreamVideoAdView.this;
                        instreamVideoAdView.addView(instreamVideoAdView.h);
                        return;
                    }
                    throw new IllegalStateException("Cannot present null view");
                }

                public void a(n nVar, AdError adError) {
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onError(InstreamVideoAdView.this, adError);
                    }
                }

                public void b(n nVar) {
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdClicked(InstreamVideoAdView.this);
                    }
                }

                public void c(n nVar) {
                }

                public void d(n nVar) {
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdVideoComplete(InstreamVideoAdView.this);
                    }
                }
            }, this.d.g, this.i.getBundle("adapter"), EnumSet.of(CacheFlag.NONE));
            return;
        }
        this.d.b(str);
    }

    private d getController() {
        com.facebook.ads.internal.b.a aVar = new com.facebook.ads.internal.b.a(this.b, e.INSTREAM_VIDEO, AdPlacementType.INSTREAM, this.c.toInternalAdSize(), 1);
        this.d = new d(getContext(), aVar);
        this.d.a((com.facebook.ads.internal.adapters.a) new com.facebook.ads.internal.adapters.a() {
            public void a() {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onAdClicked(InstreamVideoAdView.this);
                }
            }

            public void a(View view) {
                if (view != null) {
                    InstreamVideoAdView.this.h = view;
                    InstreamVideoAdView.this.removeAllViews();
                    InstreamVideoAdView.this.h.setLayoutParams(new LayoutParams(-1, -1));
                    InstreamVideoAdView instreamVideoAdView = InstreamVideoAdView.this;
                    instreamVideoAdView.addView(instreamVideoAdView.h);
                    if (com.facebook.ads.internal.r.a.b(InstreamVideoAdView.this.a)) {
                        InstreamVideoAdView.this.j = new c();
                        InstreamVideoAdView.this.j.a(InstreamVideoAdView.this.b);
                        InstreamVideoAdView.this.j.b(InstreamVideoAdView.this.a.getPackageName());
                        if (InstreamVideoAdView.this.d.b() != null) {
                            InstreamVideoAdView.this.j.a(InstreamVideoAdView.this.d.b().a());
                        }
                        InstreamVideoAdView.this.h.getOverlay().add(InstreamVideoAdView.this.j);
                        InstreamVideoAdView.this.h.setOnLongClickListener(new OnLongClickListener() {
                            public boolean onLongClick(View view) {
                                if (InstreamVideoAdView.this.h == null || InstreamVideoAdView.this.j == null) {
                                    return false;
                                }
                                InstreamVideoAdView.this.j.setBounds(0, 0, InstreamVideoAdView.this.h.getWidth(), InstreamVideoAdView.this.h.getHeight());
                                InstreamVideoAdView.this.j.a(!InstreamVideoAdView.this.j.a());
                                return true;
                            }
                        });
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("Cannot present null view");
            }

            public void a(AdAdapter adAdapter) {
                if (InstreamVideoAdView.this.d != null) {
                    InstreamVideoAdView.this.f = true;
                    if (InstreamVideoAdView.this.g != null) {
                        InstreamVideoAdView.this.g.onAdLoaded(InstreamVideoAdView.this);
                    }
                }
            }

            public void a(com.facebook.ads.internal.protocol.a aVar) {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onError(InstreamVideoAdView.this, AdError.getAdErrorFromWrapper(aVar));
                }
            }

            public void b() {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onLoggingImpression(InstreamVideoAdView.this);
                }
            }

            public void c() {
                if (InstreamVideoAdView.this.g != null) {
                    InstreamVideoAdView.this.g.onAdVideoComplete(InstreamVideoAdView.this);
                }
            }
        });
        return this.d;
    }

    public void destroy() {
        if (this.j != null && com.facebook.ads.internal.r.a.b(this.a)) {
            this.j.b();
            View view = this.h;
            if (view != null) {
                view.getOverlay().remove(this.j);
            }
        }
        d dVar = this.d;
        if (dVar != null) {
            dVar.a(true);
            this.d = null;
            this.d = getController();
            this.e = null;
            this.f = false;
            removeAllViews();
        }
    }

    public String getPlacementId() {
        return this.b;
    }

    public Bundle getSaveInstanceState() {
        n nVar = this.e;
        if (nVar == null) {
            nVar = (n) this.d.f;
        }
        if (nVar == null) {
            return null;
        }
        Bundle g2 = nVar.g();
        if (g2 == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("adapter", g2);
        bundle.putString("placementID", this.b);
        bundle.putSerializable("adSize", this.c);
        return bundle;
    }

    public boolean isAdInvalidated() {
        d dVar = this.d;
        return dVar == null || dVar.g();
    }

    public boolean isAdLoaded() {
        return this.f;
    }

    public void loadAd() {
        a((String) null);
    }

    public void loadAdFromBid(String str) {
        a(str);
    }

    public void setAdListener(InstreamVideoAdListener instreamVideoAdListener) {
        this.g = instreamVideoAdListener;
    }

    public boolean show() {
        if (!this.f || (this.d == null && this.e == null)) {
            InstreamVideoAdListener instreamVideoAdListener = this.g;
            if (instreamVideoAdListener != null) {
                instreamVideoAdListener.onError(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            }
            return false;
        }
        f fVar = this.e;
        if (fVar != null) {
            fVar.e();
        } else {
            this.d.e();
        }
        this.f = false;
        return true;
    }
}
