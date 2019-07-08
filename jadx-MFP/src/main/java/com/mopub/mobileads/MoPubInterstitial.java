package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.factories.CustomEventInterstitialAdapterFactory;
import java.util.Map;

public class MoPubInterstitial implements CustomEventInterstitialAdapterListener {
    @NonNull
    private Activity mActivity;
    @NonNull
    private final Runnable mAdExpiration;
    /* access modifiers changed from: private */
    @NonNull
    public volatile InterstitialState mCurrentInterstitialState;
    /* access modifiers changed from: private */
    @Nullable
    public CustomEventInterstitialAdapter mCustomEventInterstitialAdapter;
    @NonNull
    private Handler mHandler;
    /* access modifiers changed from: private */
    @Nullable
    public InterstitialAdListener mInterstitialAdListener;
    /* access modifiers changed from: private */
    @NonNull
    public MoPubInterstitialView mInterstitialView = new MoPubInterstitialView(this.mActivity);

    public interface InterstitialAdListener {
        void onInterstitialClicked(MoPubInterstitial moPubInterstitial);

        void onInterstitialDismissed(MoPubInterstitial moPubInterstitial);

        void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode);

        void onInterstitialLoaded(MoPubInterstitial moPubInterstitial);

        void onInterstitialShown(MoPubInterstitial moPubInterstitial);
    }

    @VisibleForTesting
    enum InterstitialState {
        IDLE,
        LOADING,
        READY,
        SHOWING,
        DESTROYED
    }

    public class MoPubInterstitialView extends MoPubView {
        public MoPubInterstitialView(Context context) {
            super(context);
            setAutorefreshEnabled(false);
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public String getCustomEventClassName() {
            return this.mAdViewController.getCustomEventClassName();
        }

        public AdFormat getAdFormat() {
            return AdFormat.INTERSTITIAL;
        }

        /* access modifiers changed from: protected */
        public void loadCustomEvent(String str, Map<String, String> map) {
            if (this.mAdViewController != null) {
                if (TextUtils.isEmpty(str)) {
                    MoPubLog.d("Couldn't invoke custom event because the server did not specify one.");
                    loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                    return;
                }
                if (MoPubInterstitial.this.mCustomEventInterstitialAdapter != null) {
                    MoPubInterstitial.this.mCustomEventInterstitialAdapter.invalidate();
                }
                MoPubLog.d("Loading custom event interstitial adapter.");
                MoPubInterstitial moPubInterstitial = MoPubInterstitial.this;
                moPubInterstitial.mCustomEventInterstitialAdapter = CustomEventInterstitialAdapterFactory.create(moPubInterstitial, str, map, this.mAdViewController.getBroadcastIdentifier(), this.mAdViewController.getAdReport());
                MoPubInterstitial.this.mCustomEventInterstitialAdapter.setAdapterListener(MoPubInterstitial.this);
                MoPubInterstitial.this.mCustomEventInterstitialAdapter.loadInterstitial();
            }
        }

        /* access modifiers changed from: protected */
        public void trackImpression() {
            MoPubLog.d("Tracking impression for interstitial.");
            if (this.mAdViewController != null) {
                this.mAdViewController.trackImpression();
            }
        }

        /* access modifiers changed from: protected */
        public void adFailed(MoPubErrorCode moPubErrorCode) {
            MoPubInterstitial.this.attemptStateTransition(InterstitialState.IDLE);
            if (MoPubInterstitial.this.mInterstitialAdListener != null) {
                MoPubInterstitial.this.mInterstitialAdListener.onInterstitialFailed(MoPubInterstitial.this, moPubErrorCode);
            }
        }
    }

    public MoPubInterstitial(@NonNull Activity activity, @NonNull String str) {
        this.mActivity = activity;
        this.mInterstitialView.setAdUnitId(str);
        this.mCurrentInterstitialState = InterstitialState.IDLE;
        this.mHandler = new Handler();
        this.mAdExpiration = new Runnable() {
            public void run() {
                MoPubLog.d("Expiring unused Interstitial ad.");
                MoPubInterstitial.this.attemptStateTransition(InterstitialState.IDLE, true);
                if (!InterstitialState.SHOWING.equals(MoPubInterstitial.this.mCurrentInterstitialState) && !InterstitialState.DESTROYED.equals(MoPubInterstitial.this.mCurrentInterstitialState)) {
                    MoPubInterstitial.this.mInterstitialView.adFailed(MoPubErrorCode.EXPIRED);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public boolean attemptStateTransition(@NonNull InterstitialState interstitialState) {
        return attemptStateTransition(interstitialState, false);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x00e6, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0104, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0053, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0082, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x00bd, code lost:
        return false;
     */
    @com.mopub.common.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean attemptStateTransition(@android.support.annotation.NonNull com.mopub.mobileads.MoPubInterstitial.InterstitialState r5, boolean r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.mopub.common.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x0105 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r0 = r4.mCurrentInterstitialState     // Catch:{ all -> 0x0105 }
            int[] r1 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0105 }
            int r0 = r0.ordinal()     // Catch:{ all -> 0x0105 }
            r0 = r1[r0]     // Catch:{ all -> 0x0105 }
            r1 = 1
            r2 = 0
            switch(r0) {
                case 1: goto L_0x00be;
                case 2: goto L_0x008a;
                case 3: goto L_0x0083;
                case 4: goto L_0x0054;
                case 5: goto L_0x0015;
                default: goto L_0x0013;
            }
        L_0x0013:
            monitor-exit(r4)
            return r2
        L_0x0015:
            int[] r0 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0105 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x0105 }
            r5 = r0[r5]     // Catch:{ all -> 0x0105 }
            switch(r5) {
                case 1: goto L_0x0044;
                case 2: goto L_0x0034;
                case 3: goto L_0x002f;
                case 4: goto L_0x0022;
                default: goto L_0x0020;
            }
        L_0x0020:
            monitor-exit(r4)
            return r2
        L_0x0022:
            if (r6 == 0) goto L_0x002d
            r4.invalidateInterstitialAdapter()     // Catch:{ all -> 0x0105 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r5 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.IDLE     // Catch:{ all -> 0x0105 }
            r4.mCurrentInterstitialState = r5     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r1
        L_0x002d:
            monitor-exit(r4)
            return r2
        L_0x002f:
            r4.setInterstitialStateDestroyed()     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r1
        L_0x0034:
            r4.showCustomEventInterstitial()     // Catch:{ all -> 0x0105 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r5 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.SHOWING     // Catch:{ all -> 0x0105 }
            r4.mCurrentInterstitialState = r5     // Catch:{ all -> 0x0105 }
            android.os.Handler r5 = r4.mHandler     // Catch:{ all -> 0x0105 }
            java.lang.Runnable r6 = r4.mAdExpiration     // Catch:{ all -> 0x0105 }
            r5.removeCallbacks(r6)     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r1
        L_0x0044:
            java.lang.String r5 = "Interstitial already loaded. Not loading another."
            com.mopub.common.logging.MoPubLog.d(r5)     // Catch:{ all -> 0x0105 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialAdListener r5 = r4.mInterstitialAdListener     // Catch:{ all -> 0x0105 }
            if (r5 == 0) goto L_0x0052
            com.mopub.mobileads.MoPubInterstitial$InterstitialAdListener r5 = r4.mInterstitialAdListener     // Catch:{ all -> 0x0105 }
            r5.onInterstitialLoaded(r4)     // Catch:{ all -> 0x0105 }
        L_0x0052:
            monitor-exit(r4)
            return r2
        L_0x0054:
            int[] r0 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0105 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x0105 }
            r5 = r0[r5]     // Catch:{ all -> 0x0105 }
            switch(r5) {
                case 1: goto L_0x006d;
                case 2: goto L_0x0066;
                case 3: goto L_0x0061;
                default: goto L_0x005f;
            }
        L_0x005f:
            monitor-exit(r4)
            return r2
        L_0x0061:
            r4.setInterstitialStateDestroyed()     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r1
        L_0x0066:
            java.lang.String r5 = "No interstitial loading or loaded."
            com.mopub.common.logging.MoPubLog.d(r5)     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r2
        L_0x006d:
            r4.invalidateInterstitialAdapter()     // Catch:{ all -> 0x0105 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r5 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.LOADING     // Catch:{ all -> 0x0105 }
            r4.mCurrentInterstitialState = r5     // Catch:{ all -> 0x0105 }
            if (r6 == 0) goto L_0x007c
            com.mopub.mobileads.MoPubInterstitial$MoPubInterstitialView r5 = r4.mInterstitialView     // Catch:{ all -> 0x0105 }
            r5.forceRefresh()     // Catch:{ all -> 0x0105 }
            goto L_0x0081
        L_0x007c:
            com.mopub.mobileads.MoPubInterstitial$MoPubInterstitialView r5 = r4.mInterstitialView     // Catch:{ all -> 0x0105 }
            r5.loadAd()     // Catch:{ all -> 0x0105 }
        L_0x0081:
            monitor-exit(r4)
            return r1
        L_0x0083:
            java.lang.String r5 = "MoPubInterstitial destroyed. Ignoring all requests."
            com.mopub.common.logging.MoPubLog.d(r5)     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r2
        L_0x008a:
            int[] r0 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0105 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x0105 }
            r5 = r0[r5]     // Catch:{ all -> 0x0105 }
            switch(r5) {
                case 1: goto L_0x00b5;
                case 2: goto L_0x00ae;
                case 3: goto L_0x00a9;
                case 4: goto L_0x0097;
                default: goto L_0x0095;
            }
        L_0x0095:
            monitor-exit(r4)
            return r2
        L_0x0097:
            if (r6 == 0) goto L_0x00a0
            java.lang.String r5 = "Cannot force refresh while showing an interstitial."
            com.mopub.common.logging.MoPubLog.d(r5)     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r2
        L_0x00a0:
            r4.invalidateInterstitialAdapter()     // Catch:{ all -> 0x0105 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r5 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.IDLE     // Catch:{ all -> 0x0105 }
            r4.mCurrentInterstitialState = r5     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r1
        L_0x00a9:
            r4.setInterstitialStateDestroyed()     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r1
        L_0x00ae:
            java.lang.String r5 = "Already showing an interstitial. Cannot show it again."
            com.mopub.common.logging.MoPubLog.d(r5)     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r2
        L_0x00b5:
            if (r6 != 0) goto L_0x00bc
            java.lang.String r5 = "Interstitial already showing. Not loading another."
            com.mopub.common.logging.MoPubLog.d(r5)     // Catch:{ all -> 0x0105 }
        L_0x00bc:
            monitor-exit(r4)
            return r2
        L_0x00be:
            int[] r0 = com.mopub.mobileads.MoPubInterstitial.AnonymousClass2.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState     // Catch:{ all -> 0x0105 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x0105 }
            r5 = r0[r5]     // Catch:{ all -> 0x0105 }
            switch(r5) {
                case 1: goto L_0x00fc;
                case 2: goto L_0x00f5;
                case 3: goto L_0x00f0;
                case 4: goto L_0x00e7;
                case 5: goto L_0x00cb;
                default: goto L_0x00c9;
            }
        L_0x00c9:
            monitor-exit(r4)
            return r2
        L_0x00cb:
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r5 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.READY     // Catch:{ all -> 0x0105 }
            r4.mCurrentInterstitialState = r5     // Catch:{ all -> 0x0105 }
            com.mopub.mobileads.MoPubInterstitial$MoPubInterstitialView r5 = r4.mInterstitialView     // Catch:{ all -> 0x0105 }
            java.lang.String r5 = r5.getCustomEventClassName()     // Catch:{ all -> 0x0105 }
            boolean r5 = com.mopub.mobileads.AdTypeTranslator.CustomEventType.isMoPubSpecific(r5)     // Catch:{ all -> 0x0105 }
            if (r5 == 0) goto L_0x00e5
            android.os.Handler r5 = r4.mHandler     // Catch:{ all -> 0x0105 }
            java.lang.Runnable r6 = r4.mAdExpiration     // Catch:{ all -> 0x0105 }
            r2 = 14400000(0xdbba00, double:7.1145453E-317)
            r5.postDelayed(r6, r2)     // Catch:{ all -> 0x0105 }
        L_0x00e5:
            monitor-exit(r4)
            return r1
        L_0x00e7:
            r4.invalidateInterstitialAdapter()     // Catch:{ all -> 0x0105 }
            com.mopub.mobileads.MoPubInterstitial$InterstitialState r5 = com.mopub.mobileads.MoPubInterstitial.InterstitialState.IDLE     // Catch:{ all -> 0x0105 }
            r4.mCurrentInterstitialState = r5     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r1
        L_0x00f0:
            r4.setInterstitialStateDestroyed()     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r1
        L_0x00f5:
            java.lang.String r5 = "Interstitial is not ready to be shown yet."
            com.mopub.common.logging.MoPubLog.d(r5)     // Catch:{ all -> 0x0105 }
            monitor-exit(r4)
            return r2
        L_0x00fc:
            if (r6 != 0) goto L_0x0103
            java.lang.String r5 = "Already loading an interstitial."
            com.mopub.common.logging.MoPubLog.d(r5)     // Catch:{ all -> 0x0105 }
        L_0x0103:
            monitor-exit(r4)
            return r2
        L_0x0105:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.MoPubInterstitial.attemptStateTransition(com.mopub.mobileads.MoPubInterstitial$InterstitialState, boolean):boolean");
    }

    private void setInterstitialStateDestroyed() {
        invalidateInterstitialAdapter();
        this.mInterstitialAdListener = null;
        this.mInterstitialView.setBannerAdListener(null);
        this.mInterstitialView.destroy();
        this.mHandler.removeCallbacks(this.mAdExpiration);
        this.mCurrentInterstitialState = InterstitialState.DESTROYED;
    }

    public void load() {
        attemptStateTransition(InterstitialState.LOADING);
    }

    public boolean show() {
        return attemptStateTransition(InterstitialState.SHOWING);
    }

    public void forceRefresh() {
        attemptStateTransition(InterstitialState.IDLE, true);
        attemptStateTransition(InterstitialState.LOADING, true);
    }

    public boolean isReady() {
        return this.mCurrentInterstitialState == InterstitialState.READY;
    }

    /* access modifiers changed from: 0000 */
    public boolean isDestroyed() {
        return this.mCurrentInterstitialState == InterstitialState.DESTROYED;
    }

    /* access modifiers changed from: 0000 */
    public Integer getAdTimeoutDelay(int i) {
        return this.mInterstitialView.getAdTimeoutDelay(i);
    }

    private void showCustomEventInterstitial() {
        CustomEventInterstitialAdapter customEventInterstitialAdapter = this.mCustomEventInterstitialAdapter;
        if (customEventInterstitialAdapter != null) {
            customEventInterstitialAdapter.showInterstitial();
        }
    }

    private void invalidateInterstitialAdapter() {
        CustomEventInterstitialAdapter customEventInterstitialAdapter = this.mCustomEventInterstitialAdapter;
        if (customEventInterstitialAdapter != null) {
            customEventInterstitialAdapter.invalidate();
            this.mCustomEventInterstitialAdapter = null;
        }
    }

    public void setKeywords(@Nullable String str) {
        this.mInterstitialView.setKeywords(str);
    }

    @Nullable
    public String getKeywords() {
        return this.mInterstitialView.getKeywords();
    }

    public void setUserDataKeywords(@Nullable String str) {
        this.mInterstitialView.setUserDataKeywords(str);
    }

    @Nullable
    public String getUserDataKeywords() {
        return this.mInterstitialView.getUserDataKeywords();
    }

    @NonNull
    public Activity getActivity() {
        return this.mActivity;
    }

    @Nullable
    public Location getLocation() {
        return this.mInterstitialView.getLocation();
    }

    public void destroy() {
        attemptStateTransition(InterstitialState.DESTROYED);
    }

    public void setInterstitialAdListener(@Nullable InterstitialAdListener interstitialAdListener) {
        this.mInterstitialAdListener = interstitialAdListener;
    }

    @Nullable
    public InterstitialAdListener getInterstitialAdListener() {
        return this.mInterstitialAdListener;
    }

    public void setTesting(boolean z) {
        this.mInterstitialView.setTesting(z);
    }

    public boolean getTesting() {
        return this.mInterstitialView.getTesting();
    }

    public void setLocalExtras(Map<String, Object> map) {
        this.mInterstitialView.setLocalExtras(map);
    }

    @NonNull
    public Map<String, Object> getLocalExtras() {
        return this.mInterstitialView.getLocalExtras();
    }

    public void onCustomEventInterstitialLoaded() {
        if (!isDestroyed()) {
            attemptStateTransition(InterstitialState.READY);
            if (this.mInterstitialView.mAdViewController != null) {
                this.mInterstitialView.mAdViewController.creativeDownloadSuccess();
            }
            InterstitialAdListener interstitialAdListener = this.mInterstitialAdListener;
            if (interstitialAdListener != null) {
                interstitialAdListener.onInterstitialLoaded(this);
            }
        }
    }

    public void onCustomEventInterstitialFailed(@NonNull MoPubErrorCode moPubErrorCode) {
        if (!isDestroyed() && !this.mInterstitialView.loadFailUrl(moPubErrorCode)) {
            attemptStateTransition(InterstitialState.IDLE);
        }
    }

    public void onCustomEventInterstitialShown() {
        if (!isDestroyed()) {
            CustomEventInterstitialAdapter customEventInterstitialAdapter = this.mCustomEventInterstitialAdapter;
            if (customEventInterstitialAdapter == null || customEventInterstitialAdapter.isAutomaticImpressionAndClickTrackingEnabled()) {
                this.mInterstitialView.trackImpression();
            }
            InterstitialAdListener interstitialAdListener = this.mInterstitialAdListener;
            if (interstitialAdListener != null) {
                interstitialAdListener.onInterstitialShown(this);
            }
        }
    }

    public void onCustomEventInterstitialClicked() {
        if (!isDestroyed()) {
            this.mInterstitialView.registerClick();
            InterstitialAdListener interstitialAdListener = this.mInterstitialAdListener;
            if (interstitialAdListener != null) {
                interstitialAdListener.onInterstitialClicked(this);
            }
        }
    }

    public void onCustomEventInterstitialImpression() {
        if (!isDestroyed()) {
            CustomEventInterstitialAdapter customEventInterstitialAdapter = this.mCustomEventInterstitialAdapter;
            if (customEventInterstitialAdapter != null && !customEventInterstitialAdapter.isAutomaticImpressionAndClickTrackingEnabled()) {
                this.mInterstitialView.trackImpression();
            }
        }
    }

    public void onCustomEventInterstitialDismissed() {
        if (!isDestroyed()) {
            attemptStateTransition(InterstitialState.IDLE);
            InterstitialAdListener interstitialAdListener = this.mInterstitialAdListener;
            if (interstitialAdListener != null) {
                interstitialAdListener.onInterstitialDismissed(this);
            }
        }
    }
}
