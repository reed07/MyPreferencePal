package com.mopub.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.toolbox.HttpHeaderParser;

public class MultiAdRequest extends MoPubRequest<MultiAdResponse> {
    private int hashCode = 0;
    @NonNull
    final AdFormat mAdFormat;
    @Nullable
    final String mAdUnitId;
    @NonNull
    private final Context mContext;
    @NonNull
    public final Listener mListener;

    public interface Listener extends ErrorListener {
        void onSuccessResponse(MultiAdResponse multiAdResponse);
    }

    MultiAdRequest(@NonNull String str, @NonNull AdFormat adFormat, @Nullable String str2, @NonNull Context context, @NonNull Listener listener) {
        super(context, clearUrlIfSdkNotInitialized(str), listener);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(adFormat);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(listener);
        this.mAdUnitId = str2;
        this.mListener = listener;
        this.mAdFormat = adFormat;
        this.mContext = context.getApplicationContext();
        setRetryPolicy(new DefaultRetryPolicy(2500, 1, 1.0f));
        setShouldCache(false);
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        if (personalInformationManager != null) {
            personalInformationManager.requestSync(false);
        }
    }

    @NonNull
    private static String clearUrlIfSdkNotInitialized(@NonNull String str) {
        if (MoPub.getPersonalInformationManager() != null && MoPub.isSdkInitialized()) {
            return str;
        }
        MoPubLog.e("Make sure to call MoPub#initializeSdk before loading an ad.");
        return "";
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Response<MultiAdResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(new MultiAdResponse(this.mContext, networkResponse, this.mAdFormat, this.mAdUnitId), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (Exception e) {
            if (e instanceof MoPubNetworkError) {
                return Response.error((MoPubNetworkError) e);
            }
            return Response.error(new MoPubNetworkError((Throwable) e, Reason.UNSPECIFIED));
        }
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(MultiAdResponse multiAdResponse) {
        if (!isCanceled()) {
            this.mListener.onSuccessResponse(multiAdResponse);
        }
    }

    public void cancel() {
        super.cancel();
    }

    public boolean equals(Object obj) {
        int i;
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiAdRequest)) {
            return false;
        }
        MultiAdRequest multiAdRequest = (MultiAdRequest) obj;
        String str = this.mAdUnitId;
        if (str != null) {
            String str2 = multiAdRequest.mAdUnitId;
            i = str2 == null ? 1 : str.compareTo(str2);
        } else {
            i = multiAdRequest.mAdUnitId != null ? -1 : 0;
        }
        if (!(i == 0 && this.mAdFormat == multiAdRequest.mAdFormat && getUrl().compareTo(multiAdRequest.getUrl()) == 0)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.mAdUnitId;
            this.hashCode = ((((str == null ? 29 : str.hashCode()) * 31) + this.mAdFormat.hashCode()) * 31) + getOriginalUrl().hashCode();
        }
        return this.hashCode;
    }
}
