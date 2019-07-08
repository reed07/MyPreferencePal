package com.mopub.mobileads;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.appindexing.Indexable;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.RewardedVideoCompletionRequest.RewardedVideoCompletionRequestListener;
import com.mopub.network.Networking;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.VolleyError;

public class RewardedVideoCompletionRequestHandler implements RewardedVideoCompletionRequestListener {
    static final int[] RETRY_TIMES = {5000, 10000, Indexable.MAX_STRING_LENGTH, 40000, 60000};
    @NonNull
    private final Context mContext;
    @NonNull
    private final Handler mHandler;
    @NonNull
    private final RequestQueue mRequestQueue;
    private int mRetryCount;
    private volatile boolean mShouldStop;
    @NonNull
    private final String mUrl;

    RewardedVideoCompletionRequestHandler(@NonNull Context context, @NonNull String str, @Nullable String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5, @Nullable String str6) {
        this(context, str, str2, str3, str4, str5, str6, new Handler());
    }

    @VisibleForTesting
    RewardedVideoCompletionRequestHandler(@NonNull Context context, @NonNull String str, @Nullable String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5, @Nullable String str6, @NonNull Handler handler) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str3);
        Preconditions.checkNotNull(str4);
        Preconditions.checkNotNull(handler);
        this.mUrl = appendParameters(str, str2, str3, str4, str5, str6);
        this.mRetryCount = 0;
        this.mHandler = handler;
        this.mRequestQueue = Networking.getRequestQueue(context);
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: 0000 */
    public void makeRewardedVideoCompletionRequest() {
        if (this.mShouldStop) {
            this.mRequestQueue.cancelAll((Object) this.mUrl);
            return;
        }
        RewardedVideoCompletionRequest rewardedVideoCompletionRequest = new RewardedVideoCompletionRequest(this.mContext, this.mUrl, new DefaultRetryPolicy(getTimeout(this.mRetryCount) - 1000, 0, BitmapDescriptorFactory.HUE_RED), this);
        rewardedVideoCompletionRequest.setTag(this.mUrl);
        this.mRequestQueue.add(rewardedVideoCompletionRequest);
        if (this.mRetryCount >= 17) {
            MoPubLog.d("Exceeded number of retries for rewarded video completion request.");
            return;
        }
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                RewardedVideoCompletionRequestHandler.this.makeRewardedVideoCompletionRequest();
            }
        }, (long) getTimeout(this.mRetryCount));
        this.mRetryCount++;
    }

    public void onResponse(Integer num) {
        if (num == null) {
            return;
        }
        if (num.intValue() < 500 || num.intValue() >= 600) {
            this.mShouldStop = true;
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (volleyError != null && volleyError.networkResponse != null) {
            if (volleyError.networkResponse.statusCode < 500 || volleyError.networkResponse.statusCode >= 600) {
                this.mShouldStop = true;
            }
        }
    }

    public static void makeRewardedVideoCompletionRequest(@Nullable Context context, @Nullable String str, @Nullable String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5, @Nullable String str6) {
        if (context != null && !TextUtils.isEmpty(str) && str3 != null && str4 != null) {
            RewardedVideoCompletionRequestHandler rewardedVideoCompletionRequestHandler = new RewardedVideoCompletionRequestHandler(context, str, str2, str3, str4, str5, str6);
            rewardedVideoCompletionRequestHandler.makeRewardedVideoCompletionRequest();
        }
    }

    static int getTimeout(int i) {
        if (i >= 0) {
            int[] iArr = RETRY_TIMES;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        int[] iArr2 = RETRY_TIMES;
        return iArr2[iArr2.length - 1];
    }

    private static String appendParameters(@NonNull String str, @Nullable String str2, @NonNull String str3, @NonNull String str4, @Nullable String str5, @Nullable String str6) {
        String str7;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str3);
        Preconditions.checkNotNull(str4);
        StringBuilder sb = new StringBuilder(str);
        sb.append("&customer_id=");
        sb.append(str2 == null ? "" : Uri.encode(str2));
        sb.append("&rcn=");
        sb.append(Uri.encode(str3));
        sb.append("&rca=");
        sb.append(Uri.encode(str4));
        sb.append("&nv=");
        sb.append(Uri.encode("5.4.1"));
        sb.append("&v=");
        sb.append(1);
        sb.append("&cec=");
        if (str5 == null) {
            str7 = "";
        } else {
            str7 = Uri.encode(str5);
        }
        sb.append(str7);
        if (!TextUtils.isEmpty(str6)) {
            sb.append("&rcd=");
            sb.append(Uri.encode(str6));
        }
        return sb.toString();
    }
}
