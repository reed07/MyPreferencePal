package com.mopub.network;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.Request;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;

public class AdLoader {
    /* access modifiers changed from: private */
    @NonNull
    public final Object lock = new Object();
    private final com.mopub.network.MultiAdRequest.Listener mAdListener;
    private boolean mContentDownloaded;
    private final WeakReference<Context> mContext;
    @Nullable
    private ContentDownloadAnalytics mDownloadTracker;
    /* access modifiers changed from: private */
    public volatile boolean mFailed;
    @NonNull
    private Handler mHandler;
    @Nullable
    protected AdResponse mLastDeliveredResponse = null;
    @NonNull
    private MultiAdRequest mMultiAdRequest;
    @Nullable
    protected MultiAdResponse mMultiAdResponse;
    private final Listener mOriginalListener;
    /* access modifiers changed from: private */
    public volatile boolean mRunning;

    public interface Listener extends ErrorListener {
        void onSuccess(AdResponse adResponse);
    }

    public AdLoader(@NonNull String str, @NonNull AdFormat adFormat, @Nullable String str2, @NonNull Context context, @NonNull Listener listener) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str));
        Preconditions.checkNotNull(adFormat);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(listener);
        this.mContext = new WeakReference<>(context);
        this.mOriginalListener = listener;
        this.mHandler = new Handler();
        this.mAdListener = new com.mopub.network.MultiAdRequest.Listener() {
            public void onErrorResponse(VolleyError volleyError) {
                AdLoader.this.mFailed = true;
                AdLoader.this.mRunning = false;
                AdLoader.this.deliverError(volleyError);
            }

            public void onSuccessResponse(MultiAdResponse multiAdResponse) {
                synchronized (AdLoader.this.lock) {
                    AdLoader.this.mRunning = false;
                    AdLoader.this.mMultiAdResponse = multiAdResponse;
                    if (AdLoader.this.mMultiAdResponse.hasNext()) {
                        AdLoader.this.deliverResponse(AdLoader.this.mMultiAdResponse.next());
                    }
                }
            }
        };
        this.mRunning = false;
        this.mFailed = false;
        MultiAdRequest multiAdRequest = new MultiAdRequest(str, adFormat, str2, context, this.mAdListener);
        this.mMultiAdRequest = multiAdRequest;
    }

    public boolean hasMoreAds() {
        boolean z = false;
        if (this.mFailed || this.mContentDownloaded) {
            return false;
        }
        MultiAdResponse multiAdResponse = this.mMultiAdResponse;
        if (multiAdResponse == null || multiAdResponse.hasNext() || !multiAdResponse.isWaterfallFinished()) {
            z = true;
        }
        return z;
    }

    @Nullable
    public Request<?> loadNextAd(@Nullable MoPubError moPubError) {
        if (this.mRunning) {
            return this.mMultiAdRequest;
        }
        if (this.mFailed) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    AdLoader.this.deliverError(new MoPubNetworkError(Reason.UNSPECIFIED));
                }
            });
            return null;
        }
        synchronized (this.lock) {
            if (this.mMultiAdResponse == null) {
                Request<?> fetchAd = fetchAd(this.mMultiAdRequest, (Context) this.mContext.get());
                return fetchAd;
            }
            if (moPubError != null) {
                creativeDownloadFailed(moPubError);
            }
            if (this.mMultiAdResponse.hasNext()) {
                final AdResponse next = this.mMultiAdResponse.next();
                this.mHandler.post(new Runnable() {
                    public void run() {
                        AdLoader.this.deliverResponse(next);
                    }
                });
                MultiAdRequest multiAdRequest = this.mMultiAdRequest;
                return multiAdRequest;
            } else if (!this.mMultiAdResponse.isWaterfallFinished()) {
                MultiAdRequest multiAdRequest2 = new MultiAdRequest(this.mMultiAdResponse.getFailURL(), this.mMultiAdRequest.mAdFormat, this.mMultiAdRequest.mAdUnitId, (Context) this.mContext.get(), this.mAdListener);
                this.mMultiAdRequest = multiAdRequest2;
                Request<?> fetchAd2 = fetchAd(this.mMultiAdRequest, (Context) this.mContext.get());
                return fetchAd2;
            } else {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        AdLoader.this.deliverError(new MoPubNetworkError(Reason.NO_FILL));
                    }
                });
                return null;
            }
        }
    }

    public void creativeDownloadSuccess() {
        this.mContentDownloaded = true;
        if (this.mDownloadTracker == null) {
            MoPubLog.e("Response analytics should not be null here");
            return;
        }
        Context context = (Context) this.mContext.get();
        if (context == null || this.mLastDeliveredResponse == null) {
            MoPubLog.w("Cannot send 'x-after-load-url' analytics.");
            return;
        }
        this.mDownloadTracker.reportAfterLoad(context, null);
        this.mDownloadTracker.reportAfterLoadSuccess(context);
    }

    private void creativeDownloadFailed(@Nullable MoPubError moPubError) {
        if (moPubError == null) {
            MoPubLog.w("Must provide error code to report creative download error");
            return;
        }
        Context context = (Context) this.mContext.get();
        if (context == null || this.mLastDeliveredResponse == null) {
            MoPubLog.w("Cannot send creative mFailed analytics.");
            return;
        }
        ContentDownloadAnalytics contentDownloadAnalytics = this.mDownloadTracker;
        if (contentDownloadAnalytics != null) {
            contentDownloadAnalytics.reportAfterLoad(context, moPubError);
            this.mDownloadTracker.reportAfterLoadFail(context, moPubError);
        }
    }

    @Nullable
    private Request<?> fetchAd(@NonNull MultiAdRequest multiAdRequest, @Nullable Context context) {
        Preconditions.checkNotNull(multiAdRequest);
        if (context == null) {
            return null;
        }
        this.mRunning = true;
        MoPubRequestQueue requestQueue = Networking.getRequestQueue(context);
        this.mMultiAdRequest = multiAdRequest;
        requestQueue.add(multiAdRequest);
        return multiAdRequest;
    }

    /* access modifiers changed from: private */
    public void deliverError(@NonNull VolleyError volleyError) {
        Preconditions.checkNotNull(volleyError);
        this.mLastDeliveredResponse = null;
        Listener listener = this.mOriginalListener;
        if (listener == null) {
            return;
        }
        if (volleyError instanceof MoPubNetworkError) {
            listener.onErrorResponse(volleyError);
        } else {
            listener.onErrorResponse(new MoPubNetworkError(volleyError.getMessage(), volleyError.getCause(), Reason.UNSPECIFIED));
        }
    }

    /* access modifiers changed from: private */
    public void deliverResponse(@NonNull AdResponse adResponse) {
        Preconditions.checkNotNull(adResponse);
        Context context = (Context) this.mContext.get();
        this.mDownloadTracker = new ContentDownloadAnalytics(adResponse);
        this.mDownloadTracker.reportBeforeLoad(context);
        Listener listener = this.mOriginalListener;
        if (listener != null) {
            this.mLastDeliveredResponse = adResponse;
            listener.onSuccess(adResponse);
        }
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    public boolean isFailed() {
        return this.mFailed;
    }
}
