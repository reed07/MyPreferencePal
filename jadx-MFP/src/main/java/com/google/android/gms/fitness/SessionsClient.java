package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResponse;
import com.google.android.gms.internal.fitness.zzay;
import com.google.android.gms.internal.fitness.zzee;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class SessionsClient extends GoogleApi<FitnessOptions> {
    private static final SessionsApi zzab = new zzee();

    SessionsClient(@NonNull Context context, @NonNull FitnessOptions fitnessOptions) {
        super(context, zzay.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    SessionsClient(@NonNull Activity activity, @NonNull FitnessOptions fitnessOptions) {
        super(activity, zzay.zzew, fitnessOptions, Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> startSession(Session session) {
        return PendingResultUtil.toVoidTask(zzab.startSession(asGoogleApiClient(), session));
    }

    public Task<List<Session>> stopSession(@Nullable String str) {
        return PendingResultUtil.toTask(zzab.stopSession(asGoogleApiClient(), str), zzp.zzf);
    }

    public Task<Void> insertSession(SessionInsertRequest sessionInsertRequest) {
        return PendingResultUtil.toVoidTask(zzab.insertSession(asGoogleApiClient(), sessionInsertRequest));
    }

    public Task<SessionReadResponse> readSession(SessionReadRequest sessionReadRequest) {
        return PendingResultUtil.toResponseTask(zzab.readSession(asGoogleApiClient(), sessionReadRequest), new SessionReadResponse());
    }

    public Task<Void> registerForSessions(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzab.registerForSessions(asGoogleApiClient(), pendingIntent));
    }

    public Task<Void> unregisterForSessions(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzab.unregisterForSessions(asGoogleApiClient(), pendingIntent));
    }
}
