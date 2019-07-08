package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.t.d;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.e.c;
import com.facebook.ads.internal.t.g;
import com.facebook.ads.internal.t.h;
import com.facebook.ads.internal.t.i;
import com.facebook.ads.internal.w.b.j;
import org.json.JSONObject;

public abstract class NativeAdBase implements Ad {
    private final e a;

    public static class Image {
        private final g a;

        Image(g gVar) {
            this.a = gVar;
        }

        public Image(String str, int i, int i2) {
            this.a = new g(str, i, i2);
        }

        @Nullable
        public static Image fromJSONObject(JSONObject jSONObject) {
            g a2 = g.a(jSONObject);
            if (a2 == null) {
                return null;
            }
            return new Image(a2);
        }

        public int getHeight() {
            return this.a.c();
        }

        public int getWidth() {
            return this.a.b();
        }
    }

    public enum MediaCacheFlag {
        NONE(d.NONE),
        ALL(d.ALL);
        
        private final d a;

        private MediaCacheFlag(d dVar) {
            this.a = dVar;
        }

        /* access modifiers changed from: 0000 */
        public d a() {
            return this.a;
        }

        public long getCacheFlagValue() {
            return this.a.a();
        }
    }

    public enum NativeComponentTag {
        AD_ICON(j.INTERNAL_AD_ICON),
        AD_TITLE(j.INTERNAL_AD_TITLE),
        AD_COVER_IMAGE(j.INTERNAL_AD_COVER_IMAGE),
        AD_SUBTITLE(j.INTERNAL_AD_SUBTITLE),
        AD_BODY(j.INTERNAL_AD_BODY),
        AD_CALL_TO_ACTION(j.INTERNAL_AD_CALL_TO_ACTION),
        AD_SOCIAL_CONTEXT(j.INTERNAL_AD_SOCIAL_CONTEXT),
        AD_CHOICES_ICON(j.INTERNAL_AD_CHOICES_ICON),
        AD_OPTIONS_VIEW(j.INTERNAL_AD_OPTIONS_VIEW),
        AD_MEDIA(j.INTERNAL_AD_MEDIA);
        
        private final j a;

        private NativeComponentTag(j jVar) {
            this.a = jVar;
        }

        public static void tagView(View view, NativeComponentTag nativeComponentTag) {
            if (view != null && nativeComponentTag != null) {
                j.a(view, nativeComponentTag.a);
            }
        }
    }

    public static class Rating {
        private final i a;

        public Rating(double d, double d2) {
            this.a = new i(d, d2);
        }

        Rating(i iVar) {
            this.a = iVar;
        }

        @Nullable
        public static Rating fromJSONObject(JSONObject jSONObject) {
            i a2 = i.a(jSONObject);
            if (a2 == null) {
                return null;
            }
            return new Rating(a2);
        }

        public double getScale() {
            return this.a.b();
        }

        public double getValue() {
            return this.a.a();
        }
    }

    public NativeAdBase(Context context, com.facebook.ads.internal.adapters.i iVar, com.facebook.ads.internal.m.d dVar) {
        this.a = new e(context, iVar, dVar, getViewTraversalPredicate());
    }

    public NativeAdBase(Context context, String str) {
        this.a = new e(context, str, getViewTraversalPredicate());
    }

    NativeAdBase(NativeAdBase nativeAdBase) {
        this.a = new e(nativeAdBase.a);
    }

    NativeAdBase(e eVar) {
        this.a = eVar;
    }

