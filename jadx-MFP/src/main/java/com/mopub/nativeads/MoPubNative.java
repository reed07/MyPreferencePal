package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.ManifestUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.network.AdLoader;
import com.mopub.network.AdLoader.Listener;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.TreeMap;

public class MoPubNative {
    static final MoPubNativeNetworkListener EMPTY_NETWORK_LISTENER = new MoPubNativeNetworkListener() {
        public void onNativeFail(NativeErrorCode nativeErrorCode) {
        }

        public void onNativeLoad(@NonNull NativeAd nativeAd) {
            nativeAd.destroy();
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public AdLoader mAdLoader;
    @NonNull
    AdRendererRegistry mAdRendererRegistry;
    /* access modifiers changed from: private */
    @NonNull
    public final String mAdUnitId;
    @NonNull
    private final WeakReference<Context> mContext;
    @NonNull
    private Map<String, Object> mLocalExtras;
    /* access modifiers changed from: private */
    @NonNull
    public MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    /* access modifiers changed from: private */
    @Nullable
    public CustomEventNativeAdapter mNativeAdapter;
    @Nullable
    private Request mNativeRequest;
    @NonNull
    private final Listener mVolleyListener;

    public interface MoPubNativeNetworkListener {
        void onNativeFail(NativeErrorCode nativeErrorCode);

        void onNativeLoad(NativeAd nativeAd);
    }

    public MoPubNative(@NonNull Context context, @NonNull String str, @NonNull MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this(context, str, new AdRendererRegistry(), moPubNativeNetworkListener);
    }

    @VisibleForTesting
    public MoPubNative(@NonNull Context context, @NonNull String str, @NonNull AdRendererRegistry adRendererRegistry, @NonNull MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this.mLocalExtras = new TreeMap();
        Preconditions.checkNotNull(context, "context may not be null.");
        Preconditions.checkNotNull(str, "AdUnitId may not be null.");
        Preconditions.checkNotNull(adRendererRegistry, "AdRendererRegistry may not be null.");
        Preconditions.checkNotNull(moPubNativeNetworkListener, "MoPubNativeNetworkListener may not be null.");
        ManifestUtils.checkNativeActivitiesDeclared(context);
        this.mContext = new WeakReference<>(context);
        this.mAdUnitId = str;
        this.mMoPubNativeNetworkListener = moPubNativeNetworkListener;
        this.mAdRendererRegistry = adRendererRegistry;
        this.mVolleyListener = new Listener() {
            public void onSuccess(@NonNull AdResponse adResponse) {
                MoPubNative.this.onAdLoad(adResponse);
            }

            public void onErrorResponse(@NonNull VolleyError volleyError) {
                MoPubNative.this.onAdError(volleyError);
            }
        };
    }

    public void registerAdRenderer(MoPubAdRenderer moPubAdRenderer) {
        this.mAdRendererRegistry.registerAdRenderer(moPubAdRenderer);
    }

    public void destroy() {
        this.mContext.clear();
        Request request = this.mNativeRequest;
        if (request != null) {
            request.cancel();
            this.mNativeRequest = null;
        }
        this.mAdLoader = null;
        this.mMoPubNativeNetworkListener = EMPTY_NETWORK_LISTENER;
    }

    public void setLocalExtras(@Nullable Map<String, Object> map) {
        if (map == null) {
            this.mLocalExtras = new TreeMap();
        } else {
            this.mLocalExtras = new TreeMap(map);
        }
    }

    public void makeRequest() {
        makeRequest(null);
    }

    public void makeRequest(@Nullable RequestParameters requestParameters) {
        makeRequest(requestParameters, null);
    }

    public void makeRequest(@Nullable RequestParameters requestParameters, @Nullable Integer num) {
        Context contextOrDestroy = getContextOrDestroy();
        if (contextOrDestroy != null) {
            if (!DeviceUtils.isNetworkAvailable(contextOrDestroy)) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
            } else {
                loadNativeAd(requestParameters, num);
            }
        }
    }

    private void loadNativeAd(@Nullable RequestParameters requestParameters, @Nullable Integer num) {
        Context contextOrDestroy = getContextOrDestroy();
        if (contextOrDestroy != null) {
            NativeUrlGenerator withRequest = new NativeUrlGenerator(contextOrDestroy).withAdUnitId(this.mAdUnitId).withRequest(requestParameters);
            if (num != null) {
                withRequest.withSequenceNumber(num.intValue());
            }
            String generateUrlString = withRequest.generateUrlString(Constants.HOST);
            if (generateUrlString != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("MoPubNative Loading ad from: ");
                sb.append(generateUrlString);
                MoPubLog.d(sb.toString());
            }
            requestNativeAd(generateUrlString, null);
        }
    }

    /* access modifiers changed from: 0000 */
    public void requestNativeAd(@Nullable String str, @Nullable NativeErrorCode nativeErrorCode) {
        Context contextOrDestroy = getContextOrDestroy();
        if (contextOrDestroy != null) {
            AdLoader adLoader = this.mAdLoader;
            if (adLoader == null || !adLoader.hasMoreAds()) {
                if (TextUtils.isEmpty(str)) {
                    MoPubNativeNetworkListener moPubNativeNetworkListener = this.mMoPubNativeNetworkListener;
                    if (nativeErrorCode == null) {
                        nativeErrorCode = NativeErrorCode.INVALID_REQUEST_URL;
                    }
                    moPubNativeNetworkListener.onNativeFail(nativeErrorCode);
                    return;
                }
                AdLoader adLoader2 = new AdLoader(str, AdFormat.NATIVE, this.mAdUnitId, contextOrDestroy, this.mVolleyListener);
                this.mAdLoader = adLoader2;
            }
            this.mNativeRequest = this.mAdLoader.loadNextAd(nativeErrorCode);
        }
    }

    /* access modifiers changed from: private */
    public void onAdLoad(@NonNull final AdResponse adResponse) {
        Context contextOrDestroy = getContextOrDestroy();
        if (contextOrDestroy != null) {
            AnonymousClass3 r1 = new CustomEventNativeListener() {
                public void onNativeAdLoaded(@NonNull BaseNativeAd baseNativeAd) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("MoPubNative.onNativeAdLoaded ");
                    sb.append(MoPubNative.this.mNativeAdapter);
                    MoPubLog.w(sb.toString());
                    MoPubNative.this.mNativeAdapter = null;
                    Context contextOrDestroy = MoPubNative.this.getContextOrDestroy();
                    if (contextOrDestroy != null) {
                        MoPubAdRenderer rendererForAd = MoPubNative.this.mAdRendererRegistry.getRendererForAd(baseNativeAd);
                        if (rendererForAd == null) {
                            onNativeAdFailed(NativeErrorCode.NATIVE_RENDERER_CONFIGURATION_ERROR);
                            return;
                        }
                        if (MoPubNative.this.mAdLoader != null) {
                            MoPubNative.this.mAdLoader.creativeDownloadSuccess();
                        }
                        MoPubNativeNetworkListener access$400 = MoPubNative.this.mMoPubNativeNetworkListener;
                        NativeAd nativeAd = new NativeAd(contextOrDestroy, adResponse.getImpressionTrackingUrls(), adResponse.getClickTrackingUrl(), MoPubNative.this.mAdUnitId, baseNativeAd, rendererForAd);
                        access$400.onNativeLoad(nativeAd);
                    }
                }

                public void onNativeAdFailed(NativeErrorCode nativeErrorCode) {
                    MoPubLog.v(String.format("Native Ad failed to load with error: %s.", new Object[]{nativeErrorCode}));
                    MoPubNative.this.mNativeAdapter = null;
                    MoPubNative.this.requestNativeAd("", nativeErrorCode);
                }
            };
            if (this.mNativeAdapter != null) {
                MoPubLog.w("Native adapter is not null.");
                this.mNativeAdapter.stopLoading();
            }
            this.mNativeAdapter = new CustomEventNativeAdapter(r1);
            this.mNativeAdapter.loadNativeAd(contextOrDestroy, this.mLocalExtras, adResponse);
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void onAdError(@NonNull VolleyError volleyError) {
        MoPubLog.d("Native ad request failed.", volleyError);
        if (volleyError instanceof MoPubNetworkError) {
            MoPubNetworkError moPubNetworkError = (MoPubNetworkError) volleyError;
            switch (moPubNetworkError.getReason()) {
                case BAD_BODY:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
                    return;
                case BAD_HEADER_DATA:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
                    return;
                case WARMING_UP:
                    MoPubLog.c(MoPubErrorCode.WARMUP.toString());
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
                    return;
                case NO_FILL:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
                    return;
                default:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
                    return;
            }
        } else {
            NetworkResponse networkResponse = volleyError.networkResponse;
            if (networkResponse != null && networkResponse.statusCode >= 500 && networkResponse.statusCode < 600) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.SERVER_ERROR_RESPONSE_CODE);
            } else if (networkResponse != null || DeviceUtils.isNetworkAvailable((Context) this.mContext.get())) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
            } else {
                MoPubLog.c(String.valueOf(MoPubErrorCode.NO_CONNECTION.toString()));
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @VisibleForTesting
    public Context getContextOrDestroy() {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            destroy();
            MoPubLog.d("Weak reference to Context in MoPubNative became null. This instance of MoPubNative is destroyed and No more requests will be processed.");
        }
        return context;
    }
}
