package com.mopub.nativeads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Constants;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.nativeads.PositioningSource.PositioningListener;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.network.Networking;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.Response.Listener;
import com.mopub.volley.VolleyError;
import com.myfitnesspal.shared.constants.Constants.Config;

class ServerPositioningSource implements PositioningSource {
    /* access modifiers changed from: private */
    @NonNull
    public final Context mContext;
    private final ErrorListener mErrorListener;
    @Nullable
    private PositioningListener mListener;
    private int mMaximumRetryTimeMillis = Config.CACHE_TTL_MILLISECONDS;
    private final Listener<MoPubClientPositioning> mPositioningListener;
    @Nullable
    private PositioningRequest mRequest;
    private int mRetryCount;
    @NonNull
    private final Handler mRetryHandler;
    @NonNull
    private final Runnable mRetryRunnable;
    @Nullable
    private String mRetryUrl;

    ServerPositioningSource(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
        this.mRetryHandler = new Handler();
        this.mRetryRunnable = new Runnable() {
            public void run() {
                ServerPositioningSource.this.requestPositioningInternal();
            }
        };
        this.mPositioningListener = new Listener<MoPubClientPositioning>() {
            public void onResponse(MoPubClientPositioning moPubClientPositioning) {
                ServerPositioningSource.this.handleSuccess(moPubClientPositioning);
            }
        };
        this.mErrorListener = new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (!(volleyError instanceof MoPubNetworkError) || ((MoPubNetworkError) volleyError).getReason().equals(Reason.WARMING_UP)) {
                    MoPubLog.e("Failed to load positioning data", volleyError);
                    if (volleyError.networkResponse == null && !DeviceUtils.isNetworkAvailable(ServerPositioningSource.this.mContext)) {
                        MoPubLog.c(String.valueOf(MoPubErrorCode.NO_CONNECTION.toString()));
                    }
                }
                ServerPositioningSource.this.handleFailure();
            }
        };
    }

    public void loadPositions(@NonNull String str, @NonNull PositioningListener positioningListener) {
        PositioningRequest positioningRequest = this.mRequest;
        if (positioningRequest != null) {
            positioningRequest.cancel();
            this.mRequest = null;
        }
        if (this.mRetryCount > 0) {
            this.mRetryHandler.removeCallbacks(this.mRetryRunnable);
            this.mRetryCount = 0;
        }
        this.mListener = positioningListener;
        this.mRetryUrl = new PositioningUrlGenerator(this.mContext).withAdUnitId(str).generateUrlString(Constants.HOST);
        requestPositioningInternal();
    }

    /* access modifiers changed from: private */
    public void requestPositioningInternal() {
        StringBuilder sb = new StringBuilder();
        sb.append("Loading positioning from: ");
        sb.append(this.mRetryUrl);
        MoPubLog.d(sb.toString());
        this.mRequest = new PositioningRequest(this.mContext, this.mRetryUrl, this.mPositioningListener, this.mErrorListener);
        Networking.getRequestQueue(this.mContext).add(this.mRequest);
    }

    /* access modifiers changed from: private */
    public void handleSuccess(@NonNull MoPubClientPositioning moPubClientPositioning) {
        PositioningListener positioningListener = this.mListener;
        if (positioningListener != null) {
            positioningListener.onLoad(moPubClientPositioning);
        }
        this.mListener = null;
        this.mRetryCount = 0;
    }

    /* access modifiers changed from: private */
    public void handleFailure() {
        int pow = (int) (Math.pow(2.0d, (double) (this.mRetryCount + 1)) * 1000.0d);
        if (pow >= this.mMaximumRetryTimeMillis) {
            MoPubLog.d("Error downloading positioning information");
            PositioningListener positioningListener = this.mListener;
            if (positioningListener != null) {
                positioningListener.onFailed();
            }
            this.mListener = null;
            return;
        }
        this.mRetryCount++;
        this.mRetryHandler.postDelayed(this.mRetryRunnable, (long) pow);
    }
}