    public static c getViewTraversalPredicate() {
        return new c() {
            public boolean a(View view) {
                return (view instanceof MediaViewVideoRenderer) || (view instanceof AdChoicesView) || (view instanceof AdOptionsView) || (view instanceof com.facebook.ads.internal.view.j);
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public void a(MediaView mediaView) {
        if (mediaView != null) {
            this.a.c(true);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(com.facebook.ads.internal.protocol.e eVar) {
        this.a.a(eVar);
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        this.a.a(z);
    }

    /* access modifiers changed from: 0000 */
    public void b(MediaView mediaView) {
        if (mediaView != null) {
            this.a.d(true);
        }
    }

    public void destroy() {
        this.a.d();
    }

    public void downloadMedia() {
        this.a.c();
    }

    /* access modifiers changed from: 0000 */
    public e f() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public com.facebook.ads.internal.adapters.i g() {
        return this.a.a();
    }

    public String getAdBodyText() {
        return this.a.l();
    }

    @Nullable
    public String getAdCallToAction() {
        return this.a.a("call_to_action");
    }

    public Image getAdChoicesIcon() {
        if (this.a.o() == null) {
            return null;
        }
        return new Image(this.a.o());
    }

    @Nullable
    public String getAdChoicesImageUrl() {
        if (this.a.o() == null) {
            return null;
        }
        return this.a.o().a();
    }

    public String getAdChoicesLinkUrl() {
        return this.a.p();
    }

    public String getAdChoicesText() {
        return this.a.q();
    }

    public Image getAdCoverImage() {
        if (this.a.j() == null) {
            return null;
        }
        return new Image(this.a.j());
    }

    @Nullable
    public String getAdHeadline() {
        return this.a.a("headline");
    }

    public Image getAdIcon() {
        if (this.a.i() == null) {
            return null;
        }
        return new Image(this.a.i());
    }

    @Nullable
    public String getAdLinkDescription() {
        return this.a.a("link_description");
    }

    @Nullable
    public String getAdSocialContext() {
        return this.a.a("social_context");
    }

    @Deprecated
    public Rating getAdStarRating() {
        if (this.a.m() == null) {
            return null;
        }
        return new Rating(this.a.m());
    }

    @Nullable
    public String getAdTranslation() {
        return this.a.a("ad_translation");
    }

    @Nullable
    public String getAdUntrimmedBodyText() {
        return this.a.a("body");
    }

    public NativeAdViewAttributes getAdViewAttributes() {
        if (this.a.k() == null) {
            return null;
        }
        return new NativeAdViewAttributes(this.a.k());
    }

    @Nullable
    public String getAdvertiserName() {
        return this.a.a("advertiser_name");
    }

    public String getId() {
        return this.a.n();
    }

    public String getPlacementId() {
        return this.a.e();
    }

    @Nullable
    public String getPromotedTranslation() {
        return this.a.a("promoted_translation");
    }

    @Nullable
    public String getSponsoredTranslation() {
        return this.a.a("sponsored_translation");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String h() {
        return this.a.v();
    }

    public boolean hasCallToAction() {
        return this.a.h();
    }

    public boolean isAdInvalidated() {
        return this.a.b();
    }

    public boolean isAdLoaded() {
        return this.a.f();
    }

    public boolean isNativeConfigEnabled() {
        return this.a.g();
    }

    public void loadAd() {
        loadAd(MediaCacheFlag.ALL);
    }

    public void loadAd(MediaCacheFlag mediaCacheFlag) {
        this.a.a(mediaCacheFlag.a(), (String) null);
    }

    public void loadAdFromBid(String str) {
        loadAdFromBid(str, MediaCacheFlag.ALL);
    }

    public void loadAdFromBid(String str, MediaCacheFlag mediaCacheFlag) {
        this.a.a(mediaCacheFlag.a(), str);
    }

    public void onCtaBroadcast() {
        this.a.w();
    }

    public void setAdListener(final NativeAdListener nativeAdListener) {
        if (nativeAdListener != null) {
            this.a.a((h) new h() {
                public void a() {
                    nativeAdListener.onMediaDownloaded(NativeAdBase.this);
                }

                public void a(a aVar) {
                    nativeAdListener.onError(NativeAdBase.this, AdError.getAdErrorFromWrapper(aVar));
                }

                public void b() {
                    nativeAdListener.onAdLoaded(NativeAdBase.this);
                }

                public void c() {
                    nativeAdListener.onAdClicked(NativeAdBase.this);
                }

                public void d() {
                    nativeAdListener.onLoggingImpression(NativeAdBase.this);
                }
            });
        }
    }

    public void setExtraHints(ExtraHints extraHints) {
        if (extraHints != null) {
            this.a.b(extraHints.getHints());
        }
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.a.a(onTouchListener);
    }

    public void unregisterView() {
        this.a.z();
    }
}
