package com.google.android.gms.ads.search;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzyz;

@zzark
public final class SearchAdView extends ViewGroup {
    private final zzyz zzvw;

    public SearchAdView(Context context) {
        super(context);
        this.zzvw = new zzyz(this);
    }

    public SearchAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzvw = new zzyz(this, attributeSet, false);
    }

    public SearchAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzvw = new zzyz(this, attributeSet, false);
    }

    public final void destroy() {
        this.zzvw.destroy();
    }

    public final AdListener getAdListener() {
        return this.zzvw.getAdListener();
    }

    public final AdSize getAdSize() {
        return this.zzvw.getAdSize();
    }

    public final String getAdUnitId() {
        return this.zzvw.getAdUnitId();
    }

    @RequiresPermission
    public final void loadAd(SearchAdRequest searchAdRequest) {
        this.zzvw.zza(searchAdRequest.zzaz());
    }

    @RequiresPermission
    public final void loadAd(DynamicHeightSearchAdRequest dynamicHeightSearchAdRequest) {
        if (AdSize.SEARCH.equals(getAdSize())) {
            this.zzvw.zza(dynamicHeightSearchAdRequest.zzaz());
            return;
        }
        throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
    }

    public final void pause() {
        this.zzvw.pause();
    }

    public final void resume() {
        this.zzvw.resume();
    }

    public final void setAdListener(AdListener adListener) {
        this.zzvw.setAdListener(adListener);
    }

    public final void setAdSize(AdSize adSize) {
        this.zzvw.setAdSizes(adSize);
    }

    public final void setAdUnitId(String str) {
        this.zzvw.setAdUnitId(str);
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            AdSize adSize = null;
            try {
                adSize = getAdSize();
            } catch (NullPointerException e) {
                zzbbd.zzb("Unable to retrieve ad size.", e);
            }
            if (adSize != null) {
                Context context = getContext();
                int widthInPixels = adSize.getWidthInPixels(context);
                i3 = adSize.getHeightInPixels(context);
                i4 = widthInPixels;
            } else {
                i3 = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            i4 = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(i4, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }
}
