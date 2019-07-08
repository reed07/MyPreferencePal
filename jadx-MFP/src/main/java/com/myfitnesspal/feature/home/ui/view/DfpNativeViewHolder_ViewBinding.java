package com.myfitnesspal.feature.home.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.myfitnesspal.android.R;

public class DfpNativeViewHolder_ViewBinding implements Unbinder {
    private DfpNativeViewHolder target;

    @UiThread
    public DfpNativeViewHolder_ViewBinding(DfpNativeViewHolder dfpNativeViewHolder, View view) {
        this.target = dfpNativeViewHolder;
        dfpNativeViewHolder.adContainer = (CardView) Utils.findRequiredViewAsType(view, R.id.native_ad_view_container, "field 'adContainer'", CardView.class);
        dfpNativeViewHolder.unifiedAdView = (UnifiedNativeAdView) Utils.findRequiredViewAsType(view, R.id.native_unified_ad_view, "field 'unifiedAdView'", UnifiedNativeAdView.class);
        dfpNativeViewHolder.customAdView = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.native_custom_ad_view, "field 'customAdView'", RelativeLayout.class);
    }

    @CallSuper
    public void unbind() {
        DfpNativeViewHolder dfpNativeViewHolder = this.target;
        if (dfpNativeViewHolder != null) {
            this.target = null;
            dfpNativeViewHolder.adContainer = null;
            dfpNativeViewHolder.unifiedAdView = null;
            dfpNativeViewHolder.customAdView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
