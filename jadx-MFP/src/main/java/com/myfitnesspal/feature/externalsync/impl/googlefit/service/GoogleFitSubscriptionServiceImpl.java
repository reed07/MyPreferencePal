package com.myfitnesspal.feature.externalsync.impl.googlefit.service;

import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataType;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;

public class GoogleFitSubscriptionServiceImpl extends SimpleAsyncServiceBase implements GoogleFitSubscriptionService {
    private static final int MAX_THREADS = 1;
    /* access modifiers changed from: private */
    public static final String TAG = "GoogleFitSubscriptionServiceImpl";

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public void subscribe(final GoogleApiClient googleApiClient) {
        auto(new Runnable() {
            public void run() {
                Fitness.RecordingApi.subscribe(googleApiClient, DataType.TYPE_STEP_COUNT_DELTA).setResultCallback(new ResultCallback<Status>() {
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            Log.d(GoogleFitSubscriptionServiceImpl.TAG, "Google Fit subscribe for steps");
                        } else if (status.getStatusCode() == -5001) {
                            Log.d(GoogleFitSubscriptionServiceImpl.TAG, "Google Fit subscription for steps already exist");
                        }
                    }
                });
            }
        });
    }

    public void unsubscribe(final GoogleApiClient googleApiClient, final Function1<Boolean> function1) {
        auto(new Runnable() {
            public void run() {
                try {
                    Fitness.RecordingApi.unsubscribe(googleApiClient, DataType.TYPE_STEP_COUNT_DELTA).setResultCallback(new ResultCallback<Status>() {
                        public void onResult(Status status) {
                            Ln.d("Google Fit unsubscribe from steps", new Object[0]);
                            GoogleFitSubscriptionServiceImpl.this.executeIfValid(function1, Boolean.valueOf(status.isSuccess()));
                        }
                    });
                } catch (IllegalStateException unused) {
                    Ln.d("Google Fit unsubscribe while google client disconnected", new Object[0]);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void executeIfValid(Function1<Boolean> function1, Boolean bool) {
        if (function1 != null) {
            function1.execute(bool);
        }
    }
}
