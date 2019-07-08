package com.facebook.ads;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.internal.w.b.x;
import java.util.ArrayList;
import java.util.List;

public class NativeAdScrollView extends LinearLayout {
    public static final int DEFAULT_INSET = 20;
    public static final int DEFAULT_MAX_ADS = 10;
    /* access modifiers changed from: private */
    public final Context a;
    /* access modifiers changed from: private */
    public final NativeAdsManager b;
    /* access modifiers changed from: private */
    public final AdViewProvider c;
    /* access modifiers changed from: private */
    public final Type d;
    /* access modifiers changed from: private */
    public final int e;
    private final b f;
    /* access modifiers changed from: private */
    public final NativeAdViewAttributes g;

    public interface AdViewProvider {
        View createView(NativeAd nativeAd, int i);

        void destroyView(NativeAd nativeAd, View view);
    }

    private class a extends PagerAdapter {
        private List<NativeAd> b = new ArrayList();

        public a() {
        }

        public void a() {
            this.b.clear();
            int min = Math.min(NativeAdScrollView.this.e, NativeAdScrollView.this.b.getUniqueNativeAdCount());
            for (int i = 0; i < min; i++) {
                NativeAd nextNativeAd = NativeAdScrollView.this.b.nextNativeAd();
                nextNativeAd.a(true);
                this.b.add(nextNativeAd);
            }
            notifyDataSetChanged();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (i < this.b.size()) {
                if (NativeAdScrollView.this.d != null) {
                    ((NativeAd) this.b.get(i)).unregisterView();
                } else {
                    NativeAdScrollView.this.c.destroyView((NativeAd) this.b.get(i), (View) obj);
                }
            }
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return this.b.size();
        }

        public int getItemPosition(Object obj) {
            int indexOf = this.b.indexOf(obj);
            if (indexOf >= 0) {
                return indexOf;
            }
            return -2;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View render = NativeAdScrollView.this.d != null ? NativeAdView.render(NativeAdScrollView.this.a, (NativeAd) this.b.get(i), NativeAdScrollView.this.d, NativeAdScrollView.this.g) : NativeAdScrollView.this.c.createView((NativeAd) this.b.get(i), i);
            viewGroup.addView(render);
            return render;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    private class b extends ViewPager {
        public b(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            int i3 = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredHeight > i3) {
                    i3 = measuredHeight;
                }
            }
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(i3, 1073741824));
        }
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider) {
        this(context, nativeAdsManager, adViewProvider, null, null, 10);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider, int i) {
        this(context, nativeAdsManager, adViewProvider, null, null, i);
    }

    private NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider, Type type, NativeAdViewAttributes nativeAdViewAttributes, int i) {
        super(context);
        if (!nativeAdsManager.isLoaded()) {
            throw new IllegalStateException("NativeAdsManager not loaded");
        } else if (type == null && adViewProvider == null) {
            throw new IllegalArgumentException("Must provide a NativeAdView.Type or AdViewProvider");
        } else {
            this.a = context;
            this.b = nativeAdsManager;
            this.g = nativeAdViewAttributes;
            this.c = adViewProvider;
            this.d = type;
            this.e = i;
            a aVar = new a();
            this.f = new b(context);
            this.f.setAdapter(aVar);
            setInset(20);
            aVar.a();
            addView(this.f);
        }
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type) {
        this(context, nativeAdsManager, null, type, new NativeAdViewAttributes(), 10);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        this(context, nativeAdsManager, null, type, nativeAdViewAttributes, 10);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type, NativeAdViewAttributes nativeAdViewAttributes, int i) {
        this(context, nativeAdsManager, null, type, nativeAdViewAttributes, i);
    }

    public void setInset(int i) {
        if (i > 0) {
            float f2 = x.b;
            int round = Math.round(((float) i) * f2);
            this.f.setPadding(round, 0, round, 0);
            this.f.setPageMargin(Math.round(((float) (i / 2)) * f2));
            this.f.setClipToPadding(false);
        }
    }
}
