package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;

public interface SessionsApi {

    public static class ViewIntentBuilder {
        private boolean zzaa = false;
        private final Context zzp;
        private String zzu;
        private Session zzz;

        public ViewIntentBuilder(Context context) {
            this.zzp = context;
        }

        public ViewIntentBuilder setSession(Session session) {
            this.zzz = session;
            return this;
        }

        public ViewIntentBuilder setPreferredApplication(@Nullable String str) {
            this.zzu = str;
            this.zzaa = true;
            return this;
        }

        public Intent build() {
            Preconditions.checkState(this.zzz != null, "Session must be set");
            Intent intent = new Intent(Fitness.ACTION_VIEW);
            intent.setType(Session.getMimeType(this.zzz.getActivity()));
            SafeParcelableSerializer.serializeToIntentExtra(this.zzz, intent, Session.EXTRA_SESSION);
            if (!this.zzaa) {
                this.zzu = this.zzz.getAppPackageName();
            }
            if (this.zzu != null) {
                Intent intent2 = new Intent(intent).setPackage(this.zzu);
                ResolveInfo resolveActivity = this.zzp.getPackageManager().resolveActivity(intent2, 0);
                if (resolveActivity != null) {
                    intent2.setComponent(new ComponentName(this.zzu, resolveActivity.activityInfo.name));
                    return intent2;
                }
            }
            return intent;
        }
    }

    PendingResult<Status> insertSession(GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest);

    PendingResult<SessionReadResult> readSession(GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest);

    PendingResult<Status> registerForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> startSession(GoogleApiClient googleApiClient, Session session);

    PendingResult<SessionStopResult> stopSession(GoogleApiClient googleApiClient, @Nullable String str);

    PendingResult<Status> unregisterForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent);
}
