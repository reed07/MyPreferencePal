package com.mopub.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.VolleyError;

public class MoPubNetworkError extends VolleyError {
    @NonNull
    private final Reason mReason;
    @Nullable
    private final Integer mRefreshTimeMillis;

    public enum Reason {
        WARMING_UP,
        NO_FILL,
        BAD_HEADER_DATA,
        BAD_BODY,
        TRACKING_FAILURE,
        UNSPECIFIED
    }

    public MoPubNetworkError(@NonNull Reason reason) {
        this.mReason = reason;
        this.mRefreshTimeMillis = null;
    }

    public MoPubNetworkError(@NonNull NetworkResponse networkResponse, @NonNull Reason reason) {
        super(networkResponse);
        this.mReason = reason;
        this.mRefreshTimeMillis = null;
    }

    public MoPubNetworkError(@NonNull Throwable th, @NonNull Reason reason) {
        super(th);
        this.mReason = reason;
        this.mRefreshTimeMillis = null;
    }

    public MoPubNetworkError(@NonNull String str, @NonNull Reason reason) {
        this(str, reason, (Integer) null);
    }

    public MoPubNetworkError(@NonNull String str, @NonNull Throwable th, @NonNull Reason reason) {
        super(str, th);
        this.mReason = reason;
        this.mRefreshTimeMillis = null;
    }

    public MoPubNetworkError(@NonNull String str, @NonNull Reason reason, @Nullable Integer num) {
        super(str);
        this.mReason = reason;
        this.mRefreshTimeMillis = num;
    }

    @NonNull
    public Reason getReason() {
        return this.mReason;
    }

    @Nullable
    public Integer getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }
}
