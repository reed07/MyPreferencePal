package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.fitness.zzab;
import com.google.android.gms.internal.fitness.zzag;
import com.google.android.gms.internal.fitness.zzam;
import com.google.android.gms.internal.fitness.zzas;
import com.google.android.gms.internal.fitness.zzay;
import com.google.android.gms.internal.fitness.zzct;
import com.google.android.gms.internal.fitness.zzdb;
import com.google.android.gms.internal.fitness.zzdg;
import com.google.android.gms.internal.fitness.zzdj;
import com.google.android.gms.internal.fitness.zzdt;
import com.google.android.gms.internal.fitness.zzea;
import com.google.android.gms.internal.fitness.zzee;
import com.google.android.gms.internal.fitness.zzeq;
import com.google.android.gms.internal.fitness.zzp;
import com.google.android.gms.internal.fitness.zzv;
import java.util.concurrent.TimeUnit;

public class Fitness {
    public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
    public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
    public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";
    @Deprecated
    public static final Void API = null;
    public static final Api<NoOptions> BLE_API = zzp.API;
    public static final BleApi BleApi;
    public static final Api<NoOptions> CONFIG_API = zzv.API;
    public static final ConfigApi ConfigApi = new zzdb();
    public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";
    public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
    public static final Api<NoOptions> GOALS_API = zzab.API;
    public static final GoalsApi GoalsApi = new zzdg();
    public static final Api<NoOptions> HISTORY_API = zzag.API;
    public static final HistoryApi HistoryApi = new zzdj();
    public static final Api<NoOptions> RECORDING_API = zzam.API;
    public static final RecordingApi RecordingApi = new zzdt();
    public static final Scope SCOPE_ACTIVITY_READ = new Scope(Scopes.FITNESS_ACTIVITY_READ);
    public static final Scope SCOPE_ACTIVITY_READ_WRITE = new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE);
    public static final Scope SCOPE_BODY_READ = new Scope(Scopes.FITNESS_BODY_READ);
    public static final Scope SCOPE_BODY_READ_WRITE = new Scope(Scopes.FITNESS_BODY_READ_WRITE);
    public static final Scope SCOPE_LOCATION_READ = new Scope(Scopes.FITNESS_LOCATION_READ);
    public static final Scope SCOPE_LOCATION_READ_WRITE = new Scope(Scopes.FITNESS_LOCATION_READ_WRITE);
    public static final Scope SCOPE_NUTRITION_READ = new Scope(Scopes.FITNESS_NUTRITION_READ);
    public static final Scope SCOPE_NUTRITION_READ_WRITE = new Scope(Scopes.FITNESS_NUTRITION_READ_WRITE);
    public static final Api<NoOptions> SENSORS_API = zzas.API;
    public static final Api<NoOptions> SESSIONS_API = zzay.API;
    public static final SensorsApi SensorsApi = new zzea();
    public static final SessionsApi SessionsApi = new zzee();

    private Fitness() {
    }

    public static SensorsClient getSensorsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static SensorsClient getSensorsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static RecordingClient getRecordingClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static RecordingClient getRecordingClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static SessionsClient getSessionsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static SessionsClient getSessionsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static HistoryClient getHistoryClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static HistoryClient getHistoryClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static GoalsClient getGoalsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static GoalsClient getGoalsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static ConfigClient getConfigClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static ConfigClient getConfigClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static BleClient getBleClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(activity, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static BleClient getBleClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(context, FitnessOptions.zza(googleSignInAccount).build());
    }

    public static long getStartTime(Intent intent, TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_START_TIME, -1);
        if (longExtra == -1) {
            return -1;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    public static long getEndTime(Intent intent, TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_END_TIME, -1);
        if (longExtra == -1) {
            return -1;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    static {
        BleApi bleApi;
        if (VERSION.SDK_INT >= 18) {
            bleApi = new zzct();
        } else {
            bleApi = new zzeq();
        }
        BleApi = bleApi;
    }
}
