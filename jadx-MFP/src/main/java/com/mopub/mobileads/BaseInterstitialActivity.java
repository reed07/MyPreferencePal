package com.mopub.mobileads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.CloseableLayout.OnCloseListener;
import com.mopub.common.DataKeys;

abstract class BaseInterstitialActivity extends Activity {
    @Nullable
    protected AdReport mAdReport;
    @Nullable
    private Long mBroadcastIdentifier;
    @Nullable
    private CloseableLayout mCloseableLayout;

    public abstract View getAdView();

    BaseInterstitialActivity() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.mBroadcastIdentifier = getBroadcastIdentifierFromIntent(intent);
        this.mAdReport = getAdReportFromIntent(intent);
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        View adView = getAdView();
        this.mCloseableLayout = new CloseableLayout(this);
        this.mCloseableLayout.setOnCloseListener(new OnCloseListener() {
            public void onClose() {
                BaseInterstitialActivity.this.finish();
            }
        });
        this.mCloseableLayout.addView(adView, new LayoutParams(-1, -1));
        setContentView(this.mCloseableLayout);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        CloseableLayout closeableLayout = this.mCloseableLayout;
        if (closeableLayout != null) {
            closeableLayout.removeAllViews();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public CloseableLayout getCloseableLayout() {
        return this.mCloseableLayout;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Long getBroadcastIdentifier() {
        return this.mBroadcastIdentifier;
    }

    /* access modifiers changed from: protected */
    public void showInterstitialCloseButton() {
        CloseableLayout closeableLayout = this.mCloseableLayout;
        if (closeableLayout != null) {
            closeableLayout.setCloseVisible(true);
        }
    }

    /* access modifiers changed from: protected */
    public void hideInterstitialCloseButton() {
        CloseableLayout closeableLayout = this.mCloseableLayout;
        if (closeableLayout != null) {
            closeableLayout.setCloseVisible(false);
        }
    }

    protected static Long getBroadcastIdentifierFromIntent(Intent intent) {
        if (intent.hasExtra(DataKeys.BROADCAST_IDENTIFIER_KEY)) {
            return Long.valueOf(intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1));
        }
        return null;
    }

    @Nullable
    protected static AdReport getAdReportFromIntent(Intent intent) {
        try {
            return (AdReport) intent.getSerializableExtra(DataKeys.AD_REPORT_KEY);
        } catch (ClassCastException unused) {
            return null;
        }
    }
}
