package com.mopub.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastErrorCode;
import com.mopub.mobileads.VastMacroHelper;
import com.mopub.mobileads.VastTracker;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackingRequest extends MoPubRequest<Void> {
    @Nullable
    private final Listener mListener;

    public interface Listener extends ErrorListener {
        void onResponse(@NonNull String str);
    }

    private TrackingRequest(@NonNull Context context, @NonNull String str, @Nullable Listener listener) {
        super(context, str, listener);
        this.mListener = listener;
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(2500, 0, 1.0f));
    }

    /* access modifiers changed from: protected */
    public Response<Void> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse.statusCode == 200) {
            return Response.success(null, HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to log tracking request. Response code: ");
        sb.append(networkResponse.statusCode);
        sb.append(" for url: ");
        sb.append(getUrl());
        return Response.error(new MoPubNetworkError(sb.toString(), Reason.TRACKING_FAILURE));
    }

    public void deliverResponse(Void voidR) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onResponse(getUrl());
        }
    }

    public static void makeVastTrackingHttpRequest(@NonNull List<VastTracker> list, @Nullable VastErrorCode vastErrorCode, @Nullable Integer num, @Nullable String str, @Nullable Context context) {
        Preconditions.checkNotNull(list);
        ArrayList arrayList = new ArrayList(list.size());
        for (VastTracker vastTracker : list) {
            if (vastTracker != null && (!vastTracker.isTracked() || vastTracker.isRepeatable())) {
                arrayList.add(vastTracker.getContent());
                vastTracker.setTracked();
            }
        }
        makeTrackingHttpRequest((Iterable<String>) new VastMacroHelper(arrayList).withErrorCode(vastErrorCode).withContentPlayHead(num).withAssetUri(str).getUris(), context);
    }

    public static void makeTrackingHttpRequest(@Nullable Iterable<String> iterable, @Nullable Context context, @Nullable final Listener listener) {
        if (iterable != null && context != null) {
            MoPubRequestQueue requestQueue = Networking.getRequestQueue(context);
            for (final String str : iterable) {
                if (!TextUtils.isEmpty(str)) {
                    requestQueue.add(new TrackingRequest(context, str, new Listener() {
                        public void onResponse(@NonNull String str) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Successfully hit tracking endpoint: ");
                            sb.append(str);
                            MoPubLog.d(sb.toString());
                            Listener listener = listener;
                            if (listener != null) {
                                listener.onResponse(str);
                            }
                        }

                        public void onErrorResponse(VolleyError volleyError) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Failed to hit tracking endpoint: ");
                            sb.append(str);
                            MoPubLog.d(sb.toString());
                            Listener listener = listener;
                            if (listener != null) {
                                listener.onErrorResponse(volleyError);
                            }
                        }
                    }));
                }
            }
        }
    }

    public static void makeTrackingHttpRequest(@Nullable String str, @Nullable Context context) {
        makeTrackingHttpRequest(str, context, (Listener) null);
    }

    public static void makeTrackingHttpRequest(@Nullable String str, @Nullable Context context, @Nullable Listener listener) {
        if (str != null) {
            makeTrackingHttpRequest((Iterable<String>) Arrays.asList(new String[]{str}), context, listener);
        }
    }

    public static void makeTrackingHttpRequest(@Nullable Iterable<String> iterable, @Nullable Context context) {
        makeTrackingHttpRequest(iterable, context, (Listener) null);
    }
}
