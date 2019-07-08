package com.mopub.mobileads;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.mopub.common.AdFormat;
import com.mopub.common.AdReport;
import com.mopub.common.MoPub;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ManifestUtils;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Reflection.MethodBuilder;
import com.mopub.common.util.Visibility;
import com.mopub.mobileads.factories.AdViewControllerFactory;
import java.util.Map;
import java.util.TreeMap;

public class MoPubView extends FrameLayout {
    @Nullable
    protected AdViewController mAdViewController;
    private BannerAdListener mBannerAdListener;
    private Context mContext;
    protected Object mCustomEventBannerAdapter;
    private BroadcastReceiver mScreenStateReceiver;
    /* access modifiers changed from: private */
    public int mScreenVisibility;

    public interface BannerAdListener {
        void onBannerClicked(MoPubView moPubView);

        void onBannerCollapsed(MoPubView moPubView);

        void onBannerExpanded(MoPubView moPubView);

        void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode);

        void onBannerLoaded(MoPubView moPubView);
    }

    @Deprecated
    public String getClickTrackingUrl() {
        return null;
    }

    @Deprecated
    public String getResponseString() {
        return null;
    }

    @Deprecated
    public void setTimeout(int i) {
    }

    public MoPubView(Context context) {
        this(context, null);
    }

    public MoPubView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ManifestUtils.checkWebViewActivitiesDeclared(context);
        this.mContext = context;
        this.mScreenVisibility = getVisibility();
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        this.mAdViewController = AdViewControllerFactory.create(context, this);
        registerScreenStateBroadcastReceiver();
    }

    private void registerScreenStateBroadcastReceiver() {
        this.mScreenStateReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (Visibility.isScreenVisible(MoPubView.this.mScreenVisibility) && intent != null) {
                    String action = intent.getAction();
                    if ("android.intent.action.USER_PRESENT".equals(action)) {
                        MoPubView.this.setAdVisibility(0);
                    } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                        MoPubView.this.setAdVisibility(8);
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.mContext.registerReceiver(this.mScreenStateReceiver, intentFilter);
    }

    private void unregisterScreenStateBroadcastReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mScreenStateReceiver);
        } catch (Exception unused) {
            MoPubLog.d("Failed to unregister screen state broadcast receiver (never registered).");
        }
    }

    public void loadAd() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.loadAd();
        }
    }

    public void destroy() {
        unregisterScreenStateBroadcastReceiver();
        removeAllViews();
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.cleanup();
            this.mAdViewController = null;
        }
        if (this.mCustomEventBannerAdapter != null) {
            invalidateAdapter();
            this.mCustomEventBannerAdapter = null;
        }
    }

    private void invalidateAdapter() {
        Object obj = this.mCustomEventBannerAdapter;
        if (obj != null) {
            try {
                new MethodBuilder(obj, "invalidate").setAccessible().execute();
            } catch (Exception e) {
                MoPubLog.e("Error invalidating adapter", e);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Integer getAdTimeoutDelay(int i) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController == null) {
            return Integer.valueOf(i);
        }
        return adViewController.getAdTimeoutDelay(i);
    }

    /* access modifiers changed from: protected */
    public boolean loadFailUrl(@NonNull MoPubErrorCode moPubErrorCode) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController == null) {
            return false;
        }
        return adViewController.loadFailUrl(moPubErrorCode);
    }

    /* access modifiers changed from: protected */
    public void loadCustomEvent(String str, Map<String, String> map) {
        if (this.mAdViewController != null) {
            if (TextUtils.isEmpty(str)) {
                MoPubLog.d("Couldn't invoke custom event because the server did not specify one.");
                loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                return;
            }
            if (this.mCustomEventBannerAdapter != null) {
                invalidateAdapter();
            }
            MoPubLog.d("Loading custom event adapter.");
            if (Reflection.classFound("com.mopub.mobileads.factories.CustomEventBannerAdapterFactory")) {
                try {
                    this.mCustomEventBannerAdapter = new MethodBuilder(null, "create").setStatic(Class.forName("com.mopub.mobileads.factories.CustomEventBannerAdapterFactory")).addParam(MoPubView.class, this).addParam(String.class, str).addParam(Map.class, map).addParam(Long.TYPE, Long.valueOf(this.mAdViewController.getBroadcastIdentifier())).addParam(AdReport.class, this.mAdViewController.getAdReport()).execute();
                    new MethodBuilder(this.mCustomEventBannerAdapter, "loadAd").setAccessible().execute();
                } catch (Exception e) {
                    MoPubLog.e("Error loading custom event", e);
                }
            } else {
                MoPubLog.e("Could not load custom event -- missing banner module");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void registerClick() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.registerClick();
            adClicked();
        }
    }

    /* access modifiers changed from: protected */
    public void trackNativeImpression() {
        MoPubLog.d("Tracking impression for native adapter.");
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.trackImpression();
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        if (Visibility.hasScreenVisibilityChanged(this.mScreenVisibility, i)) {
            this.mScreenVisibility = i;
            setAdVisibility(this.mScreenVisibility);
        }
    }

    /* access modifiers changed from: private */
    public void setAdVisibility(int i) {
        if (this.mAdViewController != null) {
            if (Visibility.isScreenVisible(i)) {
                this.mAdViewController.resumeRefresh();
            } else {
                this.mAdViewController.pauseRefresh();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void adLoaded() {
        MoPubLog.d("adLoaded");
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerLoaded(this);
        }
    }

    /* access modifiers changed from: protected */
    public void adFailed(MoPubErrorCode moPubErrorCode) {
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerFailed(this, moPubErrorCode);
        }
    }

    /* access modifiers changed from: protected */
    public void adPresentedOverlay() {
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerExpanded(this);
        }
    }

    /* access modifiers changed from: protected */
    public void adClosed() {
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerCollapsed(this);
        }
    }

    /* access modifiers changed from: protected */
    public void adClicked() {
        BannerAdListener bannerAdListener = this.mBannerAdListener;
        if (bannerAdListener != null) {
            bannerAdListener.onBannerClicked(this);
        }
    }

    /* access modifiers changed from: protected */
    public void creativeDownloaded() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.creativeDownloadSuccess();
        }
        adLoaded();
    }

    public void setAdUnitId(String str) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setAdUnitId(str);
        }
    }

    public String getAdUnitId() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getAdUnitId();
        }
        return null;
    }

    public void setKeywords(String str) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setKeywords(str);
        }
    }

    public String getKeywords() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getKeywords();
        }
        return null;
    }

    public void setUserDataKeywords(String str) {
        if (this.mAdViewController != null && MoPub.canCollectPersonalInformation()) {
            this.mAdViewController.setUserDataKeywords(str);
        }
    }

    public String getUserDataKeywords() {
        if (this.mAdViewController == null || !MoPub.canCollectPersonalInformation()) {
            return null;
        }
        return this.mAdViewController.getUserDataKeywords();
    }

    public void setLocation(Location location) {
        if (this.mAdViewController != null && MoPub.canCollectPersonalInformation()) {
            this.mAdViewController.setLocation(location);
        }
    }

    public Location getLocation() {
        if (this.mAdViewController == null || !MoPub.canCollectPersonalInformation()) {
            return null;
        }
        return this.mAdViewController.getLocation();
    }

    public int getAdWidth() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getAdWidth();
        }
        return 0;
    }

    public int getAdHeight() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getAdHeight();
        }
        return 0;
    }

    public Activity getActivity() {
        return (Activity) this.mContext;
    }

    public void setBannerAdListener(BannerAdListener bannerAdListener) {
        this.mBannerAdListener = bannerAdListener;
    }

    public BannerAdListener getBannerAdListener() {
        return this.mBannerAdListener;
    }

    public void setLocalExtras(Map<String, Object> map) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setLocalExtras(map);
        }
    }

    public Map<String, Object> getLocalExtras() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getLocalExtras();
        }
        return new TreeMap();
    }

    public void setAutorefreshEnabled(boolean z) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setShouldAllowAutoRefresh(z);
        }
    }

    /* access modifiers changed from: 0000 */
    public void pauseAutorefresh() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.pauseRefresh();
        }
    }

    /* access modifiers changed from: 0000 */
    public void resumeAutorefresh() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.resumeRefresh();
        }
    }

    /* access modifiers changed from: 0000 */
    public void expand() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.expand();
        }
    }

    /* access modifiers changed from: 0000 */
    public void collapse() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.collapse();
        }
    }

    public boolean getAutorefreshEnabled() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getCurrentAutoRefreshStatus();
        }
        MoPubLog.d("Can't get autorefresh status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void setAdContentView(View view) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setAdContentView(view);
        }
    }

    public void setTesting(boolean z) {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.setTesting(z);
        }
    }

    public boolean getTesting() {
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            return adViewController.getTesting();
        }
        MoPubLog.d("Can't get testing status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void forceRefresh() {
        if (this.mCustomEventBannerAdapter != null) {
            invalidateAdapter();
            this.mCustomEventBannerAdapter = null;
        }
        AdViewController adViewController = this.mAdViewController;
        if (adViewController != null) {
            adViewController.forceRefresh();
        }
    }

    /* access modifiers changed from: 0000 */
    public AdViewController getAdViewController() {
        return this.mAdViewController;
    }

    public AdFormat getAdFormat() {
        return AdFormat.BANNER;
    }
}
