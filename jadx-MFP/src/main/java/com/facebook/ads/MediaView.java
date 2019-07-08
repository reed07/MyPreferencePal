package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.a.a.C0001a;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.t.f;
import com.facebook.ads.internal.t.g;
import com.facebook.ads.internal.view.c.b;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.j;
import com.facebook.ads.internal.view.q;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.h.a;
import java.util.Iterator;

public class MediaView extends f {
    private static final String a = "MediaView";
    private j b;
    private ImageView c;
    private b d;
    private RecyclerView e;
    /* access modifiers changed from: private */
    public MediaViewVideoRenderer f;
    private View g;
    @Nullable
    private MediaViewListener h;
    private boolean i;
    private boolean j;
    private boolean k;

    public MediaView(Context context) {
        super(context);
        setIconView(new ImageView(context));
        setImageRenderer(new b(context));
        this.b = new j(context);
        b();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context));
        a();
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setIconView(new ImageView(context, attributeSet));
        setImageRenderer(new b(context, attributeSet));
        this.b = new j(context, attributeSet);
        b();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet));
        a();
    }

    public MediaView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setIconView(new ImageView(context, attributeSet, i2));
        setImageRenderer(new b(context, attributeSet, i2));
        this.b = new j(context, attributeSet, i2);
        b();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet, i2));
        a();
    }

    @TargetApi(21)
    public MediaView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        setIconView(new ImageView(context, attributeSet, i2, i3));
        setImageRenderer(new b(context, attributeSet, i2, i3));
        this.b = new j(context, attributeSet, i2);
        b();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet, i2, i3));
        a();
    }

    private void a() {
        com.facebook.ads.internal.w.b.j.a(this, com.facebook.ads.internal.w.b.j.INTERNAL_AD_MEDIA);
        com.facebook.ads.internal.w.b.j.a(this.d, com.facebook.ads.internal.w.b.j.INTERNAL_AD_MEDIA);
        com.facebook.ads.internal.w.b.j.a(this.f, com.facebook.ads.internal.w.b.j.INTERNAL_AD_MEDIA);
        com.facebook.ads.internal.w.b.j.a(this.e, com.facebook.ads.internal.w.b.j.INTERNAL_AD_MEDIA);
        this.j = true;
    }

    private void b() {
        if (!this.i) {
            if (this.e != null) {
                x.b(this.b);
            }
            float f2 = x.b;
            int round = Math.round(4.0f * f2);
            int round2 = Math.round(f2 * 12.0f);
            this.b.setChildSpacing(round);
            this.b.setPadding(0, round2, 0, round2);
            this.b.setVisibility(8);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(13);
            addView((View) this.b, (ViewGroup.LayoutParams) layoutParams);
            return;
        }
        throw new IllegalStateException("Carousel renderer must be set before nativeAd.");
    }

    private void setIconView(ImageView imageView) {
        if (!this.i) {
            ImageView imageView2 = this.c;
            if (imageView2 != null) {
                x.b(imageView2);
            }
            imageView.setVisibility(8);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(13);
            addView((View) imageView, (ViewGroup.LayoutParams) layoutParams);
            this.c = imageView;
            return;
        }
        throw new IllegalStateException("Image renderer must be set before nativeBannerAd.");
    }

    private void setImageRenderer(b bVar) {
        if (!this.i) {
            b bVar2 = this.d;
            if (bVar2 != null) {
                removeView(bVar2);
            }
            bVar.setVisibility(8);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(13);
            addView((View) bVar, (ViewGroup.LayoutParams) layoutParams);
            this.d = bVar;
            return;
        }
        throw new IllegalStateException("Image renderer must be set before nativeAd.");
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        this.j = false;
        addView(view, layoutParams);
        this.j = true;
    }

    /* access modifiers changed from: 0000 */
    public void a(final NativeAdBase nativeAdBase, boolean z) {
        this.i = true;
        nativeAdBase.b(this);
        this.d.setVisibility(8);
        this.d.a(null, null);
        this.f.setVisibility(8);
        this.f.a();
        RecyclerView recyclerView = this.e;
        if (recyclerView != null) {
            recyclerView.setVisibility(8);
            this.e.setAdapter(null);
        }
        this.c.setVisibility(0);
        bringChildToFront(this.c);
        ImageView imageView = this.c;
        this.g = imageView;
        d a2 = new d(imageView).a();
        if (z) {
            a2.a((e) new e() {
                public void a(boolean z) {
                    nativeAdBase.f().a(z, true);
                }
            });
        }
        g i2 = nativeAdBase.f().i();
        if (i2 != null) {
            a2.a(i2.a());
            return;
        }
        if (z) {
            nativeAdBase.f().a(false, true);
        }
        int i3 = com.facebook.ads.internal.w.h.b.h;
        StringBuilder sb = new StringBuilder();
        sb.append("Native Ad Icon is null. Loaded: ");
        sb.append(nativeAdBase.f().f());
        a.b(getContext(), "api", i3, new Exception(sb.toString()));
    }

    public void addView(View view) {
        if (!this.j) {
            super.addView(view);
        }
    }

    public void addView(View view, int i2) {
        if (!this.j) {
            super.addView(view, i2);
        }
    }

    public void addView(View view, int i2, int i3) {
        if (!this.j) {
            super.addView(view, i2, i3);
        }
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!this.j) {
            super.addView(view, i2, layoutParams);
        }
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (!this.j) {
            super.addView(view, layoutParams);
        }
    }

    public void bringChildToFront(View view) {
        if (view == this.e || view == this.f || view == this.d || view == this.c) {
            super.bringChildToFront(view);
        }
    }

    public void destroy() {
        this.f.pause(false);
        this.f.destroy();
    }

    /* access modifiers changed from: protected */
    public View getAdContentsView() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public c getAdEventManager() {
        return com.facebook.ads.internal.s.d.a(getContext());
    }

    public void setListener(final MediaViewListener mediaViewListener) {
        this.h = mediaViewListener;
        if (mediaViewListener == null) {
            this.f.setListener(null);
        } else {
            this.f.setListener(new q() {
                public void a() {
                    MediaViewListener mediaViewListener = mediaViewListener;
                    MediaView mediaView = MediaView.this;
                    mediaViewListener.onVolumeChange(mediaView, mediaView.f.getVolume());
                }

                public void b() {
                    mediaViewListener.onPause(MediaView.this);
                }

                public void c() {
                    mediaViewListener.onPlay(MediaView.this);
                }

                public void d() {
                    mediaViewListener.onFullscreenBackground(MediaView.this);
                }

                public void e() {
                    mediaViewListener.onFullscreenForeground(MediaView.this);
                }

                public void f() {
                    mediaViewListener.onExitFullscreen(MediaView.this);
                }

                public void g() {
                    mediaViewListener.onEnterFullscreen(MediaView.this);
                }

                public void h() {
                    mediaViewListener.onComplete(MediaView.this);
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void setNativeAd(final NativeAd nativeAd) {
        boolean z;
        boolean z2 = true;
        this.i = true;
        nativeAd.a(this);
        this.c.setVisibility(8);
        this.c.setImageDrawable(null);
        if (nativeAd.d() != null) {
            Iterator it = nativeAd.d().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((NativeAd) it.next()).getAdCoverImage() == null) {
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            boolean q = com.facebook.ads.internal.r.a.q(getContext());
            this.e = this.b;
            ((j) this.e).setCurrentPosition(0);
            com.facebook.ads.internal.adapters.a.a bVar = q ? new com.facebook.ads.internal.adapters.a.b((j) this.e, nativeAd.f().u()) : new com.facebook.ads.internal.adapters.a.c((j) this.e, nativeAd.f().u());
            bVar.a((C0001a) new C0001a() {
                public void a() {
                    nativeAd.f().a(true, true);
                }
            });
            this.e.setAdapter(bVar);
            this.g = this.e;
            this.d.setVisibility(8);
            this.d.a(null, null);
            this.f.setVisibility(8);
            this.f.a();
            bringChildToFront(this.e);
            this.e.setVisibility(0);
            return;
        }
        if (VERSION.SDK_INT < 14 || TextUtils.isEmpty(nativeAd.a())) {
            z2 = false;
        }
        if (z2) {
            nativeAd.f().b(this.k);
            this.g = this.f.getVideoView();
            this.d.setVisibility(8);
            this.d.a(null, null);
            RecyclerView recyclerView = this.e;
            if (recyclerView != null) {
                recyclerView.setVisibility(8);
                this.e.setAdapter(null);
            }
            bringChildToFront(this.f);
            this.f.setNativeAd(nativeAd);
            this.f.setVisibility(0);
        } else if (nativeAd.getAdCoverImage() != null) {
            this.g = this.d.getBodyImageView();
            this.f.setVisibility(8);
            this.f.a();
            RecyclerView recyclerView2 = this.e;
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(8);
                this.e.setAdapter(null);
            }
            bringChildToFront(this.d);
            this.d.setVisibility(0);
            new d(this.d).a(getHeight(), getWidth()).a(com.facebook.ads.internal.r.a.m(getContext())).a((e) new e() {
                public void a(boolean z) {
                    nativeAd.f().a(z, true);
                }
            }).a(nativeAd.f().j().a());
        }
    }

    public void setVideoRenderer(MediaViewVideoRenderer mediaViewVideoRenderer) {
        if (!this.i) {
            MediaViewVideoRenderer mediaViewVideoRenderer2 = this.f;
            if (mediaViewVideoRenderer2 != null) {
                removeView(mediaViewVideoRenderer2);
                this.f.destroy();
            }
            mediaViewVideoRenderer.setAdEventManager(getAdEventManager());
            mediaViewVideoRenderer.setVisibility(8);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(13);
            a((View) mediaViewVideoRenderer, (ViewGroup.LayoutParams) layoutParams);
            this.f = mediaViewVideoRenderer;
            this.k = !(this.f instanceof DefaultMediaViewVideoRenderer);
            return;
        }
        throw new IllegalStateException("Video renderer must be set before nativeAd.");
    }
}
