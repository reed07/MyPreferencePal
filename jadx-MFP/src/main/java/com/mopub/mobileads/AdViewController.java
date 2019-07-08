package com.mopub.mobileads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mraid.MraidNativeCommandHandler;
import com.mopub.network.AdLoader;
import com.mopub.network.AdLoader.Listener;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

public class AdViewController {
    private static final LayoutParams WRAP_AND_CENTER_LAYOUT_PARAMS = new LayoutParams(-2, -2, 17);
    private static final WeakHashMap<View, Boolean> sViewShouldHonorServerDimensions = new WeakHashMap<>();
    @Nullable
    private Request mActiveRequest;
    @NonNull
    private final Listener mAdListener;
    @Nullable
    AdLoader mAdLoader;
    @Nullable
    private AdResponse mAdResponse;
    @Nullable
    private String mAdUnitId;
    private boolean mAdWasLoaded;
    @VisibleForTesting
    int mBackoffPower = 1;
    private final long mBroadcastIdentifier;
    @Nullable
    private Context mContext;
    private boolean mCurrentAutoRefreshStatus = true;
    @Nullable
    private String mCustomEventClassName;
    private boolean mExpanded;
    private Handler mHandler;
    private boolean mIsDestroyed;
    private boolean mIsTesting;
    private String mKeywords;
    private Map<String, Object> mLocalExtras = new HashMap();
    private Location mLocation;
    @Nullable
    private MoPubView mMoPubView;
    private final Runnable mRefreshRunnable;
    @Nullable
    private Integer mRefreshTimeMillis;
    private boolean mShouldAllowAutoRefresh = true;
    @Nullable
    private WebViewAdUrlGenerator mUrlGenerator;
    private String mUserDataKeywords;

    public static void setShouldHonorServerDimensions(View view) {
        sViewShouldHonorServerDimensions.put(view, Boolean.valueOf(true));
    }

    private static boolean getShouldHonorServerDimensions(View view) {
        return sViewShouldHonorServerDimensions.get(view) != null;
    }

