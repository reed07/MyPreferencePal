package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.SessionsApi;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;
import java.util.concurrent.TimeUnit;

public final class zzee implements SessionsApi {
    public final PendingResult<Status> startSession(GoogleApiClient googleApiClient, Session session) {
        Preconditions.checkNotNull(session, "Session cannot be null");
        Preconditions.checkArgument(session.getEndTime(TimeUnit.MILLISECONDS) == 0, "Cannot start a session which has already ended");
        return googleApiClient.execute(new zzef(this, googleApiClient, session));
    }

    public final PendingResult<SessionStopResult> stopSession(GoogleApiClient googleApiClient, @Nullable String str) {
        return googleApiClient.execute(new zzeg(this, googleApiClient, null, str));
    }

    public final PendingResult<Status> insertSession(GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest) {
        return googleApiClient.enqueue(new zzeh(this, googleApiClient, sessionInsertRequest));
    }

    public final PendingResult<SessionReadResult> readSession(GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest) {
        return googleApiClient.enqueue(new zzei(this, googleApiClient, sessionReadRequest));
    }

    public final PendingResult<Status> registerForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return googleApiClient.execute(new zzej(this, googleApiClient, pendingIntent, 0));
    }

    public final PendingResult<Status> unregisterForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return googleApiClient.execute(new zzek(this, googleApiClient, pendingIntent));
    }
}
