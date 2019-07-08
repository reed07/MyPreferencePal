package com.google.android.gms.location;

import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

@Deprecated
public interface ActivityRecognitionApi {
    @RequiresPermission
    PendingResult<Status> removeActivityUpdates(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    @RequiresPermission
    PendingResult<Status> requestActivityUpdates(GoogleApiClient googleApiClient, long j, PendingIntent pendingIntent);

    @RequiresPermission
    PendingResult<Status> zza(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    @RequiresPermission
    PendingResult<Status> zza(GoogleApiClient googleApiClient, ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent);
}