    public AdViewController(@NonNull Context context, @NonNull MoPubView moPubView) {
        this.mContext = context;
        this.mMoPubView = moPubView;
        this.mBroadcastIdentifier = Utils.generateUniqueId();
        this.mUrlGenerator = new WebViewAdUrlGenerator(this.mContext.getApplicationContext(), MraidNativeCommandHandler.isStorePictureSupported(this.mContext));
        this.mAdListener = new Listener() {
            public void onSuccess(AdResponse adResponse) {
                AdViewController.this.onAdLoadSuccess(adResponse);
            }

            public void onErrorResponse(VolleyError volleyError) {
                AdViewController.this.onAdLoadError(volleyError);
            }
        };
        this.mRefreshRunnable = new Runnable() {
            public void run() {
                AdViewController.this.internalLoadAd();
            }
        };
        this.mRefreshTimeMillis = Integer.valueOf(60000);
        this.mHandler = new Handler();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void onAdLoadSuccess(@NonNull AdResponse adResponse) {
        this.mBackoffPower = 1;
        this.mAdResponse = adResponse;
        this.mCustomEventClassName = adResponse.getCustomEventClassName();
        this.mRefreshTimeMillis = this.mAdResponse.getRefreshTimeMillis();
        this.mActiveRequest = null;
        loadCustomEvent(this.mMoPubView, adResponse.getCustomEventClassName(), adResponse.getServerExtras());
        scheduleRefreshTimerIfEnabled();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void onAdLoadError(VolleyError volleyError) {
        if (volleyError instanceof MoPubNetworkError) {
            MoPubNetworkError moPubNetworkError = (MoPubNetworkError) volleyError;
            if (moPubNetworkError.getRefreshTimeMillis() != null) {
                this.mRefreshTimeMillis = moPubNetworkError.getRefreshTimeMillis();
            }
        }
        MoPubErrorCode errorCodeFromVolleyError = getErrorCodeFromVolleyError(volleyError, this.mContext);
        if (errorCodeFromVolleyError == MoPubErrorCode.SERVER_ERROR) {
            this.mBackoffPower++;
        }
        adDidFail(errorCodeFromVolleyError);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void loadCustomEvent(@Nullable MoPubView moPubView, @Nullable String str, @NonNull Map<String, String> map) {
        Preconditions.checkNotNull(map);
        if (moPubView == null) {
            MoPubLog.d("Can't load an ad in this ad view because it was destroyed.");
        } else {
            moPubView.loadCustomEvent(str, map);
        }
    }

    @NonNull
    @VisibleForTesting
    static MoPubErrorCode getErrorCodeFromVolleyError(@NonNull VolleyError volleyError, @Nullable Context context) {
        NetworkResponse networkResponse = volleyError.networkResponse;
        if (volleyError instanceof MoPubNetworkError) {
            switch (((MoPubNetworkError) volleyError).getReason()) {
                case WARMING_UP:
                    return MoPubErrorCode.WARMUP;
                case NO_FILL:
                    return MoPubErrorCode.NO_FILL;
                default:
                    return MoPubErrorCode.UNSPECIFIED;
            }
        } else if (networkResponse == null) {
            if (!DeviceUtils.isNetworkAvailable(context)) {
                return MoPubErrorCode.NO_CONNECTION;
            }
            return MoPubErrorCode.UNSPECIFIED;
        } else if (volleyError.networkResponse.statusCode >= 400) {
            return MoPubErrorCode.SERVER_ERROR;
        } else {
            return MoPubErrorCode.UNSPECIFIED;
        }
    }

    @Nullable
    public MoPubView getMoPubView() {
        return this.mMoPubView;
    }

    public void loadAd() {
        this.mBackoffPower = 1;
        internalLoadAd();
    }

    /* access modifiers changed from: private */
    public void internalLoadAd() {
        this.mAdWasLoaded = true;
        if (TextUtils.isEmpty(this.mAdUnitId)) {
            MoPubLog.d("Can't load an ad in this ad view because the ad unit ID is not set. Did you forget to call setAdUnitId()?");
        } else if (!isNetworkAvailable()) {
            MoPubLog.d("Can't load an ad because there is no network connectivity.");
            scheduleRefreshTimerIfEnabled();
        } else {
            loadNonJavascript(generateAdUrl(), null);
        }
    }

    /* access modifiers changed from: 0000 */
    public void loadNonJavascript(@Nullable String str, @Nullable MoPubError moPubError) {
        if (str == null) {
            adDidFail(MoPubErrorCode.NO_FILL);
            return;
        }
        if (!str.startsWith("javascript:")) {
            StringBuilder sb = new StringBuilder();
            sb.append("Loading url: ");
            sb.append(str);
            MoPubLog.d(sb.toString());
        }
        if (this.mActiveRequest != null) {
            if (!TextUtils.isEmpty(this.mAdUnitId)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Already loading an ad for ");
                sb2.append(this.mAdUnitId);
                sb2.append(", wait to finish.");
                MoPubLog.i(sb2.toString());
            }
            return;
        }
        fetchAd(str, moPubError);
    }

    @Deprecated
    public void reload() {
        loadAd();
    }

    /* access modifiers changed from: 0000 */
    public boolean loadFailUrl(MoPubErrorCode moPubErrorCode) {
        String str = "MoPub";
        StringBuilder sb = new StringBuilder();
        sb.append("MoPubErrorCode: ");
        sb.append(moPubErrorCode == null ? "" : moPubErrorCode.toString());
        Log.v(str, sb.toString());
        AdLoader adLoader = this.mAdLoader;
        if (adLoader == null || !adLoader.hasMoreAds()) {
            adDidFail(MoPubErrorCode.NO_FILL);
            return false;
        }
        loadNonJavascript("", moPubErrorCode);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void setNotLoading() {
        Request request = this.mActiveRequest;
        if (request != null) {
            if (!request.isCanceled()) {
                this.mActiveRequest.cancel();
            }
            this.mActiveRequest = null;
        }
        this.mAdLoader = null;
    }

    /* access modifiers changed from: 0000 */
    public void creativeDownloadSuccess() {
        scheduleRefreshTimerIfEnabled();
        AdLoader adLoader = this.mAdLoader;
        if (adLoader == null) {
            MoPubLog.w("mAdLoader is not supposed to be null");
            return;
        }
        adLoader.creativeDownloadSuccess();
        this.mAdLoader = null;
    }

    public String getKeywords() {
        return this.mKeywords;
    }

    public void setKeywords(String str) {
        this.mKeywords = str;
    }

    public String getUserDataKeywords() {
        if (!MoPub.canCollectPersonalInformation()) {
            return null;
        }
        return this.mUserDataKeywords;
    }

    public void setUserDataKeywords(String str) {
        if (!MoPub.canCollectPersonalInformation()) {
            this.mUserDataKeywords = null;
        } else {
            this.mUserDataKeywords = str;
        }
    }

    public Location getLocation() {
        if (!MoPub.canCollectPersonalInformation()) {
            return null;
        }
        return this.mLocation;
    }

    public void setLocation(Location location) {
        if (!MoPub.canCollectPersonalInformation()) {
            this.mLocation = null;
        } else {
            this.mLocation = location;
        }
    }

    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    @Nullable
    public String getCustomEventClassName() {
        return this.mCustomEventClassName;
    }

    public void setAdUnitId(@NonNull String str) {
        this.mAdUnitId = str;
    }

    public long getBroadcastIdentifier() {
        return this.mBroadcastIdentifier;
    }

    public int getAdWidth() {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse == null || adResponse.getWidth() == null) {
            return 0;
        }
        return this.mAdResponse.getWidth().intValue();
    }

    public int getAdHeight() {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse == null || adResponse.getHeight() == null) {
            return 0;
        }
        return this.mAdResponse.getHeight().intValue();
    }

    @Deprecated
    public boolean getAutorefreshEnabled() {
        return getCurrentAutoRefreshStatus();
    }

    public boolean getCurrentAutoRefreshStatus() {
        return this.mCurrentAutoRefreshStatus;
    }

    /* access modifiers changed from: 0000 */
    public void pauseRefresh() {
        setAutoRefreshStatus(false);
    }

    /* access modifiers changed from: 0000 */
    public void resumeRefresh() {
        if (this.mShouldAllowAutoRefresh && !this.mExpanded) {
            setAutoRefreshStatus(true);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setShouldAllowAutoRefresh(boolean z) {
        this.mShouldAllowAutoRefresh = z;
        setAutoRefreshStatus(z);
    }

    private void setAutoRefreshStatus(boolean z) {
        if (this.mAdWasLoaded && this.mCurrentAutoRefreshStatus != z) {
            String str = z ? "enabled" : "disabled";
            StringBuilder sb = new StringBuilder();
            sb.append("Refresh ");
            sb.append(str);
            sb.append(" for ad unit (");
            sb.append(this.mAdUnitId);
            sb.append(").");
            MoPubLog.d(sb.toString());
        }
        this.mCurrentAutoRefreshStatus = z;
        if (this.mAdWasLoaded && this.mCurrentAutoRefreshStatus) {
            scheduleRefreshTimerIfEnabled();
        } else if (!this.mCurrentAutoRefreshStatus) {
            cancelRefreshTimer();
        }
    }

    /* access modifiers changed from: 0000 */
    public void expand() {
        this.mExpanded = true;
        pauseRefresh();
    }

    /* access modifiers changed from: 0000 */
    public void collapse() {
        this.mExpanded = false;
        resumeRefresh();
    }

    @Nullable
    public AdReport getAdReport() {
        String str = this.mAdUnitId;
        if (str == null || this.mAdResponse == null) {
            return null;
        }
        return new AdReport(str, ClientMetadata.getInstance(this.mContext), this.mAdResponse);
    }

    public boolean getTesting() {
        return this.mIsTesting;
    }

    public void setTesting(boolean z) {
        this.mIsTesting = z;
    }

    /* access modifiers changed from: 0000 */
    public void cleanup() {
        if (!this.mIsDestroyed) {
            setNotLoading();
            setAutoRefreshStatus(false);
            cancelRefreshTimer();
            this.mMoPubView = null;
            this.mContext = null;
            this.mUrlGenerator = null;
            this.mIsDestroyed = true;
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Integer getAdTimeoutDelay(int i) {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse == null) {
            return Integer.valueOf(i);
        }
        return adResponse.getAdTimeoutMillis(i);
    }

    /* access modifiers changed from: 0000 */
    public void trackImpression() {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse != null) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) adResponse.getImpressionTrackingUrls(), this.mContext);
        }
    }

    /* access modifiers changed from: 0000 */
    public void registerClick() {
        AdResponse adResponse = this.mAdResponse;
        if (adResponse != null) {
            TrackingRequest.makeTrackingHttpRequest(adResponse.getClickTrackingUrl(), this.mContext);
        }
    }

    /* access modifiers changed from: 0000 */
    public void fetchAd(@NonNull String str, @Nullable MoPubError moPubError) {
        MoPubView moPubView = getMoPubView();
        if (moPubView == null || this.mContext == null) {
            MoPubLog.d("Can't load an ad in this ad view because it was destroyed.");
            setNotLoading();
            return;
        }
        synchronized (this) {
            if (this.mAdLoader == null || !this.mAdLoader.hasMoreAds()) {
                AdLoader adLoader = new AdLoader(str, moPubView.getAdFormat(), this.mAdUnitId, this.mContext, this.mAdListener);
                this.mAdLoader = adLoader;
            }
        }
        this.mActiveRequest = this.mAdLoader.loadNextAd(moPubError);
    }

    /* access modifiers changed from: 0000 */
    public void forceRefresh() {
        setNotLoading();
        loadAd();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String generateAdUrl() {
        Location location = null;
        if (this.mUrlGenerator == null) {
            return null;
        }
        boolean canCollectPersonalInformation = MoPub.canCollectPersonalInformation();
        AdUrlGenerator withUserDataKeywords = this.mUrlGenerator.withAdUnitId(this.mAdUnitId).withKeywords(this.mKeywords).withUserDataKeywords(canCollectPersonalInformation ? this.mUserDataKeywords : null);
        if (canCollectPersonalInformation) {
            location = this.mLocation;
        }
        withUserDataKeywords.withLocation(location);
        return this.mUrlGenerator.generateUrlString(Constants.HOST);
    }

    /* access modifiers changed from: 0000 */
    public void adDidFail(MoPubErrorCode moPubErrorCode) {
        MoPubLog.i("Ad failed to load.");
        setNotLoading();
        MoPubView moPubView = getMoPubView();
        if (moPubView != null) {
            scheduleRefreshTimerIfEnabled();
            moPubView.adFailed(moPubErrorCode);
        }
    }

    /* access modifiers changed from: 0000 */
    public void scheduleRefreshTimerIfEnabled() {
        cancelRefreshTimer();
        if (this.mCurrentAutoRefreshStatus) {
            Integer num = this.mRefreshTimeMillis;
            if (num != null && num.intValue() > 0) {
                this.mHandler.postDelayed(this.mRefreshRunnable, Math.min(600000, ((long) this.mRefreshTimeMillis.intValue()) * ((long) Math.pow(1.5d, (double) this.mBackoffPower))));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setLocalExtras(Map<String, Object> map) {
        this.mLocalExtras = map != null ? new TreeMap(map) : new TreeMap();
    }

    /* access modifiers changed from: 0000 */
    public Map<String, Object> getLocalExtras() {
        Map<String, Object> map = this.mLocalExtras;
        return map != null ? new TreeMap(map) : new TreeMap();
    }

    private void cancelRefreshTimer() {
        this.mHandler.removeCallbacks(this.mRefreshRunnable);
    }

    @SuppressLint({"MissingPermission"})
    private boolean isNetworkAvailable() {
        Context context = this.mContext;
        boolean z = false;
        if (context == null) {
            return false;
        }
        if (!DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public void setAdContentView(final View view) {
        this.mHandler.post(new Runnable() {
            public void run() {
                MoPubView moPubView = AdViewController.this.getMoPubView();
                if (moPubView != null) {
                    moPubView.removeAllViews();
                    View view = view;
                    moPubView.addView(view, AdViewController.this.getAdLayoutParams(view));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public LayoutParams getAdLayoutParams(View view) {
        Integer num;
        AdResponse adResponse = this.mAdResponse;
        Integer num2 = null;
        if (adResponse != null) {
            num2 = adResponse.getWidth();
            num = this.mAdResponse.getHeight();
        } else {
            num = null;
        }
        if (num2 == null || num == null || !getShouldHonorServerDimensions(view) || num2.intValue() <= 0 || num.intValue() <= 0) {
            return WRAP_AND_CENTER_LAYOUT_PARAMS;
        }
        return new LayoutParams(Dips.asIntPixels((float) num2.intValue(), this.mContext), Dips.asIntPixels((float) num.intValue(), this.mContext), 17);
    }
}
